$().ready(function () {
    validateRule();
});
$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});

function loadProcess(row) {
    if ($("[name='processId']:eq(0)").val() === "") {
        $("[name='processId']:eq(0)").val(row.processId);
        $("[name='processItems']:eq(0) :input.form-control:eq(0)").val(row.processName);
    } else {
        var table = $("[name='processItems']:eq(0)")
        $("[name='processItems']:last").after(table.clone());
        $("[name='processId']:last").val(row.processId);
        $("[name='processItems']:last :input.form-control:eq(0)").val(row.processName);
        $("[name='processItems']:last :input.form-control:eq(4)").val("");
        $("[name='processItems']:last :input.form-control:eq(5)").val("");
        $("[name='processItems']:last :input.form-control:eq(6)").val("");
    }
    addProduct();
}

function loadProduct(row) {
    if ($("[name='componentId']:eq(0)").val() === "") {
        $("[name='componentId']:eq(0)").val(row.productId);
        $("[name='processItems']:eq(0) :input.form-control:eq(1)").val(row.productName);
        $("[name='processItems']:eq(0) :input.form-control:eq(2)").val(row.model);
        $("[name='processItems']:eq(0) :input.form-control:eq(3)").val(row.quantityUnitName);
    } else {
        var table = $("[name='processItems']:eq(0)")
        $("[name='componentId']:last").val(row.productId);
        $("[name='processItems']:last :input.form-control:eq(1)").val(row.productName);
        $("[name='processItems']:last :input.form-control:eq(2)").val(row.model);
        $("[name='processItems']:last :input.form-control:eq(3)").val(row.quantityUnitName);
    }
}

function addProcess() {
    layer.open({
        type: 2,
        title: "选择工序",
        area: ['1000px', '600px'],
        content: "/productManage/processInspectionReport/selectProcess"
    })
}

function removeProcess() {
    if ($("[name='removeProcessChecked']").length > $("[name='removeProcessChecked']:checked").length) {
        $("[name='removeProcessChecked']:checked").parent().remove();
    } else {
        $("[name='removeProcessChecked']:checked").parent().find("input").val("");
    }
}

function addProduct() {
    layer.open({
        type: 2,
        title: "选择产品",
        area: ['1000px', '600px'],
        content: "/productManage/processInspectionReport/selectProduct"
    })
}

function save() {
    var formData = new FormData($('#signupForm')[0])
    $.ajax({
        cache: true,
        type: "POST",
        url: "/productManage/processInspectionReport/save",
        data: formData,
        async: false,
        contentType: false,
        processData: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code === 0) {
                parent.layer.msg("操作成功");
                window.opener.reLoad();
                window.close();
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
                required: icon + "请输入姓名"
            }
        }
    })
}