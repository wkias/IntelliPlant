var prefix = "/productManage/productDetail";
$(function () {
    load();
    loadDicts();
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
                        productName: $('#searchProductName').val(),
                        productType: $('#searchType').val(),
                        model: $('#searchModel').val()
                    };
                },
                columns: [
                    {
                        field: 'productName',
                        title: '产品名称'
                    },
                    {
                        field: 'productTypeName',
                        title: '产品类型'
                    },
                    {
                        field: 'model',
                        title: '规格'
                    },
                    {
                        field: 'size',
                        title: '尺寸'
                    },
                    {
                        field: 'quantityUnitName',
                        title: '数量单位'
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

function loadDicts() {
    $.ajax({
        url: '/common/dict/list/product_type',
        success: function (data) {
            var html = "";
            // 加载数据
            html += "<option value=''>所有</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#searchType").append(html);
            $("#searchType").chosen({
                maxHeight: 200
            });
            $("#searchType").trigger("chosen:updated");
        }
    });
}

function reset() {
    $('#searchProductName').val("");
    $('#searchType').val("");
    $('#searchModel').val("");
    $('#exampleTable').bootstrapTable('refresh');
}

function select(row) {
    parent.loadProduct(row);
    let index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}