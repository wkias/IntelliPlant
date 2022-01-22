$().ready(function () {
    validateRule();
    loadDicts();
    setTime();
});

$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});

function loadDicts() {
    $.ajax({
        url: '/common/dict/list/contract_type',
        success: function (data) {
            var html = "";
            // 加载数据
            html += "<option value=''>所有</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#contractType").append(html);
            $("#contractType").chosen({
                maxHeight: 200
            });
            $("#contractType").trigger("chosen:updated");
        }
    });
    $.ajax({
        url: '/common/dict/list/contract_state',
        success: function (data) {
            var html = "";
            // 加载数据
            html += "<option value=''>所有</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#state").append(html);
            $("#state").chosen({
                maxHeight: 200
            });
            $("#state").trigger("chosen:updated");
        }
    });
}

function setTime() {
    $("#createTime").val(dateFormat("yyyy-MM-dd HH:mm:ss", new Date()), 1000);
}

//setInterval(time, 1000)

var lastClickedInput;
function selectContractPerson(obj) {
    lastClickedInput=obj;
    var $traderId=obj.parents("[name='supplierItem']").find("[name='supplierId']");
    if($traderId.length==0){
        $traderId=obj.parents("[name='demanderItem']").find("[name='demanderId']");
    }
    layer.open({
        type: 2,
        title: "选择联系人",
        area: ['1000px', '600px'],
        content: "/factory/contractManage/contactPerson/"+$traderId.val()
    })
}
function loadContactPerson(row) {
    lastClickedInput.val(row.name);
    lastClickedInput.parents("div:first").find("input:eq(1)").val(row.contactPersonId);
}
function save() {
    var formData = new FormData($('#signupForm')[0])
    $.ajax({
        cache: true,
        type: "POST",
        url: "/factory/contractManage/save",
        data: formData,// 你的formid
        async: false,
        contentType: false,//必须
        processData: false,//必须
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
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

//打开人员选择
var openUser = function () {
    top.layer.open({
        type: 2,
        title: "选择人员",
        area: ['1000px', '600px'],
        content: "/sys/user/select",
        success(layero,index){
            top.layerParent=[]
            top.layerParent.push(window);
        }
    })
};

// function loadUser(userIds,userNames){
// 	$("#dutyUserId").val(userIds[0]);
// 	$("#dutyUserName").val(userNames[0]);
// }
//接收完整对象
function loadUserDO(user) {
    console.log(user);
    $("#dutyUserId").val(user.userId);
    $("#dutyUserName").val(user.name);
    $("#dutyDeptId").val(user.deptId);
    $("#dutyDeptName").val(user.deptName);
}

function loadTrader(trader, isSupplier) {
    if($("[name='supplierId'][value="+trader.custermerId+"]").length!=0
        ||$("[name='demanderId'][value="+trader.custermerId+"]").length!=0){
        return
    }
    console.log($("[name='supplierId'][value="+trader.custermerId+"]"))
    //console.log(trader);
    var eleName;
    if(isSupplier){
    	eleName="supplier";
	}else {
    	eleName="demander";
	}
	if ($("[name='"+eleName+"Id']:eq(0)").val() == "") {
	    //隐藏域
		$("[name='"+eleName+"Id']:eq(0)").val(trader.custermerId);
        $("[name='"+eleName+"ContactPersonId']:eq(0)").val(trader.mainContactPerson);
        //b标签文本
        let $text=$("[name='"+eleName+"Item']:eq(0) b");
        $("[name='"+eleName+"Item']:eq(0) b").text(""+(isSupplier?"供应商":"需求商")+"1");
        //表单显示
		$("[name='"+eleName+"Item']:eq(0) :input.form-control:eq(0)").val(trader.custermerName);
		$("[name='"+eleName+"Item']:eq(0) :input.form-control:eq(1)").val(trader.deliverAddress);
		$("[name='"+eleName+"Item']:eq(0) :input.form-control:eq(2)").val(trader.name);
		$("[name='"+eleName+"Item']:eq(0) :input.form-control:eq(3)").val(trader.custermerTel);
		$("[name='"+eleName+"Item']:eq(0) :input.form-control:eq(4)").val(trader.taxpayerIdNumbers);
		$("[name='"+eleName+"Item']:eq(0) :input.form-control:eq(5)").val(trader.address);
		$("[name='"+eleName+"Item']:eq(0) :input.form-control:eq(6)").val(trader.bank);
		$("[name='"+eleName+"Item']:eq(0) :input.form-control:eq(7)").val(trader.bankAccount);
	} else {
		var table = $("[name='"+eleName+"Item']:eq(0)")
		$("[name='"+eleName+"Item']:last").after(table.clone());
        //隐藏域
		$("[name='"+eleName+"Id']:last").val(trader.custermerId);
        $("[name='"+eleName+"ContactPersonId']:last").val(trader.mainContactPerson);
        //b标签文本
        let $text=$("[name='"+eleName+"Item']:last b");
        $("[name='"+eleName+"Item']:last b").text(""+(isSupplier?"供应商":"需求商")+( $("[name='"+eleName+"Item'] b").index($text)+1)+"");
        //表单显示
		$("[name='"+eleName+"Item']:last :input.form-control:eq(0)").val(trader.custermerName);
		$("[name='"+eleName+"Item']:last :input.form-control:eq(1)").val("");
		$("[name='"+eleName+"Item']:last :input.form-control:eq(2)").val(trader.name);
		$("[name='"+eleName+"Item']:last :input.form-control:eq(3)").val(trader.custermerTel);
		$("[name='"+eleName+"Item']:last :input.form-control:eq(4)").val(trader.taxpayerIdNumbers);
		$("[name='"+eleName+"Item']:last :input.form-control:eq(5)").val(trader.address);
		$("[name='"+eleName+"Item']:last :input.form-control:eq(6)").val(trader.bank);
		$("[name='"+eleName+"Item']:last :input.form-control:eq(7)").val(trader.bankAccount);
	}
}

function addSup() {
    layer.open({
        type: 2,
        title: "选择供应商",
        area: ['1000px', '600px'],
        content: "/factory/contractManage/traderList/Sup"
    })
}

function addDem() {
    layer.open({
        type: 2,
        title: "选择供应商",
        area: ['1000px', '600px'],
        content: "/factory/contractManage/traderList/Dem"
    })
}

function delSup() {
    if(  $("[name='delSupChecked']").length>$("[name='delSupChecked']:checked").length){
        $("[name='delSupChecked']:checked").parent().remove();
    }else{
        $("[name='delSupChecked']:checked").parent().find("input").val("");
    }
}
function delDem() {
    if(  $("[name='delDemChecked']").length>$("[name='delDemChecked']:checked").length){
        $("[name='delDemChecked']:checked").parent().remove();
    }else{
        $("[name='delDemChecked']:checked").parent().find("input").val("");
    }
}