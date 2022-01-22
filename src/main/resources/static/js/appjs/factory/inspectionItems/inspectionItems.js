var prefix = "/factory/inspectionItems";
$(function () {
    load();
    loadDict();
});

function loadDict() {
    $.ajax({
        url: '/common/dict/list/inspection_item_state',
        success: function (data) {
            var html = "";
            html += "<option value=''>状态</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#state").append(html);
            $("#state").val('');
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
                method: 'get',
                url: prefix + "/list", iconSize: 'outline',
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
                        projectName: '%' + $('#searchName').val() + '%',
                        state: $('#state').val(),
                    };
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'projectName',
                        title: '项目名称'
                    },
                    {
                        field: 'valueTypeName',
                        title: '值类型'
                    },
                    {
                        field: 'unitName',
                        title: '单位'
                    },
                    {
                        field: 'rangeThreshold',
                        title: '范围阈值'
                    },
                    {
                        field: 'description',
                        title: '说明'
                    },
                    {
                        field: 'state',
                        title: '状态',
                        align: 'center',
                        formatter: function (value, row, index) {
                            if (value == 0)
                                return '<button type="button" class="btn  btn-danger btn-sm"\n' +
                                    '<i class="fa fa-crosshairs" aria-hidden="true"></i>已停用\n' +
                                    '</button>';
                            else
                                return '<button type="button" class="btn  btn-primary btn-sm"\n' +
                                    '<i class="fa fa-crosshairs" aria-hidden="true"></i>已启用\n' +
                                    '</button>';
                        }
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            let title = '';
                            if (row.state === '1') {
                                title = '停用';
                            } else {
                                title = '启用';
                            }
                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.inspectionItemsId
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm ' + s_edit_h + '" href="#" title="' + title + '"  mce_href="#" ' +
                                'onclick="switchh(\'' + row.inspectionItemsId + '\',\'' + title + '\')"><i class="fa fa-ticket"></i></a> ';
                            return e + d;
                        }
                    }]
            });
}

function switchh(id, title) {
    let content = '确定要' + title + '选中的记录？';
    layer.confirm(content, {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/switch",
            type: "post",
            data: {
                'inspectionItemsId': id
            },
            success: function (r) {
                if (r.code === 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function reset() {
    $("#searchName").val("");
    $("#state").val('');
    $("#exampleTable").bootstrapTable('refresh');
}

function add() {
    top.layer.open({
        type: 2,
        title: '添加质检项目',
        maxmin: true,
        shadeClose: false,
        area: ['800px', '520px'],
        content: prefix + '/add',
        success: function (layer, index) {
            top.layerParent = window;
        }
    });
}

function edit(id) {
    top.layer.open({
        type: 2,
        title: '编辑质检项目',
        maxmin: true,
        shadeClose: false,
        area: ['800px', '520px'],
        content: prefix + '/edit/' + id,
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
                'inspectionItemsId': id
            },
            success: function (r) {
                if (r.code === 0) {
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
    if (rows.length === 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
    }, function () {
        var ids = [];
        $.each(rows, function (i, row) {
            ids[i] = row['inspectionItemsId'];
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: prefix + '/batchRemove',
            success: function (r) {
                if (r.code === 0) {
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