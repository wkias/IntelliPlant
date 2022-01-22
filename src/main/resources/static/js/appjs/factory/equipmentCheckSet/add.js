var prefix="/factory/equipmentCheckSet";
$().ready(function() {
	validateRule();
	loadCheckCycle();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function loadCheckCycle() {
	var html = "";
	$.ajax({
		url: '/common/dict/list/check_cycle',
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
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/factory/equipmentCheckSet/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				var layerParent = top.layerParent;
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
//打开设备列表
function  openEquipmentSelect() {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '1000px', '600px' ],
		content : prefix + '/equipmentSelect' ,// iframe的url
		success:function (layero,index) {
			top.layerParent = window
		}
	});
}
