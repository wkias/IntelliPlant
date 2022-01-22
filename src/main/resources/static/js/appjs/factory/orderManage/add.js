$().ready(function() {
    loadDicts();
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function loadDicts(){
    $.ajax({
        url: '/common/dict/list/order_type',
        success: function (data) {
            var html = "";
            // 加载数据
            html+="<option value=''>所有</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#orderType").append(html);
            $("#orderType").chosen({
                maxHeight: 200
            });
            $("#orderType").trigger("chosen:updated");
        }
    });
    $.ajax({
        url: '/common/dict/list/order_state',
        success: function (data) {
            var html = "";
            // 加载数据
            html+="<option value=''>所有</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#state").append(html);
            $("#state").chosen({
                maxHeight: 200
            });
            $("#state").trigger("chosen:updated");
        }
    });
}
function getContract() {
    top.layer.open({
        async:false,
        type : 2,
        title : '合同列表',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '1000px', '600px' ],
        content : '/factory/orderManage/contractList',// iframe的url
        success:function (layero,index) {
            top.layerParent = window

        },
        end:function () {
            getDemand();
        }
    });

}
function getDemand() {
    let contractId = $("#contractId").val();
    if(contractId!=""&&contractId!=undefined){
    $.ajax({
        type : "GET",
        url : "/factory/contractManage/getVO/"+contractId,
        // data : {'contractId':contractId},// 你的formid
        dateType:"json",
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            let vo = data.demanders;
            let le = vo.length;
            var html = "";
            html += "<option value=''>所有</option>";
            for (var i = 0; i < le; i++) {
                html += '<option value="' + data.demanders[i].trader.custermerId + '">' + data.demanders[i].trader.custermerName + '</option>'
            }
            $("#demandId").append(html);
            $("#demandId").chosen({
                maxHeight: 200
            });
            $("#demandId").val($("#Ttype").val());
            $("#demandId").trigger("chosen:updated");

        }
    });}

}
function getContractByCode() {
	var code = $("#contractCode").val();

    $.ajax({
        cache : true,
        type : "GET",
        url : "/factory/contractManage/ajaxList",
        data : {
            'contractCode' : code
        },
		dateType:"json",
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
			if(data[0]!=null){
                $("#contractId").val(data[0].contractId);
                $("#contractType").val(data[0].typeName);
                $("#contractName").val(data[0].contractName);
			}else alert("无此合同编码");


        }
    });

}

