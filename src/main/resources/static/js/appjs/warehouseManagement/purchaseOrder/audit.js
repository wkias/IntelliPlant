$().ready(function() {
    validateRule();
    loadDicts();
});

$.validator.setDefaults({
    submitHandler : function() {
        update();
    }
});
function loadDicts() {
    $.ajax({
        url: '/common/dict/list/purchase_business_type',
        success: function (data) {
            var html = "";
            // 加载数据
            html += "<option value=''>所有</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#businessType").append(html);
            $("#businessType").chosen({
                maxHeight: 200
            });
            $('#businessType').on('change', function(e, params) {
                if(params.selected=="material_purchase"){
                    console.log("原料采购");
                    $("#supplier").attr("readonly","readonly");
                    $("#supplier").click(openSupplierSelect);
                }
            });
            $("#businessType").val($("#oldBusinessType").val());
            $("#businessType").trigger("chosen:updated");
        }
    });
    $.ajax({
        url: '/common/dict/list/common_phrase',
        success: function (data) {
            var html = "";
            // 加载数据
            html += "<option value=''>常用审批意见</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].name + '">' + data[i].name + '</option>'
            }
            $("#commonPhrase").append(html);
            $("#commonPhrase").chosen({
                maxHeight: 200
            });
            $('#commonPhrase').on('change', function(e, params) {
                $("#auditComment").text(params.selected);
            });
            $("#commonPhrase").trigger("chosen:updated");
        }
    });
}
//文件上传
function uploadFile() {
    var formData=new FormData();
    formData.append("file",$("#file")[0].files[0]);
    formData.append("fileName",$("#fileName").val());
    $.ajax({
        type:"POST",//必须
        url:'/common/sysFile/fileUpload',
        enctype:"multipart/form-data",
        data:formData,
        processData: false,//必须
        contentType:false,//必须
        success:function (r) {
            layer.msg(r.msg);
            $("#fileName").val(r.fileName);
            $("#file").nextAll("p").remove()
            $("#file").after("<p style='color: #00B83F'>上传成功<span class='glyphicon glyphicon-ok'></span></p> ");
        },
        error:function () {
            console.log("ajaxError:uploadFile");
        }

    })
}
//文件下载
function downloadFile(target){
    window.location='/common/sysFile/files/'+$(target).parent().prev("[name='fileName']").val();
}
function update() {
    $.ajax({
        cache : true,
        type : "POST",
        url : "/warehouseManagement/purchaseOrder/audit",
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