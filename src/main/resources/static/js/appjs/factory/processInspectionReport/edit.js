let prefix = "/productManage/processInspectionReport";
$().ready(function () {
    validateRule();
    load();
});
$.validator.setDefaults({
    submitHandler: function () {
        update();
    }
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get',
                url: prefix + "/listItems", iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true,
                dataType: "json",
                pagination: true,
                singleSelect: false,
                pageSize: 10,
                pageNumber: 1, showColumns: false,
                sidePagination: "server",
                queryParams: function (params) {
                    return {
                        limit: params.limit,
                        offset: params.offset,
                        processInspectionReportId: $('#processInspectionReportId').val()
                    };
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'processName',
                        title: '工序名称'
                    },
                    {
                        field: 'componentName',
                        title: '部件名称'
                    },
                    {
                        field: 'unit',
                        title: '数量单位'
                    },
                    {
                        field: 'qualifiedNum',
                        title: '合格/返工/报废',
                        formatter: function (value, row, index) {
                            return '<a class="btn btn-primary btn-sm" title="合格">' + row.qualifiedNum + '</a>' +
                                '<a class="btn btn-info btn-sm" title="返工">' + row.reworkNum + '</a>' +
                                '<a class="btn btn-danger btn-sm" title="报废">' + row.scrapNum + '</a>';
                        }
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            let a = '<a class="btn btn-primary btn-sm" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.processInspectionDetailId
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            let b = '<a class="btn btn-danger btn-sm" href="#" mce_href="#" title="删除" onclick="remove(\''
                                + row.processInspectionDetailId
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            return a + b;
                        }
                    }]
            });
}

function addItemm() {
    layer.open({
        type: 2,
        title: '添加',
        maxmin: true,
        shadeClose: false,
        area: ['1000px', '600px'],
        content: prefix + '/addItem'
    });
}

function edit(id) {
    layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false,
        area: ['1000px', '600px'],
        content: prefix + '/editItem/' + id
    });
}

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/removeItem",
            type: "post",
            data: {
                'processInspectionDetailId': id
            },
            success: function (r) {
                if (r.code === 0) {
                    layer.msg(r.msg);
                    $('#exampleTable').bootstrapTable('refresh');
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}

function batchRemoveItems() {
    var rows = $('#exampleTable').bootstrapTable('getSelections');
    if (rows.length === 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
    }, function () {
        let ids = [];
        $.each(rows, function (i, row) {
            ids[i] = row['processInspectionDetailId'];
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: prefix + '/batchRemoveItems',
            success: function (r) {
                if (r.code === 0) {
                    layer.msg(r.msg);
                    $('#exampleTable').bootstrapTable('refresh');
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function () {
    });
}

function update() {
    $.ajax({
        cache: true,
        type: "POST",
        url: "/productManage/processInspectionReport/update",
        data: $('#signupForm').serialize(),
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code === 0) {
                parent.layer.msg("操作成功");
                window.opener.reLoad();
                window.close();
            } else {
                parent.layer.alert(data.msg)
            }
        }
    });
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            name: {
                required: true
            }
        },
        messages: {
            name: {
                required: icon + "请输入名字"
            }
        }
    })
}