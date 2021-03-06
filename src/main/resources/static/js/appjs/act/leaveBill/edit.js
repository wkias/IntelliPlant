$().ready(function() {
	validateRule();
	backForm();

});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});

function backForm() {
	//如果是回退请求
	if($("#applyUserId").val()==$("#currentUserId").val()){
		$(".form-info").removeAttr("readonly");
	}
}
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/act/leaveBill/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
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