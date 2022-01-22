var prefix = "/reimburse/reimbursement"
$(function () {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get',
                url: prefix + "/list",
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true,
                dataType: "json",
                pagination: true,
                singleSelect: false,
                pageSize: 10,
                pageNumber: 1,
                showColumns: false,
                sidePagination: "server",
                queryParams: function (params) {
                    return {
                        limit: params.limit,
                        offset: params.offset,
                        startDate: $("#startDate").val(),
                        endDate: $("#endDate").val(),
                        invoiceId: $("#searchId").val(),
                    };
                },
                onDblClickRow: function (row, $element) {
                    detail(row.reimburseId);
                },
                columns: [
                    {
                        checkbox:true,
                    },
                    {
                        field: 'invoiceTypeName',
                        title: '单据类型'
                    },
                    {
                        field: 'reason',
                        title: '报销事由'
                    },
                    {
                        field: 'payee',
                        title: '收款账户'
                    },
                    {
                        field: 'state',
                        title: '状态',
                        align: 'center',
                        formatter: function (value, row, index) {
                            if (row.isDraft === 1) {
                                return '<a class="btn btn-default btn-sm" title="草稿">草稿</a>';
                            } else if (value === 0) {
                                return '<a class="btn btn-danger btn-sm" title="未完成">未完成</a>';
                            } else {
                                return '<a class="btn btn-primary btn-sm" title="已完成">已完成</a>';
                            }
                        }
                    },
                    {
                        field: 'createTime',
                        title: '创建时间'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            let e = '<a class="btn btn-info btn-sm" href="#" mce_href="#" title="附件" onclick="getFile(\''
                                + row.file
                                + '\')"><i class="fa fa-ticket"></i></a> ';
                            let f = '<a class="btn btn-primary btn-sm" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.reimburseId
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm " href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.reimburseId
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            if (row.isDraft === 1) {
                                return f + e + d;
                            } else {
                                return e + d;
                            }
                        }
                    }]
            });
}

function reset() {
    $("#startDate").val();
    $("#endDate").val();
    $("#searchId").val();
    reLoad();
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function add() {
    top.layer.open({
        type: 2,
        title: '增加',
        maxmin: true,
        shadeClose: false,
        area: ['1000px', '650px'],
        content: prefix + '/add',
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
        shadeClose: false,
        area: ['1000px', '650px'],
        content: prefix + '/edit/' + id,
        success: function (layer, index) {
            top.layerParent = window;
        }
    });
}

function getFile(file) {
    if (file === 'null' || file === '') {
        layer.msg('没有附件')
    } else {
        location.href = prefix + '/file/' + file;
    }
}

function detail(id) {
    top.layer.open({
        type: 2,
        title: '详情',
        maxmin: true,
        shadeClose: false,
        area: ['1000px', '650px'],
        content: prefix + '/detail/' + id,
        success: function (layer, index) {
            top.layerParent = window;
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
                'reimburseId': id
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
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function (i, row) {
            ids[i] = row['reimburseId'];
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