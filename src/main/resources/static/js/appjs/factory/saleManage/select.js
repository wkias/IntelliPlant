
$(function() {
    load();
});
function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : "/contractManage/orderContent/listByComplete", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize : 10, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns : false, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams : function(params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        orderId:$('#orderId').val(),


                        // name:$('#searchName').val(),
                        // username:$('#searchName').val()
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns : [
                    {
                        checkbox : true
                    },
                    {
                        visible:false,
                        field : 'contentId',
                        title : '订单内容ID'
                    },
                    {
                        visible:false,
                        field : 'productId',
                        title : '产品ID'
                    },
                    {

                        field : 'productDefinitionDO.productName',
                        title : '产品名称'
                    },

                    {

                        field : 'productDefinitionDO.productModel',
                        title : '产品型号'
                    },
                    {

                        field : 'productDefinitionDO.unitName',
                        title : '单位'
                    },
                    {
                        field : 'count',
                        title : '数量'
                    },
                    {
                        field : 'totalSum',
                        title : '总金额'
                    },
                    {
                        field : 'deadline',
                        title : '到货日期'
                    },
                    {
                        field : 'destination',
                        title : '目的地'
                    },
                    {
                        field : 'storehouse',
                        title : '仓库'
                    },
                    {
                        visible:false,
                        field : 'orderId',
                        title : '订单id'
                    },
                    {
                        visible:false,
                        field : 'isBatched',
                        title : '是否分批次  0 不分 '
                    },
                    {
                        field : 'complete',
                        title : '已发货数'
                    } ]
            });
}
function select(row) {
    // let index = parent.layer.getFrameIndex(window.name); //获取窗口索引rn fmt;
    // parent.document.getElementById("orderId").setAttribute("value", row.orderId);
    // parent.document.getElementById("orderCode").setAttribute("value", row.orderCode);
    // parent.document.getElementById("orderName").setAttribute("value", row.orderName);
    // parent.document.getElementById("custermerId").setAttribute("value", row.demandId);
    // parent.document.getElementById("custermerName").setAttribute("value", row.demandName);
    // let id = row.demandId;
    // $.ajax({
    //     catch:true,
    //     method : 'get',
    //     url : "/factory/custermerInformation/getDo/"+id,
    //     dataType : "json",
    //     async : false,
    //     data : {
    //     },
    //     success : function(r) {
    //         console.log(r)
    //         console.log(r.custermerId);
    //         console.log(r.name);
    //         parent.document.getElementById("contact").setAttribute("value", r.name==null?"":r.name);
    //         parent.document.getElementById("contactTel").setAttribute("value", r.contactPersonTel==null?"":r.contactPersonTel);
    //
    //     }
    // });
    //
    // parent.layer.close(index);
}
function reLoad() {

    $('#exampleTable').bootstrapTable('refresh');
}

function batchSelect() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要添加的数据");
        return;
    }
    layer.confirm("确认要添加选中的'" + rows.length + "'条数据吗?", {
        btn : [ '确定', '取消' ]
        // 按钮
    }, function() {
        console.log(rows[0].orderId+"   id");
        var layerParent = top.layerParent;
        layerParent.getSelect(rows);
        var index = layerParent.top.layer.getFrameIndex(window.name);
        layerParent.top.layer.close(index);
        // var ids = new Array();
        // // 遍历所有选择的行数据，取每条数据对应的ID
        // $.each(rows, function(i, row) {
        //     ids[i] = row['orderId'];
        // });
        // $.ajax({
        //     type : 'POST',
        //     data : {
        //         "ids" : ids
        //     },
        //     url : prefix + '/batchRemove',
        //     success : function(r) {
        //         if (r.code == 0) {
        //             layer.msg(r.msg);
        //             reLoad();
        //         } else {
        //             layer.msg(r.msg);
        //         }
        //     }
        // });
    }, function() {

    });
}