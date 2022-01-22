var prefix = "/reimburse/reimbursement";

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
    let c = [];//待报销金额
    let l = [];//待还款金额
    let consume = 0;
    let loan = 0;
    let amount = 0;
    $("input[name='consumeAmount']").each(function (i, v) {
        c.push(parseInt(v.value));
    });
    $("input[name='loanAmount']").each(function (i, v) {
        l.push(parseFloat(v.value));
    });
    for (let i = 0; i < c.length; i++) {
        consume += c[i];
    }
    for (let i = 0; i < l.length; i++) {
        loan += l[i];
    }
    // if (loan == null || loan === '' || loan === undefined) {
    //     loan = 0;
    // }
    amount = consume - loan;
    $("#amountToBeReimbursed").val(consume);
    $("#amountToBeRepaid").val(loan);
    $("#cumulativeReimbursementAmount").val(amount);
}

function addConsume() {
    var table = $("[name='consume']:eq(0)")
    $("[name='consume']:last").after(table.clone());
    $("[name='consume']:last :input.form-control:eq(0)").val("");
    $("[name='consume']:last :input.form-control:eq(1)").val("");
    $("[name='consume']:last :input.form-control:eq(2)").val("");
    $("[name='consume']:last :input.form-control:eq(3)").val("");
    $("[name='consume']:last :input.form-control:eq(4)").val("");
    $("[name='consume']:last :input.form-control:eq(5)").val("");
    $("[name='consume']:last :input.form-control:eq(6)").val("");
    $("[name='consume']:last :input.form-control:eq(7)").val("");
}

function removeConsume() {
    if ($("[name='removeConsumeChecked']").length > $("[name='removeConsumeChecked']:checked").length) {
        $("[name='removeConsumeChecked']:checked").parents("[name='consume']").remove();
    } else {
        $("[name='removeConsumeChecked']:checked").parents("[name='consume']").find("input").val("");
    }
    refreshData();
}

function loadLoan(row) {
    if ($("[name='loanId']:eq(0)").val() === "") {
        $("[name='loanId']:eq(0)").val(row.loanApplicationId);
        $("[name='loan']:last :input.form-control:eq(1)").val(row.loanCode);
        $("[name='loan']:last :input.form-control:eq(2)").val(row.loanState);
        $("[name='loan']:last :input.form-control:eq(3)").val(row.loanDate);
        $("[name='loan']:last :input.form-control:eq(4)").val(row.loanPurpose);
        $("[name='loan']:last :input.form-control:eq(5)").val(row.loanAccount);
    } else {
        var table = $("[name='loan']:eq(0)")
        $("[name='loan']:last").after(table.clone());
        $("[name='loanId']:last").val(row.loanApplicationId);
        $("[name='loan']:last :input.form-control:eq(1)").val(row.loanCode);
        $("[name='loan']:last :input.form-control:eq(2)").val(row.loanState);
        $("[name='loan']:last :input.form-control:eq(3)").val(row.loanDate);
        $("[name='loan']:last :input.form-control:eq(4)").val(row.loanPurpose);
        $("[name='loan']:last :input.form-control:eq(5)").val(row.loanAccount);
    }
    refreshData();
}

function removeLoan() {
    if ($("[name='removeLoanChecked']").length > $("[name='removeLoanChecked']:checked").length) {
        $("[name='removeLoanChecked']:checked").parents("[name='loan']").remove();
    } else {
        $("[name='removeLoanChecked']:checked").parents("[name='loan']").find("input").val("");
    }
    refreshData();
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

function selectLoan() {
    layer.open({
        type: 2,
        title: '选择',
        maxmin: true,
        shadeClose: false,
        area: ['800px', '520px'],
        content: prefix + '/loan'
    });
}

function selectAccount() {
    layer.open({
        type: 2,
        title: '选择',
        maxmin: true,
        shadeClose: false,
        area: ['800px', '520px'],
        content: prefix + '/account'
    });
}

function loadAccount(row) {
    $("#payee").val(row.collectionAccount);
    $("#bank").val(row.bankName);
    $("#bankAccount").val(row.bankAccount);
}

function loadDicts() {
    $.ajax({
        url: '/common/dict/list/reimburse_type',
        success: function (data) {
            var html = "<option value=''>选择类型</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#invoiceType").append(html);
            $("#invoiceType").val('');
            $("#invoiceType").chosen({
                maxHeight: 200
            });
            $("#invoiceType").trigger("chosen:updated");
        }
    });
}

function saveDraft() {
    $("#isDraft").val(1);
    save();
}

function save() {
    if ($("#cumulativeReimbursementAmount").val() < 0) {
        layer.msg("报销费用不足以冲抵借款");
        return;
    }
    $.ajax({
        cache: true,
        type: "POST",
        url: "/reimburse/reimbursement/save",
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
                required: icon + "请输入姓名"
            }
        }
    })
}