var prefix = "/saleManage/saleManage";
$(function () {
    loadCustermer();
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
                onDblClickRow: function (row, $element) {
                    details(row.saleId);
                },
                queryParams: function (params) {
                    return {
                        limit: params.limit,
                        offset: params.offset,
                        custermerId: $("#custermer").val(),
                        saleDate: $("#saleDate").val(),
                        saleCode: $("#saleCode").val()
                    };
                },
                columns: [
                    {
                        field: 'saleCode',
                        title: '销售发货单编号'
                    },
                    {
                        field: 'saleDate',
                        title: '销售发货日期'
                    }, {
                        field: 'saleStateName',
                        title: '销售发货单状态'
                    },
                    {
                        field: 'custermerName',
                        title: '订货单位'
                    },
                    {
                        field: 'destination',
                        title: '收货地址'
                    },
                    {
                        title: '操作',
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
    parent.document.getElementById("associatedTableId").setAttribute("value", row.saleId);
    parent.document.getElementById("associatedTableCode").setAttribute("value", row.saleCode);
    parent.document.getElementById("consigneeId").setAttribute("value", row.custermerId);
    parent.document.getElementById("consignee").setAttribute("value", row.custermerName);
    parent.document.getElementById("businessType").setAttribute("value", "product_outbound");
    parent.document.getElementById("businessTypeName").setAttribute("value", "产品出库");
    parent.layer.close(index);
}

function loadCustermer() {
    $.ajax({
        url: '/factory/custermerInformation/list',
        type: "GET",
        dateType: "JSON",
        success: function (data) {
            var html = "";
            // 加载数据
            html += "<option value=''>所有</option>";
            for (var i = 0; i < data.total; i++) {
                html += '<option value="' + data.rows[i].custermerId + '">' + data.rows[i].custermerName + '</option>'
            }
            $("#custermer").append(html);
            $("#custermer").chosen({
                maxHeight: 200
            });
            $("#custermer").trigger("chosen:updated");
        }
    });
}
