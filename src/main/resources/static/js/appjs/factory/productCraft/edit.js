$().ready(function() {
	validateRule();
	loadCrafts();
	loadProducts();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function loadCrafts() {
	$.ajax({
		url: '/factory/productCraft/craftList',
		success: function (data) {
			var html = "";
			// 加载数据
			html += "<option value=''>选择工艺</option>";
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].craftId + '">' + data[i].craftName + '</option>'
			}
			$("#craft").append(html);
			$("#craft").chosen({
				maxHeight: 200
			});
			$("#craft").val($("#oldCraftId").val());
			$("#craft").trigger("chosen:updated");
			if($("#craft").val()==""){return;}
			$.ajax({
				type:"get",
				url:"/factory/craftDefinition/processList/"+$("#craft").val(),
				success:function (data) {
					console.log(data)
					let html=""
					for(let i=0;i<data.length;i++){
						html+="    <a href='#' class='list-group-item'>" +
							"       <h3>工序" +
							data[i].order+
							"</h3> <h4 class=\"list-group-item-text\">工序名称：" +
							data[i].processDefinition.processName +
							"        </h4>\n" +
							"        <p class=\"list-group-item-text\">工序编码" +
							data[i].processDefinition.processCode  +
							"        </p>\n" +
							"    </a>"
					}
					$("#processList").html(html);
				}
			})
		}
	});
}

function loadProducts() {
	$.ajax({
		url: '/factory/productCraft/productList',
		success: function (data) {
			var html = "";
			// 加载数据
			html += "<option value=''>选择产品</option>";
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].productId + '">' + data[i].productName + '</option>'
			}
			$("#product").append(html);
			$("#product").chosen({
				maxHeight: 200
			});
			$("#product").val($("#oldProductId").val());
			$("#product").trigger("chosen:updated");
		}
	});
}
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/factory/productCraft/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
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