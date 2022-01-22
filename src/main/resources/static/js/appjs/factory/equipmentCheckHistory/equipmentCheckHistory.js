var prefix = "/factory/equipmentCheckHistory"
$(function () {
    load();
});

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
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                onDblClickRow: function (row, $element) {//双击事件
                    if (s_checkDetails_h != 'hidden') {
                        checkDetails(row.checkHistoryId);
                    }
                },
                queryParams: function (params) {
                    return {
                        limit: params.limit,
                        offset: params.offset,
                        name: $('#searchName').val(),
                        searchCode: $('#searchCode').val(),
                        dateBefore: $('#searchDateBefore').val(),
                        dateLater: $('#searchDateLate').val()
                    };
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        visible: false,
                        field: 'checkHistoryId',
                        title: '点检序号'
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
                        field: 'state',
                        title: '状态',
                        align: 'center',
                        formatter: function (value, row, index) {
                            if (value == 0)
                                return '<button type="button" class="btn  btn-danger btn-sm"\n' +
                                    '<i class="fa fa-crosshairs" aria-hidden="true"></i>待点检\n' +
                                    '</button>';
                            else if (value == 1)
                                return '<button type="button" class="btn  btn-default btn-sm"\n' +
                                    '<i class="fa fa-crosshairs" aria-hidden="true"></i>异常\n' +
                                    '</button>';
                            else
                                return '<button type="button" class="btn  btn-primary btn-sm"\n' +
                                    '<i class="fa fa-crosshairs" aria-hidden="true"></i>已点检\n' +
                                    '</button>';
                        }
                    },
                    {
                        field: 'checkDate',
                        title: '点检日期'
                    },
                    {
                        field: 'checkerId',
                        title: '点检人'
                    },
                    {
                        field: 'remarks',
                        title: '备注'
                    },
                    {
                        visible: false,
                        field: 'isDeleted',
                        title: '删除标记'
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
                    ]
            });
}

function reLoad() {
    var dateBefore = $('#searchDateBefore').val();
    var dateLater = $('#searchDateLate').val();
    if (dateBefore != null && dateBefore != "" && dateLater != null && dateLater != "" && dateBefore > dateLater) {
        layer.msg("请输入正确的日期范围");
        return;
    }
    $('#exampleTable').bootstrapTable('refresh');
}
function reSet() {
    $('#searchName').val("");
    $('#searchCode').val("");
        $('#searchDateBefore').val(""),
        $('#searchDateLate').val("")


    $('#exampleTable').bootstrapTable('refresh');
}

function add() {
    console.log("test top layer open")
    top.layer.open({
        type: 2,
        title: '新增历史',
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
    var name = $('#searchName').val();
    var searchType = $('#searchType').val();
    location.href = prefix + "/exportFile" + "?name=" + name + "&searchType=" + searchType;
}

function checkDetails(checkHistoryId) {
    console.log("test top layer open")
    top.layer.open({
        type: 2,
        title: '点检',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['1000px', '600px'],
        content: prefix + '/checkDetails/' + checkHistoryId, // iframe的url
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
                'checkHistoryId': id
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
        var ids = new Array();
        $.each(rows, function (i, row) {
            ids[i] = row['checkHistoryId'];
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