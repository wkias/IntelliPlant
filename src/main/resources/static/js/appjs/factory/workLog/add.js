$().ready(function() {
	validateRule();
    loadDicts();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});

function loadDicts(){
    $.ajax({
        url: '/common/dict/list/work_time_type',
        success: function (data) {
            var html = "";
            // 加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#workTimeType").append(html);
            $("#workTimeType").chosen({
                maxHeight: 200
            });
            $("#workTimeType").trigger("chosen:updated");
        }
    });
    $.ajax({
        url: '/common/dict/list/hour_period',
        success: function (data) {
            var html = "";
            // 加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#hourPeriod").append(html);
            $("#hourPeriod").chosen({
                maxHeight: 200
            });
            $("#hourPeriod").trigger("chosen:updated");
        }
    });
}


function save() {
    var formData = new FormData($('#signupForm')[0])
    $.ajax({
        cache : true,
        type : "POST",
        url : "/factory/workLog/save",
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
				required : icon + "请输入姓名"
			}
		}
	})
}