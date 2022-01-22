$().ready(function() {
    setDate();
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});

function singleDate() {
    $("#period").val();
}
function setDate() {
    var nowDate=new Date();
    var nowMonth=nowDate.getMonth()+1;   //月份和日期若为小于10的数要做处理
    var nowDay=nowDate.getDate();
    if((nowDate.getMonth()+1)<10){
        nowMonth="0"+nowMonth;
    }
    if(nowDate.getDate()<10){
        nowDay='0'+nowDay;
    }
    var dateVal=nowDate.getFullYear()+"-"+nowMonth+"-"+nowDay;    //显示格式：2018/07/14
    $("#period").val(dateVal);
}
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/productManage/dailyProductionManage/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
            var layerParent = top.layerParent;
            layerParent.layer.alert(request.msg);
		},
		success : function(data) {
            var layerParent = top.layerParent;
            if (data.code == 0) {
                layerParent.layer.msg("操作成功");
                var index = top.layer.getFrameIndex(window.name); // 获取窗口索引
                top.layer.close(index);
            } else {
                layerParent.layer.alert(data.msg)
            }


        }
	});

}
var lastClick;
function getProcess(obj) {
	lastClick = obj;
    top.layer.open({
        async:false,
        type : 2,
        title : '工序列表',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '1000px', '600px' ],
        content : '/productManage/dailyProductionManage/processList',// iframe的url
        success:function (layero,index) {
            top.layerParent = window

        }
    });

}

function loadProcess(row) {
    console.log(row);

    $(lastClick).find("[name='processId']").val(row.processId);
    $(lastClick).find("[name='processName']").val(row.processName);

}
function getProduct(obj) {
	lastClick =obj;
    top.layer.open({
        async:false,
        type : 2,
        title : '部件列表',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '1000px', '600px' ],
        content : '/productManage/dailyProductionManage/productList',// iframe的url
        success:function (layero,index) {
            top.layerParent = window

        }
    });

}

function loadProduct(row) {
    console.log(row);

    $(lastClick).find("[name='productId']").val(row.productId);
    $(lastClick).find("[name='productName']").val(row.productName);
    $(lastClick).nextAll().find("[name='model']").val(row.model);
    $(lastClick).nextAll().find("[name='quantityUnit']").val(row.quantityUnit);
    $(lastClick).nextAll().find("[name='quantityUnitName']").val(row.quantityUnitName);

}

function del() {



    $("input[name='id']:checked").each(function() { // 遍历选中的checkbox
        var n = $(this).parents("tr").index();  // 获取checkbox所在行的顺序
		if(n!=1){
        $("[name='all']").find("tr:eq("+n+")").remove();
		}else{
            var tr = $("[name='detail']").eq(0);
            $(tr).find("[name='processId']").val("");
            $(tr).find("[name='processName']").val("");
            $(tr).find("[name='productId']").val("");
            $(tr).find("[name='productName']").val("");
            $(tr).nextAll().find("[name='model']").val("");
            $(tr).nextAll().find("[name='quantityUnit']").val("");
            $(tr).nextAll().find("[name='quantityUnitName']").val("");
        }
    });
}
function add() {
	var tr = $("[name='detail']").eq(0);
	var t = tr.clone();
    $(t).find("[name='processId']").val("");
    $(t).find("[name='processName']").val("");
    $(t).find("[name='productId']").val("");
    $(t).find("[name='productName']").val("");
    $(t).find("[name='model']").val("");
    $(t).find("[name='quantityUnit']").val("");
    $(t).find("[name='quantityUnitName']").val("");
    $(t).find("[name='count']").val("");

    $("[name='all']:last").append(t);

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			}
		}
	})
}