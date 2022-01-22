$().ready(function() {
	validateRule();
    loadProcess();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});


function update() {
    var formData = new FormData($('#signupForm')[0])
    $.ajax({
        cache : true,
        type : "POST",
        url : "/productManage/processInspection/update",
        data : formData,// 你的formid
        async : false,
        dateType:"json",
        contentType: false,//必须
        processData: false,//必须
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
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
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入名字"
			}
		}
	})
}

function addItems() {
    var $table=$("[name='items']:last");
    var $tableClone=$table.clone();
    $tableClone.find("input").val("")
    $table.after($tableClone)

}
function delItems() {
    console.log("delItems")
    if(  $("[name='itemsCheck']").length>$("[name='processCheck']:checked").length){
        $("[name='itemsCheck']:checked").parents("[name='items']").remove();
    }else{
        $("[name='itemsCheck']:checked").parents("[name='items']").find("input").val("");
    }

}
//打开质检项目选择
var lastClickedItem;
function openItemsSelect(obj){
    lastClickedItem=obj;
    layer.open({
        type : 2,
        title : '增加',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '500px' ],
        content : "/productManage/processInspection/itemsSelect" // iframe的url
    });
}
function loadItems(row) {
    console.log(row);
    $(lastClickedItem).find("[name='inspectionItemsId']").val(row.inspectionItemsId);
    $(lastClickedItem).find("[name='projectName']").val(row.projectName);
    $(lastClickedItem).find("[name='valueType']").val(row.valueTypeName);
    $(lastClickedItem).find("[name='unit']").val(row.unitName);
    $(lastClickedItem).find("[name='rangeThreshold']").val(row.rangeThreshold);
    $(lastClickedItem).find("[name='description']").val(row.description);
}

function loadProcess() {
    $.ajax({
        url: '/productManage/processInspection/processList',
        success: function (data) {
            var html = "";
            // 加载数据
            html += "<option value=''>选择工序名称</option>";
            for (var i = 0; i < data.length; i++) {
                if(data[i].state=='1') {
                    html += '<option value="' + data[i].processId + '">' + data[i].processName + '</option>'
                }
            }
            $("#process").append(html);
            $("#process").chosen({
                maxHeight: 200
            });
            $("#process").val($("#oldProcessId").val());
            $("#process").trigger("chosen:updated");
        }
    });
}