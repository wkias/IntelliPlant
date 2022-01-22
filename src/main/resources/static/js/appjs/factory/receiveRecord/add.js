$().ready(function () {
    validateRule();
    loadType();
    loadMethod();
});

$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});

let prefix = '/factory/receiveRecord';

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
    loadReceiveBatchName();
}

function contractSelect() {
    layer.open({
        type: 2,
        title: '选择',
        maxmin: true,
        shadeClose: false,
        area: ['800px', '520px'],
        content: prefix + '/contractSelect',
    });
}

let userSelect = function () {
    layer.open({
        type: 2,
        title: "选择人员",
        area: ['1000px', '700px'],
        content: "/sys/user/select"
    });
};

function loadUserDO(user) {
    $('#payee').val(user.userId);
    $('#payeeName').val(user.name);
}

function loadType() {
    var html = '<option value="">选择回款类型</option>';
    $.ajax({
        url: '/common/dict/list/receive_type',
        success: (function (data) {
            for (let i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>';
            }
            $('#receiveType').append(html);
            $('#receiveType').val("");
            $("#receiveType").chosen({
                maxHeight: 200
            });
            $("#receiveType").trigger("chosen:updated");
            $('#receiveType').on('change', function (e, params) {
            });
        })
    })
}

function loadReceiveBatchName(id) {
    var html = '<option value="">选择计划回款期次</option>';
    $.ajax({
        url: '/factory/receivePlan/list?contractId=' + $("#contractId").val() + '?demander=' + $("#demander").val(),
        success: function (data) {
            for (let i = 0; i < data.rows.length; i++) {
                html += '<option value="' + data.rows[i].receiveBatch + '">' + data.rows[i].receiveBatchName + '</option>';
            }
            $("#planBatch").append(html);
            $("#planBatch").val("");
            $("#planBatch").chosen({
                maxHeight: 200
            });
            $("#planBatch").trigger("chosen:updated");
            $('#planBatch').on('change', function (e, params) {
            });
            if (data.rows.length == 0) {
                $("#planB").remove();
            }
        }
    });
}

function loadMethod() {
    var html = '<option value="">选择付款方式</option>';
    $.ajax({
        url: '/common/dict/list/payment_method',
        success: (function (data) {
            for (let i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>';
            }
            $('#paymentMethod').append(html);
            $("#paymentMethod").val("");
            $("#paymentMethod").chosen({
                maxHeight: 200
            });
            $("#paymentMethod").trigger("chosen:updated");
            $('#paymentMethod').on('change', function (e, params) {
            });
        })
    })
}

function save() {
    $.ajax({
        cache: true,
        type: "POST",
        url: "/factory/receiveRecord/save",
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
    let icon = "<i class='fa fa-times-circle'></i> ";
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