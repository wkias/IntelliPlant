var prefix = "/factory/custermerInformation";
$(function () {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list", // 服务器数据的加载地址
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                singleSelect: false, // 设置为true将禁止多选
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams: function (params) {
                    return {
                        limit: params.limit,
                        offset: params.offset,
                        custermerName: $('#custermerName').val(),
                    };
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        visible:false,
                        field : 'custermerId',
                        title : '客户ID'
                    },
                    {
                        field : 'custermerName',
                        title : '客户名称（全称）'
                    },
                    {
                        field: 'custermerName',
                        title: '收款账户'
                    },
                    {
                        field: 'bankAccount',
                        title: '银行账户'
                    },
                    {
                        field: 'bank',
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

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function select(row) {
    var layerParent = top.layerParent;
    let index = top.layer.getFrameIndex(window.name); //获取窗口索引
    layerParent.$("#collectionAccount").val(row.custermerName);
    layerParent.$("#bankAccount").val(row.bankAccount);
    layerParent.$("#bankName").val(row.bank);
    layerParent.$('#exampleTable').bootstrapTable('load', row.details);
    top.layer.close(index);
}