$().ready(function() {
	validateRule();
    loadType();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
    var formData = new FormData($('#signupForm')[0])
    $.ajax({
        cache : true,
        type : "POST",
        url : "/factory/equipmentMaintainCycle/update",
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

function loadType() {
    var html = "";
    $.ajax({
        url: '/common/dict/list/equipment_type',
        success: function (data) {
            // 加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $(".chosen-select").append(html);
            $(".chosen-select").chosen({
                maxHeight: 200
            });
            $(".chosen-select").val($("#Ttype").val());
            $(".chosen-select").trigger("chosen:updated");
            // 点击事件
            $('.chosen-select').on('change', function (e, params) {

            });
        }
    });
}