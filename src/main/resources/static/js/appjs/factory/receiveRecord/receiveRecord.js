var prefix = "/factory/receiveRecord";
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
                    detail(row.recordId);
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'contractName',
                        title: '合同名称'
                    },
                    {
                        field: 'demanderName',
                        title: '合作商',
                    },
                    {
                        field: 'planBatchName',
                        title: '回款期次'
                    },
                    {
                        field: 'receiveAmount',
                        title: '回款金额'
                    },
                    {
                        field: 'date',
                        title: '回款日期'
                    },
                    {
                        field: 'plannedDate',
                        title: '计划回款日期'
                    },
                    {
                        field: 'state',
                        title: '计划回款状态',
                        align: 'center',
                        formatter: function (value, row, index) {
                            if (row.state === "2")
                                return '<a class="btn btn-primary btn-sm" title="已完成">已完成</a>';
                            else if (row.state === "0")
                                return '<a class="btn btn-danger btn-sm" title="未完成">未完成</a>';
                            else return '<a class="btn btn-default btn-sm" title="异常">异常</a>';
                        }
                    },
                    {
                        field: 'payeeName',
                        title: '收款人'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            return '<a class="btn btn-success btn-sm"' + s_attachment_h + ' href="#" title="附件"  mce_href="#" onclick="file(\''
                                + row.file
                                + '\')"><i class="fa fa-clone"></i>附件</a> ';
                        },
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

function file(fileName) {
    if (fileName === "null") {
        layer.msg("没有上传附件");
        return;
    }
    location.href = prefix + "/file/" + fileName;
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
    $('#searchCode').val('');
    $('#searchName').val('');
    $('#state').val('');
    $('#searchDemander').val('');
    $('#exampleTable').bootstrapTable('refresh');
}

function add() {
    top.layer.open({
        type: 2,
        title: '添加回款记录',
        maxmin: true,
        shadeClose: false,
        area: ['1000px', '800px'],
        content: prefix + '/add',
        success: function (layer, index) {
            top.layerParent = window;
        }
    });
}

function edit(id) {
    top.layer.open({
        type: 2,
        title: '编辑回款记录',
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
                'recordId': id
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
            ids[i] = row['recordId'];
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