function save() {
    var formData = new FormData($('#signupForm')[0])
	$.ajax({
		cache : true,
		type : "POST",
		url : "/factory/orderManage/save",
		data : formData,// 你的formid
		async : false,
        dateType:"json",
        contentType: false,//必须
        processData: false,//必须
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
            var layerParent = top.layerParent;

            if (data.code == 0) {
                layerParent.layer.msg("操作成功");
                var index = top.layer.getFrameIndex(window.name); // 获取窗口索引
                top.layer.close(index);
            } else {
                layerParent.layer.alert(data.msg)
            }

		}
	});

}
var openUser = function(){
    layer.open({
        type:2,
        title:"选择人员",
        area : [ '300px', '450px' ],
        content:"/sys/user/treeView"
    })
}
function loadProduct(product, isBatch) {
    console.log(product);
    var eleName;
    if(isBatch=="NoBatch"){
        eleName="noBatch";
    }else {
        eleName="batch";
    }
    if ($("[name='"+eleName+"Id']:eq(0)").val() == "") {
        //隐藏域
        $("[name='"+eleName+"Id']:eq(0)").val(product.productId);
        // $("[name='"+eleName+"ContactPersonId']:eq(0)").val(trader.mainContactPerson);
        //b标签文本
        let $text=$("[name='"+eleName+"Item']:eq(0) b");
        $("[name='"+eleName+"Item']:eq(0) b").text(""+(isBatch=="NoBatch"?"订单内容":"订单内容"));
        //表单显示
        $("[name='"+eleName+"Item']:eq(0) :input.form-control:eq(0)").val(product.productCode);
        $("[name='"+eleName+"Item']:eq(0) :input.form-control:eq(1)").val(product.productName);
        $("[name='"+eleName+"Item']:eq(0) :input.form-control:eq(2)").val(product.productModel);
        $("[name='"+eleName+"Item']:eq(0) :input.form-control:eq(3)").val("");
        $("[name='"+eleName+"Item']:eq(0) :input.form-control:eq(3)").change(function () {
            $("[name='"+eleName+"Item']:eq(0) :input.form-control:eq(7)").val(product.price*$(this).val());
            let sum = 0 ;
            $("[name='noTotalSum']").each(function (index) {
                if($(this).val()!=undefined&&$(this).val()!=null){
                sum = parseInt($(this).val())+parseInt(sum);
                }
            })
            $("[name='TotalSum']").each(function (index) {

                if($(this).val()!=undefined&&$(this).val()!=null&&$(this).val()!=""){
                    sum = parseInt($(this).val())+parseInt(sum);


                }
            })
            $('#totalSum').val(sum);

        });
        $("[name='"+eleName+"Item']:eq(0) :input.form-control:eq(4)").val("");
         $("[name='"+eleName+"Item']:eq(0) :input.form-control:eq(5)").val(product.unitName);
        $("[name='"+eleName+"Item']:eq(0) :input.form-control:eq(6)").val(product.price);
        $("[name='"+eleName+"Item']:eq(0) :input.form-control:eq(7)").val("");
        $("[name='"+eleName+"Item']:eq(0) :input.form-control:eq(8)").val("");
        $("[name='"+eleName+"Item']:eq(0) :input.form-control:eq(9)").val("");

    } else {
        var table = $("[name='"+eleName+"Item']:eq(0)")
        $("[name='"+eleName+"Item']:last").after(table.clone());
        //隐藏域
        $("[name='"+eleName+"Id']:last").val(product.productId);
        // $("[name='"+eleName+"ContactPersonId']:eq(0)").val(trader.mainContactPerson);
        //b标签文本
        let $text=$("[name='"+eleName+"Item']:eq(0) b");
        $("[name='"+eleName+"Item']:last b").text(""+(isBatch=="NoBatch"?"订单内容":"订单内容"));
        //表单显示
        $("[name='"+eleName+"Item']:last :input.form-control:eq(0)").val(product.productCode);
        $("[name='"+eleName+"Item']:last :input.form-control:eq(1)").val(product.productName);
        $("[name='"+eleName+"Item']:last :input.form-control:eq(2)").val(product.productModel);
        $("[name='"+eleName+"Item']:last :input.form-control:eq(3)").val("");
        $("[name='"+eleName+"Item']:last :input.form-control:eq(4)").val("");
         $("[name='"+eleName+"Item']:last :input.form-control:eq(5)").val(product.unitName);
        $("[name='"+eleName+"Item']:last :input.form-control:eq(6)").val(product.price);
        $("[name='"+eleName+"Item']:last :input.form-control:eq(3)").change(function () {

            $("[name='"+eleName+"Item']:last :input.form-control:eq(7)").val(product.price*$(this).val());
            let sum = 0 ;
            $("[name='noTotalSum']").each(function (index) {
                if($(this).val()!=undefined&&$(this).val()!=null){
                    sum = parseInt($(this).val())+parseInt(sum);
                }
            })
            $("[name='TotalSum']").each(function (index) {

                if($(this).val()!=undefined&&$(this).val()!=null&&$(this).val()!=""){
                    sum = parseInt($(this).val())+parseInt(sum);


                }
            })
            $('#totalSum').val(sum);
        });
        $("[name='"+eleName+"Item']:last :input.form-control:eq(7)").val("");
        $("[name='"+eleName+"Item']:last :input.form-control:eq(8)").val("");
        $("[name='"+eleName+"Item']:last :input.form-control:eq(9)").val("");
    }
}
var addNoBatch = function(){
    top.layer.open({
        type: 2,
        title: "选择订单内容",
        area: ['1000px', '600px'],
        content: "/factory/orderManage/productList/NoBatch", success:function (layero,index) {
            top.layerParent = window

        },
        end:function () {
            // location.reload();
        }


    })
}
function addBatch() {
    layer.open({
        type: 2,
        title: "选择分批次订单内容",
        area: ['1000px', '600px'],
        content: "/factory/orderManage/productList/Batch"
    })
}

function delNoBatch() {
    if(  $("[name='noBatchChecked']").length>$("[name='noBatchChecked']:checked").length){
        $("[name='noBatchChecked']:checked").parent().remove();
    }else{
        $("[name='noBatchChecked']:checked").parent().find("input").val("");
    }
}
function delBatch() {
    if(  $("[name='batchChecked']").length>$("[name='batchChecked']:checked").length){
        $("[name='batchChecked']:checked").parent().remove();
    }else{
        $("[name='batchChecked']:checked").parent().find("input").val("");
    }
}
function loadUserDO(users){
    console.log(users);
    let user = users[0];
    $("#orderManagerId").val(user.id);
    $("#orderManagerName").val(user.text);

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