$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		console.log("submit")
		save();
	}
});
function refreshOrder() {
	$("[name='order']").each(function () {
		$(this).val($("[name='order']").index(this)+1)
	})
}
function addProcess() {
	var $table=$("[name='processItem']:last");
	var $tableClone=$table.clone();
	$tableClone.find("input").val("")
	$table.after($tableClone)
	refreshOrder()
}
function delProcess() {
	console.log("delPorcess")
	if(  $("[name='processCheck']").length>$("[name='processCheck']:checked").length){
		$("[name='processCheck']:checked").parents("[name='processItem']").remove();
	}else{
		$("[name='processCheck']:checked").parents("[name='processItem']").find("input").val("");
	}
	refreshOrder()
}
//打开工序选择
var lastClickedItem;
function openProcessSelect(obj){
	lastClickedItem=obj;
	top.layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '500px' ],
		content : "/factory/craftDefinition/processSelect" ,// iframe的url
		success:function (layero,index) {
			top.layerParent.push(window)
		}
	});
}
function loadProcess(row) {
	console.log(row);
	$(lastClickedItem).find("[name='processId']").val(row.processId);
	$(lastClickedItem).find("[name='processName']").val(row.processName);
	$(lastClickedItem).find("[name='processCode']").val(row.processCode);
}
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/factory/craftDefinition/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			let layerParent=top.layerParent.pop();
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