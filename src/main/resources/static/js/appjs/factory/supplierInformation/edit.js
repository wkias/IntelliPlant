$().ready(function() {
	validateRule();
    loadCustermerType();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});

function loadCustermerType() {
    var html = "";
    $.ajax({
        url: '/common/dict/list/supplier_type',
        success: function (data) {
            // 加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $(".chosen-select").append(html);
            $(".chosen-select").chosen({
                maxHeight: 200
            });
            //$(".chosen-select").val($("#Ttype").val());
            $(".chosen-select").trigger("chosen:updated");
            // 点击事件
            // $('.chosen-select').on('change', function (e, params) {
            //
            // });
        }
    });
}
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/factory/supplierInformation/update",
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
				top.layer.alert(data.msg)
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