$().ready(function() {
    loadDicts();
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function loadDicts(){
    $.ajax({
        url: '/common/dict/list/product_type',
        success: function (data) {
            var html = "";
            // 加载数据
            html+="<option value=''>所有</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#productType").append(html);
            $("#productType").chosen({
                maxHeight: 200
            });
            $("#productType").val($("#oldType").val());
            $("#productType").trigger("chosen:updated");
        }
    });
    $.ajax({
        url: '/common/dict/list/weight_unit',
        success: function (data) {
            var html = "";
            // 加载数据
            html+="<option value=''>所有</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#weightUnit").append(html);
            $("#weightUnit").chosen({
                maxHeight: 200
            });
            $("#weightUnit").val($("#oldWeight").val());
            $("#weightUnit").trigger("chosen:updated");
        }
    });
    $.ajax({
        url: '/common/dict/list/quantity_unit',
        success: function (data) {
            var html = "";
            // 加载数据
            html+="<option value=''>所有</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#quantityUnit").append(html);
            $("#quantityUnit").chosen({
                maxHeight: 200
            });
            $("#quantityUnit").val($("#oldQuantity").val());
            $("#quantityUnit").trigger("chosen:updated");
        }
    });
}
function update() {
    var formData = new FormData($('#signupForm')[0])
	$.ajax({
		cache : true,
		type : "POST",
		url : "/productManage/productDetail/update",
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