$().ready(function () {
    validateRule();
    loadDicts();
});

$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});

function loadDicts() {
    $.ajax({
        url: '/common/dict/list/logistics_company',
        success: function (data) {
            var html = "";
            html += "<option value=''>所有</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#logisticsCompany").append(html);
            $("#logisticsCompany").chosen({
                maxHeight: 200
            });
            $("#logisticsCompany").trigger("chosen:updated");
        }
    });
    $.ajax({
        url: '/common/dict/list/weight_unit',
        success: function (data) {
            var html = "";
            html += "<option value=''>所有</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#weightUnit").append(html);
            $("#weightUnit").chosen({
                maxHeight: 200
            });
            $("#weightUnit").trigger("chosen:updated");
        }
    });
        $.ajax({
            url: '/common/dict/list/sale_state',
            success: function (data) {
                var html = "";
                html+="<option value=''>所有</option>";
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
                }
                $("#saleState").append(html);
                $("#saleState").chosen({
                    maxHeight: 200
                });
                $("#saleState").trigger("chosen:updated");
            }
        });
}

function save() {
    var formData = new FormData($('#signupForm')[0])
    $.ajax({
        cache: false,
        type: "POST",
        url: "/factory/logisticsAccount/save",
        data: formData,// 你的formid
        async: false,
        contentType: false,//必须
        processData: false,//必须
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
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

var openSelect=function() {
    console.log("test top layer open")
    top.layer.open({
        type: 2,
        title: "选择关联表",
        area: ['1000px', '600px'],
        content: "/factory/logisticsAccount/saleManage",
        success:function (layero,index) {
            top.layerParent = window
        }
    });
};

var openPurchase=function() {
    console.log("test top layer open")
    top.layer.open({
        type : 2,
        title : '选择关联表',
        area : [ '1000px', '600px' ],
        content : "/factory/logisticsAccount/purchaseOrder",
        success:function (layero,index) {
            top.layerParent = window
        }
    });
};