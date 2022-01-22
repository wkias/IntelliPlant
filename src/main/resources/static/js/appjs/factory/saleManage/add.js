let prefix = '/saleManage/saleManage';
$().ready(function() {
    loadDicts();
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
var openUser = function(){
    layer.open({
        type:2,
        title:"选择人员",
        area : [ '300px', '450px' ],
        content:"/sys/user/treeView"
    })
}
function loadUserDO(users){
    console.log(users);
    let user = users[0];
    $("#saleManagerId").val(user.id);
    $("#saleManagerName").val(user.text);

}
function loadDicts(){
    $.ajax({
        url: '/common/dict/list/sale_state',
        success: function (data) {
            var html = "";
            // 加载数据
            html+="<option value=''>所有</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#saleState").append(html);
            $("#saleState").chosen({
                maxHeight: 200
            });
            $("#saleState").trigger("chosen:updated");
        }
    });

}
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/saleManage/saleManage/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
            var layerParent = top.layerParent;
            if (data.code == 0) {
                layerParent.layer.msg("操作成功");
                // layerParent.reLoad();
                var index = top.layer.getFrameIndex(window.name); // 获取窗口索引
                top.layer.close(index);
            } else {
                layerParent.layer.alert(data.msg)
            }

		}
	});

}
function getOrder() {
    layer.open({
        async : false,
        type : 2,
        title : '订单列表',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : ['1000px', '600px'],
        content : prefix + '/orderList',
        end:function () {
            // loadPeriod();
        }

    });

}
function getSelect(data) {
    var eleName = "content";
            // 加载数据

            for (var i = 0; i < data.length; i++) {
                // if ($("[name='"+eleName+"Id']:eq(0)").val() == "") {
                    //隐藏域

                    $("[name='"+eleName+"Id']:eq("+i+")").val(data[i].contentId);
                    $("[name='"+eleName+"ProductId']:eq("+i+")").val(data[i].productId);
                    $("#destination").val(data[i].destination);
                    // $("[name='"+eleName+"ContactPersonId']:eq(0)").val(trader.mainContactPerson);

                    //表单显示
                    $("[name='"+eleName+"']:eq("+i+") :input.form-control:eq(0)").val(data[i].productDefinitionDO.productCode);
                    $("[name='"+eleName+"']:eq("+i+") :input.form-control:eq(1)").val(data[i].productDefinitionDO.productName);
                    $("[name='"+eleName+"']:eq("+i+") :input.form-control:eq(2)").val(data[i].productDefinitionDO.productModel);
                    $("[name='"+eleName+"']:eq("+i+") :input.form-control:eq(3)").val(data[i].count-data[i].complete);
                    $("[name='"+eleName+"']:eq("+i+") :input.form-control:eq(4)").val(data[i].productDefinitionDO.unit);
                    $("[name='"+eleName+"']:eq("+i+") :input.form-control:eq(5)").val(data[i].productDefinitionDO.unitName);
                    $("[name='"+eleName+"']:eq("+i+") :input.form-control:eq(6)").val(data[i].deadline);
                    $("[name='"+eleName+"']:eq("+i+") :input.form-control:eq(7)").val(data[i].storehouse);
                   if(i!=(data.length-1)){
                    var table = $("[name='"+eleName+"']:last").eq(0);
                    $("[name='"+eleName+"']:last").append(table.clone());}

            }
}
function loadPeriod() {
    // var date = $("#period").val();
    var orderId = $("#orderId").val();
    if(orderId!=""&&orderId!=null){
    top.layer.open({
        type : 2,
        title : '订单详情',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '1000px', '600px' ],
        content : prefix + '/select/'+orderId,// iframe的url
        success:function (layero,index) {
            top.layerParent = window
        }
    });
    }else layer.msg("请先选择订单");


    // $.ajax({
    //     url: '/contractManage/orderContent/listByComplete',
    //     type:"POST",
    //     data:{
    //         // 'deadline':date,
    //         'orderId':orderId
    //     },
    //     dateType:"JSON",
    //     error : function(request) {
    //         parent.layer.alert("Connection error");
    //     },
    //     success: function (data) {
    //         var eleName = "content";
    //         // 加载数据
    //
    //         for (var i = 0; i < data.length; i++) {
    //             // if ($("[name='"+eleName+"Id']:eq(0)").val() == "") {
    //                 //隐藏域
    //
    //                 $("[name='"+eleName+"Id']:eq("+i+")").val(data[i].contentId);
    //                 $("[name='"+eleName+"ProductId']:eq("+i+")").val(data[i].productId);
    //                 $("#destination").val(data[i].destination);
    //                 // $("[name='"+eleName+"ContactPersonId']:eq(0)").val(trader.mainContactPerson);
    //
    //                 //表单显示
    //                 $("[name='"+eleName+"']:eq("+i+") :input.form-control:eq(0)").val(data[i].productDefinitionDO.productName);
    //                 $("[name='"+eleName+"']:eq("+i+") :input.form-control:eq(1)").val(data[i].productDefinitionDO.productModel);
    //                 $("[name='"+eleName+"']:eq("+i+") :input.form-control:eq(2)").val(data[i].productDefinitionDO.productSize);
    //                 $("[name='"+eleName+"']:eq("+i+") :input.form-control:eq(3)").val(data[i].count-data[i].complete);
    //                 $("[name='"+eleName+"']:eq("+i+") :input.form-control:eq(4)").val(data[i].productDefinitionDO.unit);
    //                 $("[name='"+eleName+"']:eq("+i+") :input.form-control:eq(5)").val(data[i].productDefinitionDO.unitName);
    //                 $("[name='"+eleName+"']:eq("+i+") :input.form-control:eq(6)").val(data[i].deadline);
    //                 $("[name='"+eleName+"']:eq("+i+") :input.form-control:eq(7)").val(data[i].storehouse);
    //                if(i!=(data.length-1)){
    //                 var table = $("[name='"+eleName+"']:eq(0)");
    //                 $("[name='"+eleName+"']:eq("+i+")").append(table.clone());}
    //
    //         }
    //
    //     }
    // });

}

function getCustermer() {
    layer.open({
        type : 2,
        title : '收货单位列表',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : ['1000px', '600px'],
        content : prefix + '/custermerList' // iframe的url
    });
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			}
		}
	})
}