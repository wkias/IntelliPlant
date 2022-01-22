$().ready(function () {
    validateRule();
});

$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});

function save() {
    var formData = new FormData($('#signupForm')[0])
    $.ajax({
        cache: false,
        type: "POST",
        url: "/factory/paymentRequest/save",
        data: formData,// 你的formid
        async: false,
        contentType: false,//必须
        processData: false,//必须
        error: function (request) {
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
    $("#summary").val($("#claimAmount").val())
}

var contractSelect = function () {
    console.log("test top layer open")
    top.layer.open({
        type: 2,
        title: "选择关联表",
        area: ['1000px', '600px'],
        content: "/factory/paymentRequest/supplierInformation",
        success: function (layero, index) {
            top.layerParent = window
        }
    });
};

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            name: {
                required: true
            }
        },
        messages: {
            name: {
                required: icon + "请输入姓名"
            }
        }
    })
}