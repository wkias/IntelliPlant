$().ready(function () {
    validateRule();
    loadDicts();
});
$.validator.setDefaults({
    submitHandler: function () {
        save();
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

function loadOtherGoods(row) {
    let quantity = 1;
    if ($("[name='productId']:eq(0)").val() === "") {
        $("[name='productId']:eq(0)").val(row.productId);
        $("[name='productItems']:last :input.form-control:eq(0)").val(row.productName);
        $("[name='productItems']:last :input.form-control:eq(1)").val(row.quantityUnitName);
        $("[name='productItems']:last :input.form-control:eq(2)").val(quantity);
        $("[name='productItems']:last :input.form-control:eq(3)").val(row.weightUnitName);
        $("[name='productItems']:last :input.form-control:eq(4)").val(row.weight * quantity);
        $("[name='productItems']:last :input.form-control:eq(5)").val(row.price * quantity);
        $("#quantity").val(quantity);
        $("#weight").val(row.weight * quantity);
        $("#amount").val(row.price * quantity);
    } else {
        var table = $("[name='productItems']:eq(0)")
        $("[name='productItems']:last").after(table.clone());
        $("[name='productId']:eq(0)").val(row.productId);
        $("[name='productItems']:last :input.form-control:eq(0)").val(row.productName);
        $("[name='productItems']:last :input.form-control:eq(1)").val(row.quantityUnitName);
        $("[name='productItems']:last :input.form-control:eq(2)").val(quantity);
        $("[name='productItems']:last :input.form-control:eq(3)").val(row.weightUnitName);
        $("[name='productItems']:last :input.form-control:eq(4)").val(row.weight * quantity);
        $("[name='productItems']:last :input.form-control:eq(5)").val(row.price * quantity);
        $("#quantity").val(quantity + parseInt($("#quantity").val()));
        $("#weight").val(row.weight * quantity + parseInt($("#weight").val()));
        $("#amount").val(row.price * quantity + parseInt($("#amount").val()));
    }
}

function loadOtherProduct(row) {
    let quantity = 1;
    if ($("[name='productId']:eq(0)").val() === "") {
        $("[name='productId']:eq(0)").val(row.productId);
        $("[name='productItems']:last :input.form-control:eq(0)").val(row.productName);
        $("[name='productItems']:last :input.form-control:eq(1)").val(row.unitName);
        $("[name='productItems']:last :input.form-control:eq(2)").val(quantity);
        $("[name='productItems']:last :input.form-control:eq(3)").val(row.weightUnitName);
        $("[name='productItems']:last :input.form-control:eq(4)").val(row.weight * quantity);
        $("[name='productItems']:last :input.form-control:eq(5)").val(row.price * quantity);
        $("#quantity").val(quantity);
        $("#weight").val(row.weight * quantity);
        $("#amount").val(row.price * quantity);
    } else {
        var table = $("[name='productItems']:eq(0)")
        $("[name='productItems']:last").after(table.clone());
        $("[name='productId']:eq(0)").val(row.productId);
        $("[name='productItems']:last :input.form-control:eq(0)").val(row.productName);
        $("[name='productItems']:last :input.form-control:eq(1)").val(row.unitName);
        $("[name='productItems']:last :input.form-control:eq(2)").val(quantity);
        $("[name='productItems']:last :input.form-control:eq(3)").val(row.weightUnitName);
        $("[name='productItems']:last :input.form-control:eq(4)").val(row.weight * quantity);
        $("[name='productItems']:last :input.form-control:eq(5)").val(row.price * quantity);
        $("#quantity").val(quantity + parseInt($("#quantity").val()));
        $("#weight").val(row.weight * quantity + parseInt($("#weight").val()));
        $("#amount").val(row.price * quantity + parseInt($("#amount").val()));
    }
}

function loadProduct(row) {
    if ($("[name='productId']:eq(0)").val() === "") {
        $("[name='productId']:eq(0)").val(row.productId);
        $("[name='productItems']:last :input.form-control:eq(0)").val(row.productName);
        $("[name='productItems']:last :input.form-control:eq(1)").val(row.quantityUnitName);
        $("[name='productItems']:last :input.form-control:eq(2)").val(row.isDeleted);
        $("[name='productItems']:last :input.form-control:eq(3)").val(row.weightUnitName);
        $("[name='productItems']:last :input.form-control:eq(4)").val(row.weight * row.isDeleted);
        $("[name='productItems']:last :input.form-control:eq(5)").val(row.price * row.isDeleted);
        $("#quantity").val(row.isDeleted);
        $("#weight").val(row.weight * row.isDeleted);
        $("#amount").val(row.price * row.isDeleted);
    } else {
        var table = $("[name='productItems']:eq(0)")
        $("[name='productItems']:last").after(table.clone());
        $("[name='productId']:last").val(row.productId);
        $("[name='productItems']:last :input.form-control:eq(0)").val(row.productName);
        $("[name='productItems']:last :input.form-control:eq(1)").val(row.quantityUnitName);
        $("[name='productItems']:last :input.form-control:eq(2)").val(row.isDeleted);
        $("[name='productItems']:last :input.form-control:eq(3)").val(row.weightUnitName);
        $("[name='productItems']:last :input.form-control:eq(4)").val(row.weight * row.isDeleted);
        $("[name='productItems']:last :input.form-control:eq(5)").val(row.price * row.isDeleted);
        $("#quantity").val(row.isDeleted + parseInt($("#quantity").val()));
        $("#weight").val(row.weight * row.isDeleted + parseInt($("#weight").val()));
        $("#amount").val(row.price * row.isDeleted + parseInt($("#amount").val()));
    }
}

function addProduct() {
    let content = "";
    if ($('#associatedTableId').val() == '') {
        content = "/factory/godownEntry/selectOthers";
    } else {
        content = "/factory/godownEntry/selectProduct";
    }
    layer.open({
        type: 2,
        title: "选择物品",
        area: ['1000px', '600px'],
        content: content
    })
}

function removeProduct() {
    $('#quantity').val($('#quantity').val() - $("[name='removeProductChecked']:checked:input.form-control:eq(2)").val());
    $('#weight').val($('#weight').val() - $("[name='removeProductChecked']:checked:input.form-control:eq(4)").val());
    $('#amount').val($('#amount').val() - $("[name='removeProductChecked']:checked:input.form-control:eq(5)").val());
    if ($("[name='removeProductChecked']").length > $("[name='removeProductChecked']:checked").length) {
        $("[name='removeProductChecked']:checked").parents("[name='productItems']").remove();
    } else {
        $("[name='removeProductChecked']:checked").parents("[name='productItems']").find("input").val("");
    }
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

var selectAssociatedTable = function () {
    layer.open({
        type: 2,
        title: "选择关联表",
        area: ['1000px', '450px'],
        content: "/factory/godownEntry/selectPurchaseOrder"
    });
};

var openUser = function () {
    layer.open({
        type: 2,
        title: "选择人员",
        area: ['1000px', '650px'],
        content: "/sys/user/select"
    });
};

function loadUserDO(user) {
    $("#manager").val(user.userId);
    $("#managerName").val(user.name);
}

function loadDicts() {
    $.ajax({
        url: '/common/dict/list/in_associated_table_type',
        success: function (data) {
            let html = "";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#associatedTableType").append(html);
            $("#associatedTableType").val(1);
            $("#associatedTableType").chosen({
                maxHeight: 200
            });
            $("#associatedTableType").trigger("chosen:updated");
        }
    });
    $.ajax({
        url: '/common/dict/list/godown_entry_state',
        success: function (data) {
            var html = "<option value=''>选择入库状态</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#godownEntryState").append(html);
            $("#godownEntryState").chosen({
                maxHeight: 200
            });
            $("#godownEntryState").trigger("chosen:updated");
        }
    });
    $.ajax({
        url: '/common/dict/list/repository',
        success: function (data) {
            let html = "";
            html += "<option value=''>选择仓库</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#repository").append(html);
            $("#repository").chosen({
                maxHeight: 200
            });
            $("#repository").trigger("chosen:updated");
        }
    });
}

function save() {
    var formData = new FormData($('#signupForm')[0]);
    $.ajax({
        cache: true,
        type: "POST",
        url: "/factory/godownEntry/save",
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
                required: icon + "请输入姓名"
            }
        }
    })
}