$().ready(function () {
    validateRule();
});

$.validator.setDefaults({
    submitHandler: function () {
        update();
    }
});

function update() {
    $.ajax({
        cache: true,
        type: "POST",
        url: "/factory/productInspection/update",
        data: $('#signupForm').serialize(),// 你的formid
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            console.log("edit close tsest")
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

var lastClickedItem;

function openProductSelect(obj) {
    lastClickedItem = obj;
    console.log("test top layer open")
    top.layer.open({
        type: 2,
        title: '增加',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['1000px', '600px'],
        content: "/factory/productInspection/inspectionItemsSelect", // iframe的url
        success: function (layero, index) {
            top.layerParent = window
        }
    });
}

function loadProcess(row) {
    console.log(row);
    $(lastClickedItem).find("[name='inspectionItemsId']").val(row.inspectionItemsId);
    $(lastClickedItem).find("[name='projectName']").val(row.projectName);
    $(lastClickedItem).find("[name='valueType']").val(row.valueType);
    $(lastClickedItem).find("[name='unit']").val(row.unit);
    $(lastClickedItem).find("[name='rangeThreshold']").val(row.rangeThreshold);
    $(lastClickedItem).find("[name='description']").val(row.description);
}

function addProcess() {
    var $table = $("[name='detailItem']:last");
    var $tableClone = $table.clone();
    $tableClone.find("input").val("")
    $table.after($tableClone)
}

function delProcess() {
    console.log("delPorcess")
    if ($("[name='processCheck']").length > $("[name='processCheck']:checked").length) {
        $("[name='processCheck']:checked").parents("[name='detailItem']").remove();
    } else {
        $("[name='processCheck']:checked").parents("[name='detailItem']").find("input").val("");
    }
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