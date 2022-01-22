
var prefix = "/factory/orderManage"
$(function() {
    load();
});
function getContractByName() {
    var code = $("#contractName").val();
    if(code!=null&&code!=""){
        $.ajax({
            cache : true,
            type : "GET",
            url : "/factory/contractManage/ajaxList",
            data : {
                'contractName' : code
            },
            dateType:"json",
            async : false,
            error : function(request) {
                parent.layer.alert("Connection error");
            },
            success : function(data) {
                if(data[0]!=null){
                    $("#contractId").val(data[0].contractId);
                }else alert("无此合同名称");


            }
        });
    }else $("#contractId").val(null);
}
function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/list", // 服务器数据的加载地址
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
                onDblClickRow:function (row,$element) {//双击事件
                    // if(s_details_h!='hidden'){
                    details(row.orderId);
                    // }
                },
                queryParams : function(params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset:params.offset,
                        orderCode:$('#orderCode').val(),
                        contractId:$('#contractId').val(),
                        orderName:$('#orderName').val()

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
                        field : 'contractName',
                        title : '所属合同名称'
                    },
                    {
                        visible:false,
                        field : 'orderId',
                        title : '订单ID'
                    },
                    {
                        field : 'orderCode',
                        title : '订单编号'
                    },
                    {
                        field : 'orderName',
                        title : '订单名称'
                    },
                    {
                        visible:false,
                        field : 'demandId',
                        title : '需求商id'
                    },
                    {
                        visible:false,
                        field : 'demandName',
                        title : '需求商姓名'
                    },
                    {
                        visible:false,
                        field : 'orderType',
                        title : '订单类型'
                    },
                    {
                        field : 'orderManagerName',
                        title : '订单负责人'
                    },
                    {
                        visible:false,
                        field : 'orderSigningDate',
                        title : '签订日期'
                    },
                    {
                        field : 'orderStartDate',
                        title : '开始日期'
                    },
                    {
                        field : 'orderDeadline',
                        title : '截止日期'
                    },
                    {
                        field : 'orderPeriod',
                        title : '订单期次'
                    },
                    {
                        visible:false,
                        field : 'orderSender',
                        title : '发件人'
                    },
                    {
                        field : 'state',
                        title : '订单状态'
                    },
                    {
                        visible:false,
                        field : 'senderPlace',
                        title : '发货地'
                    },
                    {
                        visible:false,
                        field : 'contractId',
                        title : '合同编号'
                    },
                    {
                        visible:false,
                        field : 'totalSum',
                        title : '总金额'
                    },
                    {
                        visible:false,
                        field : 'remark',
                        title : '备注'
                    },
                    {
                        visible:false,
                        field : 'isDeleted',
                        title : '删除标志'
                    },
                    {
                        visible:false,
                        field : 'files',
                        title : '附件'
                    },
                    {
                        title : '操作',
                        field : 'id',
                        align : 'center',
                        formatter : function(value, row, index) {
                            return "<a class='btn btn-primary btn-sm' title='选择' onclick='select("
                                + JSON.stringify(row)
                                + ")'><i class='fa fa-ticket'></i>选择</a> ";
                        }
                    } ]
            });
}
function select(row) {
    let index = parent.layer.getFrameIndex(window.name); //获取窗口索引rn fmt;
    parent.document.getElementById("orderId").setAttribute("value", row.orderId);
    parent.document.getElementById("orderCode").setAttribute("value", row.orderCode);
    parent.document.getElementById("orderName").setAttribute("value", row.orderName);
    parent.document.getElementById("custermerId").setAttribute("value", row.demandId);
    parent.document.getElementById("custermerName").setAttribute("value", row.demandName);
    let id = row.demandId;
    $.ajax({
        catch:true,
        method : 'get',
        url : "/factory/custermerInformation/getDo/"+id,
        dataType : "json",
        async : false,
        data : {
        },
        success : function(r) {
            console.log(r)
            console.log(r.custermerId);
            console.log(r.name);
            parent.document.getElementById("contact").setAttribute("value", r.name==null?"":r.name);
            parent.document.getElementById("contactTel").setAttribute("value", r.contactPersonTel==null?"":r.contactPersonTel);

        }
    });

    parent.layer.close(index);
}
function reLoad() {


    $('#exampleTable').bootstrapTable('refresh');
}
function add() {
    window.open( prefix + '/add',"添加","top=50,left=100,height=900,width=900,location=no")

}
function edit(id) {
    window.open( prefix + '/edit/' + id,"编辑","top=50,left=100,height=900,width=900,location=no")

}
function exportFile() {
    var name = $('#searchName').val();
    var searchType = $('#searchType').val();
    var dateBefore = $('#searchDateBefore').val();
    var dateLater = $('#searchDateLate').val();
    location.href = prefix + "/exportFile" + "?name=" + name + "&searchType=" + searchType + "&dateBefore="
        + dateBefore + "&dateLater=" + dateLater;
}
function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            url : prefix+"/remove",
            type : "post",
            data : {
                'orderId' : id
            },
            success : function(r) {
                if (r.code==0) {
                    layer.msg(r.msg);
                    reLoad();
                }else{
                    layer.msg(r.msg);
                }
            }
        });
    })
}
function details(id){

    window.open( prefix + '/details/'+id,"订单详情","top=50,left=100,height=900,width=900,location=no")
}
function resetPwd(id) {
}
function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn : [ '确定', '取消' ]
        // 按钮
    }, function() {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function(i, row) {
            ids[i] = row['orderId'];
        });
        $.ajax({
            type : 'POST',
            data : {
                "ids" : ids
            },
            url : prefix + '/batchRemove',
            success : function(r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function() {

    });
}