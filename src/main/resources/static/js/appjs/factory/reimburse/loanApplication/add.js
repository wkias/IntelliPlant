$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});

function getBank() {
    top.layer.open({
        async:false,
        type : 2,
        title : '合同列表',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '1000px', '600px' ],
        content : '/reimburse/loanApplication/bankAccountList',// iframe的url
        success:function (layero,index) {
            top.layerParent = window

        },
        end:function () {
        }
    });

}

function save() {
    var formData = new FormData($('#signupForm')[0])
    $.ajax({
        cache : false,
        type : "POST",
        url : "/reimburse/loanApplication/save",
        data: formData,// 你的formid
        async : false,
        contentType: false,//必须
        processData: false,//必须
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
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
function summary() {
    $("#summary").val($("#loanAccount").val())
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


