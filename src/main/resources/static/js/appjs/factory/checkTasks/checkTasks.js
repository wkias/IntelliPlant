var prefix = "/factory/checkTasks";
$(function () {
    load();
    loadDict();
});

function loadDict() {
    $.ajax({
        url: '/common/dict/list/equipment_type',
        success: function (data) {
            var html = "";
            html += "<option value=''>设备类型</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#searchType").append(html);
            $("#searchType").val('');
            $("#searchType").chosen({
                maxHeight: 200
            });
            $("#searchType").trigger("chosen:updated");
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
                    let va = 0;
                    if (params.valueOf("viewAll") === true) {
                        va = 1;
                    }
                    return {
                        limit: params.limit,
                        offset: params.offset,
                        name: '%' + $('#searchName').val() + '%',
                        equipmentType: $('#searchType').val(),
                        code: $('#searchCode').val(),
                        date: $('#searchDate').val(),
                        viewAll: va,
                    };
                },
                columns: [
                    {
                        field: 'equipmentType',
                        title: '设备类型'
                    }, {
                        field: 'code',
                        title: '设备编码'
                    }, {
                        field: 'name',
                        title: '设备名称'
                    },
                    {
                        field: 'date',
                        title: '点检日期'
                    },
                    {
                        field: 'deadline',
                        title: '点检截止时间'
                    },
                    {
                        field: 'checkerName',
                        title: '点检人'
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
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm ' + s_check_h + '" href="#" mce_href="#" title="点检" onclick="check(\''
                                + row.checkTaskId
                                + '\')"><i class="fa fa-edit"></i>点检</a> ';
                            var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.checkTaskId
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                                + row.checkTaskId
                                + '\')"><i class="fa fa-key"></i></a> ';
                            return e;
                        }
                    }]
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function viewAll() {
    let opt = {
        query: {
            viewAll: true
        }
    };
    $('#exampleTable').bootstrapTable('refresh', opt);
}

function reset() {
    $('#searchName').val("");
    $('#searchCode').val("");
    $('#searchType').val('');
    $('#searchDate').val("");
    $('#exampleTable').bootstrapTable('refresh');
}

function check(checkTaskId) {
    top.layer.open({
        type: 2,
        title: '点检',
        maxmin: true,
        shadeClose: false,
        area: ['800px', '600px'],
        content: prefix + '/check/' + checkTaskId,
        success: function (layer, index) {
            top.layerParent = window;
        }
    });
}
