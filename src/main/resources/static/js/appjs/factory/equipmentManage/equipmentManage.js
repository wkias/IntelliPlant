var prefix = "/factory/equipmentManage"
$(function () {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                onDblClickRow: function (row, $element) {//双击事件
                    if (s_details_h != 'hidden') {
                        details(row.equipmentId);
                    }
                },
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset
                        // name:$('#searchName').val(),
                        // username:$('#searchName').val()
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        visible: false,
                        field: 'equipmentId',
                        title: '设备ID'
                    },
                    {
                        field: 'equipmentType',
                        title: '设备类型'
                    },
                    {
                        field: 'code',
                        title: '设备编码'
                    },
                    {
                        field: 'name',
                        title: '设备名称'
                    },
                    {
                        visible: false,
                        field: 'photo',
                        title: '设备图片',
                        formatter: function (value, row, index) {
                            //通过图片名获取图片实际地址，若图片存于专用服务器可直接在src属性中请求Servlet进行重定向到图片的网络地址
                            if (value == null) return;
                            return "<img style='height: 80px;width: 80px' src='" + prefix + "/photo/" + value + "'/>"
                        }
                    },
                    {
                        field: 'provider',
                        title: '供应商'
                    },
                    {
                        field: 'model',
                        title: '设备型号'
                    },
                    {
                        field: 'productionDate',
                        title: '出厂日期'
                    },
                    {
                        visible: true,
                        field: 'remarks',
                        title: '备注'
                    },
                    {
                        visible: false,
                        field: 'files',
                        title: '附件'
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
                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.equipmentId
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.equipmentId
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                                + row.equipmentId
                                + '\')"><i class="fa fa-key"></i></a> ';
                            return e + d;
                        }
                    }]
            });
}

var opt = {};

function reLoad() {//搜索（带参刷新）
    //$('#exampleTable').bootstrapTable('refresh');
    opt = {
        query: {
            name: $('#searchName').val(),
            code: $('#searchCode').val()	//为方便url传参，模糊查询在mapper中实现
        }
    };
    $('#exampleTable').bootstrapTable('refresh', opt);
}

function reset() {//重置
    $('#searchName').val("");
    $('#searchCode').val("");
    $('#exampleTable').bootstrapTable('refresh')
}

function add() {
    top.layer.open({
        type: 2,
        title: '增加',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['1000px', '600px'],
        content: prefix + '/add',// iframe的url
        success: function (layero, index) {
            top.layerParent = window
        }
    });
}

function edit(id) {
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

function details(id) {
    top.layer.open({
        type: 2,
        tile: "详情",
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
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
                'equipmentId': id
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
            ids[i] = row['equipmentId'];
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

function exportFile() {
    let url = prefix + "/exportFile";
    if (JSON.stringify(opt) != '{}') {
        url += "?code=" + opt.query.code + "&name=" + opt.query.name;
        console.log("url:" + url);
    }
    location.href = url;
}