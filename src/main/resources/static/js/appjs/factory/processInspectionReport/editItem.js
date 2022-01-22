$().ready(function () {
    validateRule();
    loadUnit();
});
$.validator.setDefaults({
    submitHandler: function () {
        update();
    }
});

function loadProcess(row) {
    $("#processId").val(row.processId);
    $("#processName").val(row.processName);
}

function loadProduct(row) {
    $("#componentId").val(row.productId);
    $("#componentName").val(row.productName);
    $("#format").val(row.model);
    $("#unit").val(row.quantityUnitName);
}

function selectProcess() {
    layer.open({
        type: 2,
        title: "选择工序",
        area: ['1000px', '600px'],
        content: "/productManage/processInspectionReport/selectProcess"
    })
}

function selectProduct() {
    layer.open({
        type: 2,
        title: "选择产品",
        area: ['1000px', '600px'],
        content: "/productManage/processInspectionReport/selectProduct"
    })
}

function loadUnit() {
    var html = '<option value="">选择单位</option>';
    $.ajax({
        url: '/common/dict/list/quantity_unit',
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].name + '">' + data[i].name + '</option>'
            }
            $(".chosen-select").append(html);
            $(".chosen-select").val($("#unitt").val());
            $(".chosen-select").chosen({
                maxHeight: 200
            });
            $(".chosen-select").val($("#Uunit").val());
            $(".chosen-select").trigger("chosen:updated");
        }
    });
}

function update() {
    $.ajax({
        cache: true,
        type: "POST",
        url: "/productManage/processInspectionReport/updateItem",
        data: $('#signupForm').serialize(),
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            parent.$('#exampleTable').bootstrapTable('refresh');
            if (data.code === 0) {
                parent.layer.msg("操作成功");
                var index = parent.layer.getFrameIndex(window.name);
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