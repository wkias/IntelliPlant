$().ready(function () {
    validateRule();
    loadDicts();
    loadDetail();
});
$.validator.setDefaults({
    submitHandler: function () {
        update();
    }
});

function refreshData() {
    let q = [];
    let w = [];
    let a = [];
    let quantity = 0;
    let weight = 0;
    let amount = 0;
    $("input[name='_quantity']").each(function (i, v) {
        q.push(parseInt(v.value));
    });
    $("input[name='totalWeight']").each(function (i, v) {
        w.push(parseFloat(v.value));
    });
    $("input[name='_amount']").each(function (i, v) {
        a.push(parseFloat(v.value));
    });
    for (let i = 0; i < q.length; i++) {
        quantity += q[i];
        weight += q[i] * w[i];
        amount += q[i] * a[i];
    }
    $("#quantity").val(quantity);
    $("#weight").val(weight);
    $("#amount").val(amount);
}

function loadProduct(row) {
    if ($("[name='productId']:eq(0)").val() === "") {
        $("[name='productId']:eq(0)").val(row.productId);
        $("[name='productItems']:eq(0) :input.form-control:eq(0)").val(row.productName);
        $("[name='productItems']:eq(0) :input.form-control:eq(1)").val(row.productTypeName);
        $("[name='productItems']:eq(0) :input.form-control:eq(2)").val(row.quantityUnitName);
        $("[name='productItems']:eq(0) :input.form-control:eq(3)").val(row.count);
        $("[name='productItems']:eq(0) :input.form-control:eq(4)").val(row.weightUnitName);
        $("[name='productItems']:eq(0) :input.form-control:eq(5)").val(row.weight * row.count);
        $("[name='productItems']:eq(0) :input.form-control:eq(6)").val(row.price * row.count);
        $("#quantity").val(row.count);
        $("#weight").val(row.weight * row.count);
        $("#amount").val(row.price * row.count);
    } else {
        var table = $("[name='productItems']:eq(0)")
        $("[name='productItems']:last").after(table.clone());
        $("[name='productId']:last").val(row.productId);
        $("[name='productItems']:last :input.form-control:eq(0)").val(row.productName);
        $("[name='productItems']:last :input.form-control:eq(1)").val(row.productTypeName);
        $("[name='productItems']:last :input.form-control:eq(2)").val(row.quantityUnitName);
        $("[name='productItems']:last :input.form-control:eq(3)").val(row.count);
        $("[name='productItems']:last :input.form-control:eq(4)").val(row.weightUnitName);
        $("[name='productItems']:last :input.form-control:eq(5)").val(row.weight * row.count);
        $("[name='productItems']:last :input.form-control:eq(6)").val(row.price * row.count);
        $("#quantity").val(row.count + parseInt($("#quantity").val()));
        $("#weight").val(row.weight * row.count + parseInt($("#weight").val()));
        $("#amount").val(row.price * row.count + parseInt($("#amount").val()));
    }
}


function addProduct() {
    layer.open({
        type: 2,
        title: "选择物品",
        area: ['1000px', '600px'],
        content: "/factory/outboundOrder/selectProduct"
    })
}

function removeProduct() {
    if ($("[name='removeProductChecked']").length > $("[name='removeProductChecked']:checked").length) {
        $("[name='removeProductChecked']:checked").parents("[name='productItems']").remove();
    } else {
        $("[name='removeProductChecked']:checked").parents("[name='productItems']").find("input").val("");
    }
}

function loadDicts() {
    $.ajax({
        url: '/common/dict/list/outbound_state',
        success: function (data) {
            var html = "<option value=''>选择入库状态</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#outboundState").append(html);
            $("#outboundState").chosen({
                maxHeight: 200
            });
            $("#outboundState").val($('#outboundState_s').val());
            $("#outboundState").trigger("chosen:updated");
        }
    });
}

function uploadFile() {
    var formData = new FormData();
    formData.append("file", $("#file_u")[0].files[0]);
    formData.append("fileName", $("#file").val());
    $.ajax({
        type: "POST",
        url: '/common/sysFile/fileUpload',
        enctype: "multipart/form-data",
        data: formData,
        processData: false,
        contentType: false,
        success: function (r) {
            layer.msg(r.msg);
            $("#file").val(r.fileName);
            $("#file_u").after("<p style='color: #00B83F'>上传成功<span class='glyphicon glyphicon-ok'></span></p> ");
        },
        error: function () {
            console.log("ajaxError:uploadFile");
        }
    })
}

function update() {
    var formData = new FormData($('#signupForm')[0]);
    $.ajax({
        cache: true,
        type: "POST",
        url: "/factory/outboundOrder/update",
        data: formData,
        async: false,
        contentType: false,
        processData: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            var layerParent = top.layerParent;
            if (data.code == 0) {
                layerParent.layer.msg("操作成功");
                layerParent.reLoad();
                var index = top.layer.getFrameIndex(window.name);
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