var prefix = "/productManage/processInspectionReport"
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
                        processName: "%" + $('#searchName').val() + "%",
                        componentName: "%" + $('#searchComponentName').val() + "%",
                        inspectionDate: $('#searchDate').val()
                    };
                },
                onDblClickRow: function (row, $element) {
                    detail(row.processInspectionReportId);
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'inspectionDate',
                        title: '质检日期'
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
                            return '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.processInspectionReportId
                                + '\')"><i class="fa fa-edit"></i></a> ';
                        }
                    }]
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function reset() {
    $("#searchComponentName").val('');
    $("#searchName").val('');
    $("#searchDate").val('');
    $('#exampleTable').bootstrapTable('refresh');
}

function add() {
    window.open(prefix + "/add", '新增', 'location=no')
}

function edit(id) {
    window.open(prefix + "/edit/" + id, '编辑', 'location=no')
}

function detail(id) {
    window.open(prefix + "/detail/" + id, '详情', 'location=no')
}

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'processInspectionReportId': id
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

function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections');
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
    }, function () {
        var ids = [];
        $.each(rows, function (i, row) {
            ids[i] = row['processInspectionReportId'];
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