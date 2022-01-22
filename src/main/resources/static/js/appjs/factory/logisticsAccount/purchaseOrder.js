var prefix = "/factory/godownEntry";
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
                onDblClickRow: function (row, $element) {//双击事件
                    if (s_details_h != 'hidden') {
                    }
                },
                queryParams: function (params) {
                    return {
                        limit: params.limit,
                        offset: params.offset,
                        associatedTableId: $('#associatedTableId').val()
                    };
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'associatedTableId',
                        title: '表单编号'
                    },
                    {
                        field: 'associatedTableTypeName',
                        title: '表单类型'
                    },
                    {
                        field: 'consignee',
                        title: '发货单位'
                    },
                    {
                        field: 'quantity',
                        title: '总数量'
                    },
                    {
                        field: 'weight',
                        title: '总重量'
                    },
                    {
                        field: 'amount',
                        title: '总金额（元）'
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
    layerParent.$("#formId").val(row.associatedTableId);
    layerParent.$("#formType").val(row.associatedTableType);
    layerParent.$("#formTypeName").val(row.associatedTableTypeName);
    layerParent.$("#forwardingUnit").val(row.consignee);
    layerParent.$("#totalQuantity").val(row.quantity);
    layerParent.$("#totalWeight").val(row.weight);
    layerParent.$("#totalMoney").val(row.amount);
    layerParent.$('#exampleTable').bootstrapTable('load', row.details);
    top.layer.close(index);
}