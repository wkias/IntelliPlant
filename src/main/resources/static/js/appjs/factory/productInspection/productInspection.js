var prefix = "/factory/productInspection"
$(function () {
    load();
    reLoad();
    loadState();
});

function loadState(){
    $.ajax({
        url: '/common/dict/list/state',
        success: function (data) {
            var html = "";
            // 加载数据
            html+="<option value=''>所有状态</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#state").append(html);
            $("#state").chosen({
                maxHeight: 200
            });
            $("#state").trigger("chosen:updated");
        }
    });
}

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list", // 服务器数据的加载地址
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                singleSelect: false, // 设置为true将禁止多选
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                onDblClickRow: function (row, $element) {//双击事件
                    if (s_details_h != 'hidden') {
                        details(row.productInspectionId);
                    }
                },
                queryParams: function (params) {
                    let va = 1;
                    if (params.valueOf("state") === 0) {
                        va = 0;
                    }else if(params.valueOf("state") === 1){
                        va = 1;
                    }else if(params.valueOf("state") === 2){
                        va = 1 || 0;
                    }
                    return {
                        limit: params.limit,
                        offset: params.offset,
                        state: va,
                    };
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        visible: false,
                        field: 'productInspectionId',
                        title: '工序质检ID'
                    },
                    {
                        field: 'associatedProduct',
                        title: '产品名称'
                    },
                    {
                        field: 'productCode',
                        title: '产品编号'
                    },
                    {
                        field: 'state',
                        title: '状态',
                        align: 'center',
                        formatter: function (value, row, index) {
                            if (value == '0') {
                                return '<span class="label label-danger">已停用</span>';
                            } else if (value == '1') {
                                return '<span class="label label-primary">启用中</span>';
                            }
                        }
                    },
                    {
                        title: '质检项目名称',
                        formatter: function (value, row, index) {
                            if (row.inspectionItems != null) {
                                let html = "";
                                $.ajax({
                                    type: "get",
                                    url: "/factory/productInspection/inspectionItemsList/" + row.productInspectionId,
                                    async: false,//同步请求
                                    success: function (data) {
                                        for (let i = 0; i < data.length - 1; i++) {
                                            html += data[i].inspectionItems.projectName + ",";
                                            console.log(data);
                                        }
                                        html += data[data.length - 1].inspectionItems.projectName;
                                    }
                                })
                                return html;
                            }
                        }
                    },
                    {
                        visible: false,
                        field: 'valueType',
                        title: '值类型'
                    },
                    {
                        visible: false,
                        field: 'unit',
                        title: '单位'
                    },
                    {
                        visible: false,
                        field: 'rangeThreshold',
                        title: '范围阈值'
                    },
                    {
                        visible: false,
                        field: 'description',
                        title: '说明'
                    },
                    {
                        visible: false,
                        field: 'result',
                        title: '质检结果；0报废；1返工；2合格'
                    },
                    {
                        visible: false,
                        field: 'represent',
                        title: '描述'
                    },
                    {
                        visible: false,
                        field: 'isDeleted',
                        title: '删除标记；默认值0；0：未删除；1：已删除'
                    },
                    {
                        visible: false,
                        field: 'createUserId',
                        title: '创建者'
                    },
                    {
                        visible: false,
                        field: 'createTime',
                        title: '创建时间'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        width: "250px",
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.productInspectionId
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-success btn-sm" href="#" mce_href="#" title="启用" onclick="start(\''
                                + row.productInspectionId
                                + '\')"><i class="fa fa-hourglass-start"></i>启用</a> ';
                            var f = '<a class="btn btn-danger btn-sm" href="#" mce_href="#" title="停用" onclick="stop(\''
                                + row.productInspectionId
                                + '\')"><i class="fa fa-square-o">停用</i></a> ';
                            if (row.state == 0) {
                                return e + d;
                            } else {
                                return e + f;
                            }
                        }
                    }]
            });
}

var opt = {};

function reLoad() {//搜索（带参刷新）
    //$('#exampleTable').bootstrapTable('refresh');
    opt = {
        query: {
            associatedProduct: $('#associatedProduct').val(),
            productCode: $('#productCode').val(),
            state: $('#state').val()
        }
    }
    $('#exampleTable').bootstrapTable('refresh', opt);
}

function reset() {//重置
    $('#associatedProduct').val("");
    $('#productCode').val("");
    $('#state').val("");
    $('#exampleTable').bootstrapTable('refresh')
}

function add() {
    console.log("test top layer open")
    top.layer.open({
        type: 2,
        title: '新增质检',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['1000px', '600px'],
        content: prefix + '/add', // iframe的url
        success: function (layero, index) {
            top.layerParentCP = window
        }
    });
}

function details(id) {
    console.log("test top layer open")
    top.layer.open({
        type: 2,
        tile: "信息详情",
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['1000px', '600px'],
        content: prefix + '/details/' + id,
        success: function (layero, index) {
            top.layerParent = window
        }
    })
}

function start(productInspectionId) {
    $.ajax({
        type: "post",
        url: prefix + "/update",
        data: "productInspectionId=" + productInspectionId + "&state=" + 1,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                layer.msg("操作成功");
                reLoad();
            } else {
                layer.alert(data.msg)
            }

        }
    })
}

function stop(productInspectionId) {
    $.ajax({
        type: "post",
        url: prefix + "/update",
        data: "productInspectionId=" + productInspectionId + "&state=" + 0,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                layer.msg("操作成功");
                reLoad();
            } else {
                layer.alert(data.msg)
            }

        }
    })
}

function edit(id) {
    console.log("test top layer open")
    top.layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['1000px', '600px'],
        content: prefix + '/edit/' + id,// iframe的url
        success: function (layero, index) {
            top.layerParent = window
        }
    });
}

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'productInspectionId': id
            },
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}

function resetPwd(id) {
}

function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function (i, row) {
            ids[i] = row['productInspectionId'];
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: prefix + '/batchRemove',
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function () {

    });
}