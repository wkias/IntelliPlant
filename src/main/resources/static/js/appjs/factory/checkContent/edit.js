$().ready(function() {
	validateRule();
    loadType();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function loadType() {
    var html = "";
    $.ajax({
        url: '/common/dict/list/equipment_type',
        success: function (data) {
            // 加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].id + '">' + data[i].name + '</option>'
            }
            $("#equipmentType").append(html);
            $("#equipmentType").chosen({
                maxHeight: 200
            });
            $("#equipmentType").val($("#oldequipmentType").val());
            $("#equipmentType").trigger("chosen:updated");
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
		url : "/factory/checkContent/update",
		data : new FormData($('#signupForm')[0]),// 你的formid
        processData: false,//
        contentType: false,//
        dataType: 'json',//
        async: false,
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
function openUserSelect(){
    layer.open({
        type:2,
        title:"选择人员",
        area : [ '300px', '450px' ],
        content:"/sys/user/treeView"
    })
}
function loadUser(userIds,userNames){
    setChecker(userIds[0]);
}
function setChecker(checkerId) {

    $('#creatUserId').attr("value",checkerId);
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
function showImg(obj) {
    var file = $(obj)[0].files[0];    //获取文件信息
    var imgdata = '';
    if (file) {
        var reader = new FileReader();  //调用FileReader
        reader.readAsDataURL(file); //将文件读取为 DataURL(base64)
        reader.onload = function (evt) {   //读取操作完成时触发。
            $("#photoShow").attr('src', evt.target.result)  //将img标签的src绑定为DataURL
        };
    } else {
        alert("上传失败");
    }
}