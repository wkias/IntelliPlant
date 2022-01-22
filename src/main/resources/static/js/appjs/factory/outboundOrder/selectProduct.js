var prefix = "/factory/outboundOrder/listProduct/"
$(function () {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get',
                url: prefix + parent.$("#associatedTableId").val(),
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
                    };
                },
                columns: [
                    {
                        field: 'productCode',
                        title: '产品编号'
                    }, {
                        field: 'productName',
                        title: '产品名称'
                    },
                    {
                        field: 'productModel',
                        title: '型号'
                    },
                    {
                        field: 'count',
                        title: '待发数量'
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
    parent.loadProduct(row);
    let index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}