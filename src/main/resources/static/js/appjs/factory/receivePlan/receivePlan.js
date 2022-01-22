var prefix = "/factory/receivePlan";
$(function () {
    load();
    loadDict();
});

function loadDict() {
    $.ajax({
        url: '/common/dict/list/receive_state',
        success: function (data) {
            var html = "";
            html += "<option value=''>全部</option>";
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
                    let code = $('#searchCode').val();
                    let name = $('#searchName').val();
                    let demander = $('#searchDemander').val();
                    if ($('#searchCode').val() != "") {
                        code = '%' + $('#searchCode').val() + '%'
                    }
                    if ($('#searchName').val() != "") {
                        name = '%' + $('#searchName').val() + '%';
                    }
                    if ($('#searchDemander').val() != "") {
                        demander = '%' + $('#searchDemander').val() + '%';
                    }
                    return {
                        limit: params.limit,
                        offset: params.offset,
                        contractCode: code,
                        contractName: name,
                        demanderName: demander,
                        state: $("#state").val(),
                    };
                },
                onDblClickRow: function (row, $element) {
                    detail(row.receiveId);
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'contractName',
                        title: '合同名称',
                        cellStyle: function (value, row, index) {
                            return {
                                css: {
                                    "white-space": "nowrap",
                                    "text-overflow": "ellipsis",
                                    "overflow": "hidden",
                                    "max-width": "200px"
                                }
                            }
                        },
                        formatter: function (value, row, index) {
                            var span = document.createElement("span");
                            span.setAttribute("title", value);
                            span.innerHTML = value;
                            return span.outerHTML;
                        }
                    },
                    {
                        field: 'customer',
                        title: '合作商',
                        cellStyle: function (value, row, index) {
                            return {
                                css: {
                                    "white-space": "nowrap",
                                    "text-overflow": "ellipsis",
                                    "overflow": "hidden",
                                    "max-width": "200px"
                                }
                            }
                        },
                        formatter: function (value, row, index) {
                            var span = document.createElement("span");
                            span.setAttribute("title", value);
                            span.innerHTML = value;
                            return span.outerHTML;
                        }
                    },
                    {
                        field: 'stateString',
                        title: '计划回款状态',
                        align: 'center',
                        formatter: function (value, row, index) {
                            if (row.stateString === "已完成")
                                return '<a class="btn btn-primary btn-sm" title="已完成">已完成</a>';
                            else if (row.stateString === "异常")
                                return '<a class="btn btn-default btn-sm" title="异常">异常</a>';
                            else if (row.stateString === "未完成")
                                return '<a class="btn btn-danger btn-sm" title="未完成">未完成</a>';
                            else return row.stateString;
                        }
                    },

                    {
                        field: 'receiveBatchName',
                        title: '回款期次'
                    },
                    {
                        field: 'plannedAmount',
                        title: '计划回款金额'
                    },
                    {
                        field: 'plannedDate',
                        title: '计划回款日期'
                    },
                    {
                        field: 'receivedAmount',
                        title: '已回款金额/未回款金额',
                        formatter: function (value, row) {
                            return row.receivedAmount + "/" + row.unreceivedAmount;
                        }
                    },
                    {
                        field: 'daysOverdue',
                        title: '逾期天数'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.receiveId
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm ' + s_viewLog_h + '" href="#" title="记录"  mce_href="#" onclick="viewLog(\''
                                + row.contractCode
                                + '\')"><i class="fa fa-info"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm"' + s_attachment_h + ' href="#" title="附件"  mce_href="#" onclick="attachment(\''
                                + row.receiveId
                                + '\')"><i class="fa fa-clone"></i></a> ';
                            return e + d;
                        }
                    }]
            });
}

function detail(id) {
    top.layer.open({
        type: 2,
        title: '详情',
        maxmin: true,
        shadeClose: false,
        area: ['800px', '520px'],
        content: prefix + '/detail/' + id,
        success: function (layer, index) {
            top.layerParent = window;
        }
    });
}

function exportFile() {
    let url = prefix + "/export";
    let opt = {
        query: {
            contractCode: $('#searchCode').val(),
            contractName: $('#searchName').val(),
            state: $("#state").val(),
        }
    };
    if (JSON.stringify(opt) !== '{}') {
        url += "?contractCode=" + opt.query.contractCode + "&contractName=" + opt.query.contractName + "&state=" + opt.query.state;
        console.log("url:" + url);
    }
    location.href = url;
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function reset() {
    $('#searchId').val('');
    $('#searchName').val('');
    $('#state').val('');
    $('#searchDemander').val('');
    $('#exampleTable').bootstrapTable('refresh');
}

function viewLog(contractCode) {
    top.layer.open({
        type: 2,
        title: '记录',
        maxmin: true,
        shadeClose: false,
        area: ['800px', '520px'],
        content: prefix + '/receiveRecord/' + contractCode
    });
}

function attachment() {
    top.layer.open({
        type: 2,
        title: '附件',
        maxmin: true,
        shadeClose: false,
        area: ['800px', '520px'],
        content: prefix + '/attachment'
    });
}

function add() {
    top.layer.open({
        type: 2,
        title: '添加回款计划',
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
        title: '编辑回款计划',
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
                'receiveId': id
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
            ids[i] = row['receiveId'];
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