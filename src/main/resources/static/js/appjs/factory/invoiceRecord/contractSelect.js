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
                        contractCode: $('#contractCode').val(),
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
                        field: 'typeName',
                        title: '合同类型'
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
    layerParent.$("#contractNumber").val(row.contractCode);
    layerParent.$("#contractName").val(row.contractName);
    layerParent.$("#invoiceMoney").val(row.contractAmount);
    layerParent.$("#contractType").val(row.typeName).change() ;
    layerParent.$('#exampleTable').bootstrapTable('load', row.details);
    top.layer.close(index);
}