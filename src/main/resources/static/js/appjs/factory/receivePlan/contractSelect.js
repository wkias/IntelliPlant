var prefix = "/factory/contractManage";
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
                        contractCode: $('#searchCode').val(),
                        contractName: $('#searchName').val()
                    };
                },
                columns: [
                    {
                        field: 'contractCode',
                        title: '编号'
                    },
                    {
                        field: 'contractName',
                        title: '名称'
                    },
                    {
                        field: 'contractAmount',
                        title: '总金额'
                    },
                    {
                        field: 'dutyUser',
                        title: '负责人',
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            return "<a class='btn btn-primary btn-sm' title='选择' onclick='select("
                                + JSON.stringify(row)
                                + ")'><i class='fa fa-ticket'></i>选择</a> ";
                        }
                    }]
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function reset() {
    $('#searchId').val("");
    $('#searchName').val("");
    $('#exampleTable').bootstrapTable('refresh')
}

function details(id) {
    parent.layer.open({
        type: 2,
        tile: "详情",
        maxmin: true,
        shadeClose: false,
        area: ['800px', '520px'],
        content: prefix + '/details/' + id
    })
}

function select(row) {
    let index = parent.layer.getFrameIndex(window.name);
    parent.document.getElementById("contractId").setAttribute("value", row.contractId);
    parent.document.getElementById("contractCode").setAttribute("value", row.contractCode);
    parent.document.getElementById("contractName").setAttribute("value", row.contractName);
    parent.document.getElementById("contractAmount").setAttribute("value", row.contractAmount);
    if (row.demanders != null) {
        parent.document.getElementById("demander").setAttribute("value", row.demanders);
    }
    parent.document.getElementById("signDate").setAttribute("value", row.signDate);
    parent.loadDemander(row.contractId);
    parent.layer.close(index);
}