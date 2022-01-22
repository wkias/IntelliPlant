var prefix = "/factory/logisticsAccount"
$(function () {
    loadType();
    load();
});

function loadType() {
    var html = "";
    $.ajax({
        url: '/common/dict/list/logistics_company',
        success: function (data) {
            // 加载数据
            html += "<option value=''>所有</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $(".chosen-select").append(html);
            $(".chosen-select").chosen({
                maxHeight: 200
            });
            $(".chosen-select").val($("#Ttype").val());
            $(".chosen-select").trigger("chosen:updated");
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
                        details(row.logisticsId);
                    }
                },
                queryParams: function (params) {
                    return {
                        limit: params.limit,
                        offset: params.offset,
                        Id: $('#searchId').val(),
                        searchType: $('#searchType').val(),
                    };
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        visible: false,
                        field: 'logisticsId',
                        title: '物流台账ID'
                    },
                    {
                        field: 'formId',
                        title: '表单编号',
                    },
                    {
                        field: 'formType',
                        title: '表单类型'
                    },
                    {
                        field: 'forwardingUnit',
                        title: '发货单位'
                    },
                    {
                        field: 'logisticsNumber',
                        title: '物流台账编号'
                    },
                    {
                        field: 'logisticsCompany',
                        title: '物流公司'
                    },
                    {
                        field: 'receiveUnit',
                        title: '收货单位'
                    },
                    {
                        visible: false,
                        field: 'totalQuantity',
                        title: '总数量'
                    },
                    {
                        visible: false,
                        field: 'totalWeight',
                        title: '总重量'
                    },
                    {
                        field: 'totalMoney',
                        title: '运费总金额'
                    },
                    {
                        visible: false,
                        field: 'driverName',
                        title: '司机名称'
                    },
                    {
                        visible: false,
                        field: 'phoneNumber',
                        title: '联系电话'
                    },
                    {
                        visible: false,
                        field: 'carNumber',
                        title: '车牌号'
                    },
                    {
                        visible: false,
                        field: 'driverLicense',
                        title: '驾照'
                    },
                    {
                        visible: false,
                        field: 'driverPermit',
                        title: '行驶证'
                    },
                    {
                        visible: false,
                        field: 'weightUnit',
                        title: '重量单位'
                    },
                    {
                        visible: false,
                        field: 'weight',
                        title: '重量'
                    },
                    {
                        visible: false,
                        field: 'money',
                        title: '金额'
                    },
                    {
                        visible: false,
                        field: 'state',
                        title: '备注'
                    },
                    {
                        visible: false,
                        field: 'files',
                        title: '附件'
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
                        formatter: function (value, row, index) {
                            return '<a class="btn btn-success btn-sm"' + s_attachment_h + ' href="#" title="附件"  mce_href="#" onclick="files(\''
                                + row.files
                                + '\')"><i class="fa fa-clone"></i></a> ';
                        }
                    }]
            });
}

function files(filesName) {
    if (filesName === "null") {
        layer.msg("没有上传附件");
        return;
    }
    location.href = prefix + "/file/" + filesName;
}

var opt = {};

function reLoad() {//搜索（带参刷新）
    opt = {
        query: {
            logisticsNumber: $('#logisticsNumber').val(),
            formId: $('#formId').val(),//为方便url传参，模糊查询在mapper中实现
        }
    }
    $('#exampleTable').bootstrapTable('refresh', opt);
}

function reset() {//重置
    $('#logisticsNumber').val("");
    $('#formId').val("");
    $('#exampleTable').bootstrapTable('refresh')
}

function add() {
    console.log("test top layer open")
    top.layer.open({
        type: 2,
        title: '增加',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['1000px', '600px'],
        content: prefix + '/add', // iframe的url
        success: function (layero, index) {
            top.layerParent = window
        }
    });
}

function exportFile() {
    var formId = $('#searchId').val();
    var formType = $('#searchType').val();
    location.href = prefix + "/exportFile" + "?Id=" + formId + "&searchType=" + formType;
}

function details(id) {
    console.log("test top layer open")
    top.layer.open({
        type: 2,
        tile: "详情",
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['1000px', '600px'],
        content: prefix + '/details/' + id,
        success: function (layero, index) {
            top.layerParent = window
        }
    })
}

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'logisticsId': id
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
    }, function () {
        var ids = [];
        $.each(rows, function (i, row) {
            ids[i] = row['logisticsId'];
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