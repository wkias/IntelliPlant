var prefix = "/warehouseManagement/purchaseOrder";
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
                pageNumber: 1, showColumns: false,
                sidePagination: "server",
                queryParams: function (params) {
                    return {
                        limit: params.limit,
                        offset: params.offset
                    };
                },
                onDblClickRow: function (row, $element) {
                    details(row.purchaseId);
                },
                columns: [
                    {
                        field: 'purchaseStateName',
                        title: '采购状态'
                    },
                    {
                        field: 'orderCode',
                        title: '订单编号'
                    },
                    {
                        field: 'applyDeptName',
                        title: '申请采购部门'
                    },
                    {
                        field: 'businessTypeName',
                        title: '业务类型'
                    },
                    {
                        field: 'arrivalDate',
                        title: '请求到货日期'
                    },
                    {
                        field: 'dutyPersonName',
                        title: '采购负责人'
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
    let index = parent.layer.getFrameIndex(window.name);
    parent.document.getElementById("associatedTableId").setAttribute("value", row.purchaseId);
    parent.document.getElementById("associatedTableCode").setAttribute("value", row.orderCode);
    parent.document.getElementById("consigneeId").setAttribute("value", row.supplierId);
    parent.document.getElementById("consignee").setAttribute("value", row.supplier);
    parent.document.getElementById("businessType").setAttribute("value", row.businessType);
    parent.document.getElementById("businessTypeName").setAttribute("value", row.businessTypeName);
    parent.layer.close(index);
}
