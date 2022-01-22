$().ready(function () {
    validateRule();
});

$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});

function save() {
    $.ajax({
        cache: true,
        type: "POST",
        url: "/factory/productInspection/save",
        data: $('#signupForm').serialize(),// 你的formid
        async: false,
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

function loadDetails() {
    $('details-table').html('产品明细');
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: "factory/productManage/productDefinition/details/" + $('#productInspectionId').val(), // 服务器数据的加载地址
                async: false,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                singleSelect: false, // 设置为true将禁止多选
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                columns: []
            });
}

function selectProductTable() {
    console.log("test top layer open")
    top.layer.open({
        type: 2,
        title: "选择关联表",
        area: ['1000px', '600px'],
        content: "/factory/productInspection/productDefinition",
        success: function (layero, index) {
            top.layerParent = window
        }
    });
};

function openProductSelect(obj) {
    console.log("test top layer open")
    top.layer.open({
        type: 2,
        title: '增加',
        area: ['1000px', '600px'],
        content: "/factory/productInspection/inspectionItemsSelect", // iframe的url
    success: function (layero, index) {
        top.layerParent = window
    }
    });
}

function addDetail() {
    var $table = $("[name='detailItem']:last");
    var $tableClone = $table.clone();
    $tableClone.find("input").val("")
    $table.after($tableClone)
}

function delDetail() {
    if ($("[name='detailCheck']").length > $("[name='detailCheck']:checked").length) {
        $("[name='detailCheck']:checked").parents("[name='detailItem']").remove();
    } else {
        $("[name='detailCheck']:checked").parents("[name='detailItem']").find("input").val("");
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
                required: icon + "请输入姓名"
            }
        }
    })
}