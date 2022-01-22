var prefix = "/factory/bankAccount"
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
                        offset: params.offset
                    };
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'collectionAccount',
                        title: '收款账户'
                    },
                    {
                        field: 'bankAccount',
                        title: '银行账户'
                    },
                    {
                        field: 'bankName',
                        title: '开户行'
                    },
                    {
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

function select(row) {
    parent.loadAccount(row);
    parent.layer.close(parent.layer.getFrameIndex(window.name));
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function reset() {
    $("#searchName").val();
    $('#exampleTable').bootstrapTable('refresh');
}