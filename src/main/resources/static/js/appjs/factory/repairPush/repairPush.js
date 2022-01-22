var prefix = "/factory/repairPush";
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
                url: prefix + "/list",
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true,
                dataType: "json",
                pagination: true,
                checkout: false,
                pageSize: 10,
                pageNumber: 1,
                showColumns: false,
                sidePagination: "server",
                queryParams: function (params) {
                    return {
                        limit: params.limit,
                        offset: params.offset,
                        name: '%' + $('#searchName').val() + '%',
                        equipmentType: $('#searchType').val(),
                        code: $('#searchCode').val(),
                        date: $('#searchDate').val(),
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
                    }, {
                        field: 'purchaseDate',
                        title: '购买日期'
                    },
                    {
                        field: 'lastRepairDate',
                        title: '最后维修日期'
                    },
                    {
                        field: 'state',
                        title: '状态',
                        align: 'center',
                        formatter: function (value, row, index) {
                            if (value === 0)
                                return '<button type="button" class="btn  btn-danger btn-sm"\n' +
                                    '<i class="fa fa-crosshairs" aria-hidden="true"></i>待维保\n' +
                                    '</button>';
                            else if (value === 1)
                                return '<button type="button" class="btn  btn-default btn-sm"\n' +
                                    '<i class="fa fa-crosshairs" aria-hidden="true"></i>维保中\n' +
                                    '</button>';
                            else
                                return '<button type="button" class="btn  btn-primary btn-sm"\n' +
                                    '<i class="fa fa-crosshairs" aria-hidden="true"></i>已维保\n' +
                                    '</button>';
                        }
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="确认维保" onclick="affirm(\''
                                + row.pushInfoId
                                + '\')"><i class="fa fa-wrench"></i>确认维保</a> ';
                            var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.pushInfoId
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                                + row.pushInfoId
                                + '\')"><i class="fa fa-key"></i></a> ';
                            return e + d;
                        }
                    }]
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function reset() {
    $('#searchName').val("");
    $('#searchCode').val("");
    $('#searchType').val('');
    $('#exampleTable').bootstrapTable('refresh');
}

function affirm(id) {
    top.layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false,
        area: ['800px', '350px'],
        content: prefix + '/affirm/' + id,
        success: function (layer, index) {
            top.layerParent = window;
        }
    });
}