let isProduct = -1;
$(function () {
    goods();
});

function goods() {
    isProduct = 0;
    $("button:eq(0)").addClass("btn-primary");
    $("button:eq(1)").removeClass("btn-primary");
    $("button:eq(1)").addClass("btn-file");
    $('#DsearchProductCode').hide();
    $('#DsearchProductName').hide();
    $('#DsearchGoodsName').show();
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get',
                url: "/productManage/productDetail/list",
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
                        productName: $('#searchGoodsName').val(),
                    };
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'productName',
                        title: '物品名称'
                    }, {
                        field: 'productTypeName',
                        title: '物品类型'
                    },
                    {
                        field: 'size',
                        title: '规格'
                    },
                    {
                        field: 'price',
                        title: '单价'
                    },]
            });
}

function product() {
    isProduct = 1;
    $("button:eq(1)").addClass("btn-primary");
    $("button:eq(0)").removeClass("btn-primary");
    $("button:eq(0)").addClass("btn-file");
    $('#DsearchProductCode').show();
    $('#DsearchProductName').show();
    $('#DsearchGoodsName').hide();
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get',
                url: "/productManage/productDefinition/list",
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
                        productCode: $('#searchProductCode').val(),
                        productName: $('#searchProductName').val(),
                    };
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'productName',
                        title: '产品名称'
                    }, {
                        field: 'productCode',
                        title: '产品编号'
                    },
                    {
                        field: 'productModel',
                        title: '规格'
                    },
                    {
                        field: 'price',
                        title: '单价'
                    },]
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function reset() {
    $('#searchProductCode').val("");
    $('#searchProductName').val("");
    $('#searchGoodsName').val("");
    $('#exampleTable').bootstrapTable('refresh')
}

function select() {
    if (isProduct == -1) {
        layer.msg("请选择物品或产品");
        return;
    }
    var rows = $('#exampleTable').bootstrapTable('getSelections');
    if (rows.length == 0) {
        layer.msg("请选择产品");
        return;
    }
    $.each(rows, function (i, row) {
        if (isProduct) {
            parent.loadOtherProduct(row);
        } else {
            parent.loadOtherGoods(row);
        }
    });
    let index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}