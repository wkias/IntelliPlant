$().ready(function() {
	validateRule();
	loadDicts();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function loadDicts() {
	$.ajax({
		url: '/common/dict/list/product_plan_priority',
		success: function (data) {
			var html = "";
			// 加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].name + '">' + data[i].name + '</option>'
			}
			$("#priority").append(html);
			$("#priority").chosen({
				maxHeight: 200
			});
			$("#priority").val($("#oldPriority").val())
			$("#priority").trigger("chosen:updated");
		}
	});
	$.ajax({
		url: '/common/dict/list/product_plan_state',
		success: function (data) {
			var html = "";
			// 加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].name + '">' + data[i].name + '</option>'
			}
			$("#planState").append(html);
			$("#planState").chosen({
				maxHeight: 200
			});
			$("#planState").val($("#oldPlanState").val())
			$("#planState").trigger("chosen:updated");
			if($("#planState").val()==2||$("#planState").val()==4){
				$("#planState").attr("disabled",true);
				$("#planState").trigger("chosen:updated");
			}
		}
	});
}
//打开人员选择
var openUser = function () {
	top.layer.open({
		type: 2,
		title: "选择人员",
		area: ['1000px', '600px'],
		content: "/sys/user/select",
		success(layero,index){
			top.layerParent.push(window);
		}
	})
};
function loadUserDO(user){
	console.log(user);
	$("#dutyPersonId").val(user.userId);
	$("#dutyPersonName").val(user.name);
}
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/factory/productPlan/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			console.log("edit close tsest")
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
				required : icon + "请输入名字"
			}
		}
	})
}