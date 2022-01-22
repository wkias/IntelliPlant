let prefix = "/factory/godownEntry";
$(function () {
    loadDict();
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get',
                url: prefix + "/list",
                async: false,
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
                onDblClickRow: function (row, $element) {
                    if (detail_h === '') {
                        detail(row.godownEntryId);
                    }
                },
                queryParams: function (params, opt) {
                    let godownEntryCode = $('#searchCode').val();
                    let consignee = $('#searchC').val();
                    if (godownEntryCode !== "") {
                        godownEntryCode = '%' + godownEntryCode + '%'
                    }
                    if (consignee !== "") {
                        consignee = '%' + consignee + '%';
                    }
                    return {
                        limit: params.limit,
                        offset: params.offset,
                        godownEntryCode: godownEntryCode,
                        godownEntryDate: $('#searchDate').val(),
                        repository: $('#searchRepo').val(),
                        businessType: $('#searchBType').val(),
                        consignee: consignee,
                        sort: "create_time",
                        order: "desc",
                    };
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'godownEntryStateName',
                        title: '入库状态'
                    },
                    {
                        field: 'godownEntryCode',
                        title: '入库单编号'
                    },
                    {
                        field: 'godownEntryDate',
                        title: '入库日期'
                    },
                    {
                        field: 'businessTypeName',
                        title: '业务类型'
                    },
                    {
                        field: 'consignee',
                        title: '发货单位'
                    },
                    {
                        field: 'amount',
                        title: '总金额'
                    },
                    {
                        field: 'repositoryName',
                        title: '仓库'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.godownEntryId
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-info btn-sm ' + s_remove_h + '" href="#" title="附件"  mce_href="#" onclick="attachment(\''
                                + row.file
                                + '\')"><i class="fa fa-ticket"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm ' + s_resetPwd_h + '" href="#" title="物流台账"  mce_href="#" onclick="account(\''
                                + row.godownEntryId
                                + '\')"><i class="fa fa-key"></i></a> ';
                            return e + d + f;
                        }
                    }]
            });
}

function account(id) {
    top.layer.open({
        type: 2,
        title: '物流台账',
        maxmin: true,
        shadeClose: false,
        area: ['800px', '520px'],
        content: prefix + '/account/' + id,
        success: function (layero, index) {
            top.layerParent = window
        }
    })
}

function attachment(file) {
    if (file === 'null' || file === '') {
        layer.msg('没有附件')
    } else {
        location.href = prefix + '/file/' + file;
    }
}

function detail(id) {
    top.layer.open({
        type: 2,
        title: '入库单详情',
        maxmin: true,
        shadeClose: false,
        area: ['1000px', '700px'],
        content: prefix + '/detail/' + id,
        success: function (layero, index) {
            top.layerParent = window
        }
    })
}

function loadDict() {
    $.ajax({
        url: '/common/dict/list/purchase_business_type',
        success: function (data) {
            var html = "";
            html += "<option value=''>业务类型</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#searchBType").append(html);
            $("#searchBType").chosen({
                maxHeight: 200
            });
            $("#searchBType").trigger("chosen:updated");
        }
    });
}

function exportFile() {
    let url = prefix + "/export";
    let opt = {
        query: {
            godownEntryCode: $('#searchCode').val(),
            godownEntryDate: $('#searchDate').val(),
            repository: $('#searchRepo').val(),
            businessType: $('#searchBType').val(),
            consignee: $('#searchC').val()
        }
    };
    if (JSON.stringify(opt) !== '{}') {
        url += "?godownEntryCode=" + opt.query.godownEntryCode +
            "&godownEntryDate=" + opt.query.godownEntryDate +
            "&repository=" + opt.query.repository +
            "&businessType=" + opt.query.businessType +
            "&consignee=" + opt.query.consignee;
        console.log("url:" + url);
    }
    location.href = url;
}

function reset() {
    $('#searchCode').val("");
    $('#searchDate').val("");
    $('#searchRepo').val("");
    $('#searchBType').val("");
    $('#searchC').val("");
    $('#exampleTable').bootstrapTable('refresh');
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function add() {
    top.layer.open({
        type: 2,
        title: '添加入库单',
        maxmin: true,
        shadeClose: false,
        area: ['1000px', '700px'],
        content: prefix + '/add',
        success: function (layero, index) {
            top.layerParent = window
        }
    })
}

function edit(id) {
    top.layer.open({
        type: 2,
        title: '编辑入库单',
        maxmin: true,
        shadeClose: false,
        area: ['1000px', '700px'],
        content: prefix + '/edit/' + id,
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
                'godownEntryId': id
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
    if (rows.length === 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
    }, function () {
        var ids = [];
        $.each(rows, function (i, row) {
            ids[i] = row['godownEntryId'];
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