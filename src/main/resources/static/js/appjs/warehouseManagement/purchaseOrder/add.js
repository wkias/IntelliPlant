$().ready(function() {
	validateRule();
	loadDicts();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function loadDicts() {
	$.ajax({
		url: '/common/dict/list/purchase_business_type',
		success: function (data) {
			var html = "";
			// 加载数据
			html += "<option value=''>所有</option>";
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
			}
			$("#businessType").append(html);
			$("#businessType").chosen({
				maxHeight: 200
			});
			$('#businessType').on('change', function(e, params) {
				if(params.selected=="material_purchase"){
					console.log("原料采购");
					$("#supplier").attr("readonly","readonly");
					$("#supplier").click(openSupplierSelect);
				}else{
					$("#supplier").removeAttr("readonly");
					$("#supplier").unbind("click");
				}
			});
			$("#businessType").trigger("chosen:updated");
		}
	});
	$.ajax({
		url: '/common/dict/list/common_phrase',
		success: function (data) {
			var html = "";
			// 加载数据
			html += "<option value=''>常用审批意见</option>";
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].name + '">' + data[i].name + '</option>'
			}
			$("#commonPhrase").append(html);
			$("#commonPhrase").chosen({
				maxHeight: 200
			});
			$('#commonPhrase').on('change', function(e, params) {
				$("#auditResult").text(params.selected);
			});
			$("#commonPhrase").trigger("chosen:updated");
		}
	});
}
//文件上传
function uploadFile() {
	var formData=new FormData();
	formData.append("file",$("#file")[0].files[0]);
	formData.append("fileName",$("#fileName").val());
	$.ajax({
		type:"POST",//必须
		url:'/common/sysFile/fileUpload',
		enctype:"multipart/form-data",
		data:formData,
		processData: false,//必须
		contentType:false,//必须
		success:function (r) {
			layer.msg(r.msg);
			$("#fileName").val(r.fileName);
			$("#file").nextAll("p").remove()
			$("#file").after("<p style='color: #00B83F'>上传成功<span class='glyphicon glyphicon-ok'></span></p> ");
		},
		error:function () {
			console.log("ajaxError:uploadFile");
		}

	})
}
function openDeptSelect() {
	layer.open({
		type : 2,
		title : '选择部门',
		maxmin : false,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '300px', '450px' ],
		content : "/system/sysDept/" + 'treeView'// iframe的url
	});
}
function loadDept(deptId,deptName) {
	$("#applyDeptId").val(deptId)
	$("#applyDeptName").val(deptName)
}
//打开人员选择
var openUser = function () {
	top.layer.open({
		type: 2,
		title: "选择人员",
		area: ['1000px', '600px'],
		content: "/sys/user/select",
		success:function (layero,index) {
			top.layerParent.push(window);
		}
	})
};
function loadUserDO(user){
	console.log(user);
	$("#dutyPersonId").val(user.userId);
	$("#dutyPersonName").val(user.name);
}
//打开产品选择
var lastClickedItem;
function openProductSelect(obj){
	lastClickedItem=obj;
	top.layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '1000px', '600px' ],
		content : "/warehouseManagement/purchaseOrder/productSelect" ,// iframe的url
		success:function (layero,index) {
			top.layerParent.push(window);
		}
	});
}
//读取选中的产品信息
function loadProductSelect(row){
	$(lastClickedItem).find("[name='productId']").val(row.productId);
	$(lastClickedItem).find("[name='materialName']").val(row.productName);
	$(lastClickedItem).find("[name='materialModel']").val(row.model);
	$(lastClickedItem).find("[name='materiaStuff']").val(row.material);
	$(lastClickedItem).find("[name='numberUnit']").val(row.quantityUnitName);
	$(lastClickedItem).find("[name='weightUnit']").val(row.weightUnitName);
	$(lastClickedItem).find("[name='singleWeight']").val(row.weight);
	$(lastClickedItem).find("[name='singleMoney']").val(row.price);

	let $singleMoney=$(lastClickedItem).find("[name='singleMoney']");
	let $number=$(lastClickedItem).find("[name='number']");
	let $money=$(lastClickedItem).find("[name='money']");
	$number.on("change",function () {
		//改变金额
		$money.val($number.val()*$singleMoney.val());
		//改变总数量
		let numberSum=0;
		$("[name='number']").each(function () {
			numberSum=numberSum+1*$(this).val();
		})
		$("#totalNumber").val(numberSum)
		//改变总重量
		let weightSum=0;
		$("[name='singleWeight']").each(function () {
			weightSum=weightSum+$(this).val()*$(this).parents("tr").find("[name='number']").val();
		})
		$("#totalWeight").val(weightSum)
		//改变总金额
		let moneySum=0;
		$("[name='money']").each(function () {
			moneySum=moneySum+1*$(this).val();
		})
		$("#totalMoney").val(moneySum)

	})
	$singleMoney.on("change",function () {
		//改变金额
		$money.val($number.val()*$singleMoney.val());
		//改变总金额
		let sum=0;
		$("[name='money']").each(function () {
			sum=sum+1*$(this).val();
		})
		$("#totalMoney").val(sum)
	})
	//console.log(row);
}
function addDetail() {
	var $table=$("[name='detailItem']:last");
	var $tableClone=$table.clone();
	$tableClone.find("input").val("")
	$table.after($tableClone)
}
function openSupplierSelect() {
	top.layer.open({
		type: 2,
		title: "选择供应商",
		area: ['800px', '600px'],
		content: "/factory/contractManage/traderList/Sup",
		success:function (layero,index) {
			top.layerParent.push(window);
		}
	})
}
function loadTrader(supplier) {
	console.log(supplier);
	$("#supplier").val(supplier.custermerName);
}
function delDetail(){
	if(  $("[name='detailCheck']").length>$("[name='detailCheck']:checked").length){
		$("[name='detailCheck']:checked").parents("[name='detailItem']").remove();
	}else{
		$("[name='detailCheck']:checked").parents("[name='detailItem']").find("input").val("");
	}
}
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/warehouseManagement/purchaseOrder/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			var layerParent = top.layerParent.pop();
			if (data.code == 0) {
				layerParent.layer.msg("操作成功");
				layerParent.reLoad();
				var index = top.layer.getFrameIndex(window.name); // 获取窗口索引
				top.layer.close(index);
			} else {
				layerParent.layer.alert(data.msg)
			}

		}
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