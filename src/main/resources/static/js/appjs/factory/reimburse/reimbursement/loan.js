var prefix = "/reimburse/loanApplication"
$(function () {
    load();
});

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
                pageNumber: 1,
                showColumns: false,
                sidePagination: "server",
                queryParams: function (params) {
                    return {
                        limit: params.limit,
                        offset: params.offset,
                        queryAll: "0",
                    };
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        visible: false,
                        field: 'loanApplicationId',
                        title: '借款申请ID'
                    },
                    {
                        visible: false,
                        field: 'bankAccountId',
                        title: '银行账户ID'
                    },
                    {
                        visible: false,
                        field: 'userId',
                        title: '借款人ID'
                    },
                    {
                        field: 'userName',
                        title: '借款人'
                    },
                    {
                        field: 'loanState',
                        title: '单据状态'
                    },
                    {
                        visible: false,
                        field: 'loanCode',
                        title: '单据编号'
                    },
                    {
                        field: 'loanPurpose',
                        title: '事由'
                    },
                    {
                        field: 'loanDate',
                        title: '申请时间'
                    }, {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            return "<a class='btn btn-primary btn-sm' title='选择' onclick='select("
                                + JSON.stringify(row)
                                + ")'><i class='fa fa-ticket'></i></a> ";
                        }
                    }]
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function select(row) {
    parent.loadLoan(row);
    parent.layer.close(parent.layer.getFrameIndex(window.name));
}

function reset() {
    $("#searchName").val();
    $('#exampleTable').bootstrapTable('refresh');
}
