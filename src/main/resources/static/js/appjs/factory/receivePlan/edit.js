$().ready(function () {
    validateRule();
    // loadBatch();
    loadState();
});
$.validator.setDefaults({
    submitHandler: function () {
        update();
    }
});

function loadBatch() {
    var html = "";
    $.ajax({
        url: '/common/dict/list/receive_batch',
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#receiveBatch").append(html);
            $("#receiveBatch").chosen({
                maxHeight: 200
            });
            $("#receiveBatch").val($("#receiveBatch").val());
            $("#receiveBatch").trigger("chosen:updated");
            // 点击事件
            $('#receiveBatch').on('change', function (e, params) {
            });
        }
    });
}

function loadState() {
    var html = "";
    $.ajax({
        url: '/common/dict/list/receive_state',
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $(".chosen-select").append(html);
            $(".chosen-select").chosen({
                maxHeight: 200
            });
            $(".chosen-select").val($("#state_i").val());
            $(".chosen-select").trigger("chosen:updated");
            // 点击事件
            $('.chosen-select').on('change', function (e, params) {
            });
        }
    });
}

function update() {
    $.ajax({
        cache: true,
        type: "POST",
        url: "/factory/receivePlan/update",
        data: $('#signupForm').serialize(),// 你的formid
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
                required: icon + "请输入名字"
            }
        }
    })
}