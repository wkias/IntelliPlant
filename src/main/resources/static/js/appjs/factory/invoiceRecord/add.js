$().ready(function() {
	validateRule();
	loadType();
});

function loadType() {
	var html = "";
	$.ajax({
		url: '/common/dict/list/invoice_type',
		success: function (data) {
			// 加载数据
			html+="<option value=''>请选择开票类型</option>"
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
			}
			$(".chosen-select").append(html);
			$(".chosen-select").chosen({
				maxHeight: 200
			});
			$(".chosen-select").val($("#Ttype").val());
			$(".chosen-select").trigger("chosen:updated");
		}
	});
}

$.validator.setDefaults({
	submitHandler: function () {
		save();
	}
});

function save() {
	var formData = new FormData($('#signupForm')[0])
	$.ajax({
		cache : false,
		type : "POST",
		url : "/factory/invoiceRecord/save",
		data : formData,// 你的formid
		dataType:'json',
		contentType:false,//必须
		processData:false,//必须
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			var layerParent = top.layerParentCP;
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

function contractSelect() {
	console.log("test top layer open")
	top.layer.open({
		type: 2,
		title: '选择',
		maxmin: true,
		shadeClose: false, // 点击遮罩关闭层
		area: ['1000px', '600px'],
		content: "/factory/invoiceRecord/contractSelect", // iframe的url
		success: function (layero, index) {
			top.layerParent = window
		}
	});
}

function custermerInformation() {
    console.log("test top layer open")
    top.layer.open({
        type: 2,
        title: '选择',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['1000px', '600px'],
        content: "/factory/invoiceRecord/custermerInformation", // iframe的url
        success: function (layero, index) {
            top.layerParent = window
        }
    });
}

function supplierInformation() {
    console.log("test top layer open")
    top.layer.open({
        type: 2,
        title: '选择',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['1000px', '600px'],
        content: "/factory/invoiceRecord/supplierInformation", // iframe的url
        success: function (layero, index) {
            top.layerParent = window
        }
    });
}


$(document).ready(function (){
	$("#contractType").change(function() {
		var buyingnameID = $('#contractType').val() ;
		if ( buyingnameID == "销售合同") {
		$('#buyingnameID').text("供应商")
		}else{
			$('#buyingnameID').text("需求商")
		}
	})
})

$(document).ready(function (){
	$("#contractType").change(function() {
		var sellingnameID = $('#contractType').val() ;
		if ( sellingnameID == "销售合同") {
			$('#sellingnameID').text("需求商")
		}else{
			$('#sellingnameID').text("供应商")
		}
	})
})



function addDetail() {
	var $table = $("[name='detailItem']:last");
	var $tableClone = $table.clone();
	$tableClone.find("input").val("")
	$table.after($tableClone)
}

function delDetail() {
	if ($("[name='detailCheck']").length > $("[name='detailCheck']:checked").length) {
		$("[name='detailCheck']:checked").parents("[name='detailItem']").remove();
	} else {
		$("[name='detailCheck']:checked").parents("[name='detailItem']").find("input").val("");
	}
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