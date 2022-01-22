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
        url: '/common/dict/list/inspection_items_value_type',
        success: function (data) {
            var html = "<option value=''>选择值类型</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#valueType").append(html);
            $("#valueType").chosen({
                maxHeight: 200
            });
            $("#valueType").trigger("chosen:updated");
        }
    });
    $.ajax({
        url: '/common/dict/list/inspection_items_unit',
        success: function (data) {
            var html = "<option value=''>选择单位</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#unit").append(html);
            $("#unit").chosen({
                maxHeight: 200
            });
            $("#unit").trigger("chosen:updated");
        }
    });
}

function save() {
    $.ajax({
        cache: true,
        type: "POST",
        url: "/factory/inspectionItems/save",
        data: $('#signupForm').serialize(),
        async: false,
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