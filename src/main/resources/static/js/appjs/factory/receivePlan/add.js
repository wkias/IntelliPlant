let prefix = '/factory/receivePlan';

$().ready(function () {
    validateRule();
    loadReceiveBatchName();
    loadState();
});

$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});

function loadDemander(id) {
    $('#exampleTable').bootstrapTable('destroy');
    $("#cutm-table").show();
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get',
                url: "/factory/contractManage/getVO/" + id,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true,
                dataType: "json",
                singleSelect: false,
                showColumns: false,
                responseHandler: function (data) {
                    let n = [];
                    if (data.contractType === "sale_contract") {
                        for (let i = 0; i < data.demanders.length; i++) {
                            n.push(data.demanders[i].trader);
                        }
                    } else {
                        for (let i = 0; i < data.suppliers.length; i++) {
                            n.push(data.suppliers[i].trader);
                        }
                    }
                    return n;
                },
                columns: [
                    {
                        field: 'custermerName',
                        title: '名称',
                    },
                    {
                        field: 'custermerId',
                        title: '选择',
                        align: 'center',
                        formatter: function (value, row, index) {
                            return "<a class='btn btn-primary btn-sm' title='选择' onclick='selectCustomer("
                                + JSON.stringify(row)
                                + ")'><i class='fa fa-ticket'></i></a> ";
                        }
                    }]
            });
}

function selectCustomer(row) {
    $("#demander").val(row.custermerId);
    $("#demanderName").val(row.custermerName);
    $("#cutm-table").hide();
}

function loadState() {
    var html = "";
    $.ajax({
        url: '/common/dict/list/receive_state',
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#state").append(html);
            $("#state").chosen({
                maxHeight: 200
            });
            $("#state").trigger("chosen:updated");
            $('#state').on('change', function (e, params) {
            });
        }
    });
}

function loadReceiveBatchName() {
    var html = "";
    $.ajax({
        url: '/common/dict/list/receive_batch',
        success: function (data) {
            for (let i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#receiveBatch").append(html);
            $("#receiveBatch").chosen({
                maxHeight: 200
            });
            $("#receiveBatch").trigger("chosen:updated");
            // 点击事件
            $('#receiveBatch').on('change', function (e, params) {
            });
        }
    });
}

function contractSelect() {
    layer.open({
        type: 2,
        title: '选择',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/contractSelect' // iframe的url
    });
}

function save() {
    $.ajax({
        cache: true,
        type: "POST",
        url: "/factory/receivePlan/save",
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