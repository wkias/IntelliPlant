var prefix = "/productManage/productDetail"
$(function () {
    loadDicts();
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                onDblClickRow: function (row, $element) {//双击事件
                    // if(s_details_h!='hidden'){
                    details(row.productId);
                    // }
                },
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset,
                        productName: $('#searchProductName').val(),
                        productType: $('#searchType').val(),
                        model: $('#searchModel').val()

                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        visible: false,
                        field: 'productId',
                        title: '产品ID'
                    },
                    {
                        field: 'productName',
                        title: '产品名称'
                    },
                    {
                        visible: false,
                        field: 'productType',
                        title: '产品类型'
                    }, {
                        field: 'productTypeName',
                        title: '产品类型'
                    },
                    {
                        field: 'model',
                        title: '规格'
                    },
                    {
                        field: 'gaugeValue',
                        title: '规值'
                    },
                    {
                        field: 'material',
                        title: '材料'
                    },
                    {
                        field: 'size',
                        title: '尺寸'
                    },

                    {
                        visible: false,
                        field: 'quantityUnit',
                        title: '数量单位'
                    },
                    {
                        field: 'quantityUnitName',
                        title: '数量单位'
                    },
                    {
                        visible: false,
                        field: 'weightUnit',
                        title: '重量单位'
                    },
                    {
                        field: 'weightUnitName',
                        title: '重量单位'
                    },
                    {
                        visible: false,
                        field: 'remark',
                        title: '备注'
                    },
                    {
                        field: 'weight',
                        title: '单重'
                    },
                    {
                        field: 'price',
                        title: '单价'
                    },
                    {
                        visible: false,
                        field: 'files',
                        title: '附件'
                    },
                    {
                        visible: false,
                        field: 'isDeleted',
                        title: '删除标记'
                    },
                    {
                        visible: false,
                        field: 'creatUserId',
                        title: '创建人'
                    },
                    {
                        visible: false,
                        field: 'creatDate',
                        title: '创建时间'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter : function(value, row, index) {
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
    var index = layerParent.top.layer.getFrameIndex(window.name);

    layerParent.loadProduct(row);
    // 获取窗口索引
    // let index = parent.layer.getFrameIndex(window.name); //获取窗口索引rn fmt;
    // layerParent.document.getElementById("productId").setAttribute("value", row.productId);
    // layerParent.document.getElementById("productName").setAttribute("value", row.productName);
    // layerParent.document.getElementById("model").setAttribute("value", row.model);
    // layerParent.document.getElementById("quantityUnit").setAttribute("value", row.quantityUnit);
    // layerParent.document.getElementById("quantityUnitName").setAttribute("value", row.quantityUnitName);
    layerParent.top.layer.close(index);
}
function reset() {
    $('#searchProductName').val("");
    $('#searchType').val("");
    $('#searchModel').val("");
    $('#exampleTable').bootstrapTable('refresh');
}

function downloadFile(fileName) {
    if (fileName != null && fileName != "") {
        var url = "/factory/equipmentManage/files/" + fileName;
        window.location = url;
    } else {
        layer.msg("附件为空");
    }
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

function add() {
    // window.open( prefix + '/add',"添加","top=100,left=100,height=900,width=900,location=no")

    top.layer.open({
        type: 2,
        title: '增加物品明细',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['1000px', '600px'],
        content: prefix + '/add', // iframe的url
        success: function (layero, index) {
            top.layerParent = window
        }
    });
}

function details(id) {
    top.layer.open({
        type: 2,
        title: '查看物品明细',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['1000px', '600px'],
        content: prefix + '/details/' + id, // iframe的url
        success: function (layero, index) {
            top.layerParent = window
        }
    });
    // window.open( prefix + '/details/'+id,"产品详情","top=100,left=100,height=900,width=900,location=no")
}

function edit(id) {
    // window.open( prefix + '/edit/'+id,"添加","top=100,left=100,height=900,width=900,location=no")

    top.layer.open({
        type: 2,
        title: '编辑物品明细',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['1000px', '600px'],
        content: prefix + '/edit/' + id, // iframe的url
        success: function (layero, index) {
            top.layerParent = window
        }
    });
}

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'productId': id
            },
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}

function resetPwd(id) {
}

function exportFile() {


    var searchProductName = $('#searchProductName').val();
    var searchType = $('#searchType').val();
    var searchModel = $('#searchModel').val();
    location.href = prefix + "/exportFile" + "?searchProductName=" + searchProductName + "&searchType=" + searchType + "&searchModel="
        + searchModel;
}

function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        var ids = [];
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function (i, row) {
            ids[i] = row['productId'];
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: prefix + '/batchRemove',
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function () {

    });
}