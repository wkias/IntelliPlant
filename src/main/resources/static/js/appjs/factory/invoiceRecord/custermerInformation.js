var prefix = "/factory/custermerInformation"
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
                        custermerName: $('#custermerName').val(),
                    };
                },
                columns: [
                    {
                        field : 'custermerName',
                        title : '客户名称'
                    },
                    {
                        field : 'taxpayerIdNumbers',
                        title : '纳税人识别号'
                    },
                    {
                        field : 'address',
                        title : '地址'
                    },
                    {
                        field : 'custermerTel',
                        title : '客户电话'
                    },
                    {
                        field : 'bank',
                        title : '开户行'
                    },
                    {
                        field : 'bankAccount',
                        title : '开户行账号'
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

function select(row) {
    var layerParent = top.layerParent;
    let index = top.layer.getFrameIndex(window.name); //获取窗口索引
    layerParent.$("#buyingName").val(row.custermerName);
    layerParent.$("#buyingTin").val(row.taxpayerIdNumbers);
    layerParent.$("#buyingAddress").val(row.address);
    layerParent.$("#buyingPhoneNumber").val(row.custermerTel);
    layerParent.$("#buyingBank").val(row.bank);
    layerParent.$("#buyingBankNumber").val(row.bankAccount);
    layerParent.$('#exampleTable').bootstrapTable('load', row.details);
    top.layer.close(index);
}
