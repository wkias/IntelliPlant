var prefix = "/warehouseManagement/purchaseOrder"
$(function () {
    load();
    loadDict();
});
function loadDict(){
    $.ajax({
        url: '/common/dict/list/purchase_business_type',
        success: function (data) {
            var html = "";
            // 加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#businessType").append(html);
            $("#businessType").chosen({
                maxHeight: 200
            });
            $("#businessType").trigger("chosen:updated");
        }
    });
}
function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset
                        // name:$('#searchName').val(),
                        // username:$('#searchName').val()
                    };
                },
                onDblClickRow: function(row,$element){
                        details(row.purchaseId);
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'auditState',
                        title: '审核状态',
						formatter: function(value,row,index) {
							var a = "";
							if(value == "已拒绝") {
								var a = '<span style="color:#c12e2a;"><i class="fa fa-times-circle-o" aria-hidden="true"></i>'+value+'</span>';
							}else if(value == "已通过"){
								var a = '<span style="color:#3e8f3e"><i class="fa fa-check-circle-o" aria-hidden="true"></i>'+value+'</span>';
							}else{
								var a ='<span"><i class="glyphicon glyphicon-pencil" aria-hidden="true"></i>'+value+'</span>';
							}
							return a;
						},
                    },
                    {
                        field: 'purchaseStateName',
                        title: '采购状态'
                    },
                    {
                        field: 'orderCode',
                        title: '订单编号'
                    },

                    {
                        field: 'applyDeptName',
                        title: '申请采购部门'
                    },
                    {
                        field: 'businessTypeName',
                        title: '业务类型'
                    },
                    {
                        field: 'arrivalDate',
                        title: '请求到货日期'
                    },
                    {
                        field: 'dutyPersonName',
                        title: '采购负责人'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
						formatter: function (value, row, index) {
							var e = '<a class="btn btn-primary btn-sm " href="#" mce_href="#" title="审批记录" onclick="processRecord(\''
								+ row.purchaseId
								+ '\')"><i class="fa fa-tasks">审批记录</i></a> ';
                            var f = '<a class="btn btn-primary btn-sm " href="#" mce_href="#" title="审批记录" onclick="edit(\''
                                + row.purchaseId
                                + '\')"><i class="fa fa-edit">编辑</i></a> ';
							return  f;
						}
                    }]
            });
}
function details(id){
    top.layer.open({
        type:2,
        tile:"详情",
        maxmin:true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content:prefix+'/details/'+id,
        success:function (layero,index) {
            top.layerParent = [];
            top.layerParent.push(window);
        }
    })
  //  window.open( prefix + '/details/'+id,"","location=no")
}
var opt = {};
function reLoad() {//搜索（带参刷新）
    //$('#exampleTable').bootstrapTable('refresh');
    opt = {
        query: {
            businessType: $('#businessType').val(),
            deptName: $('#deptName').val(),
            orderCode: $('#orderCode').val()//为方便url传参，模糊查询在mapper中实现
        }
    }
    $('#exampleTable').bootstrapTable('refresh',opt);
}
function reset() {//重置
    $('#businessType').val("");
    $('#deptName').val("");
    $('#orderCode').val("");
    $('#exampleTable').bootstrapTable('refresh')
}
function processRecord(billId){
    layer.open({
        type: 2,
        title: '审批记录',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['1000px', '600px'],
        content: "/activiti/process/processRecord/"+billId // iframe的url
    });
}
function add() {
    top.layer.open({
        type: 2,
        title: '增加',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['1000px', '600px'],
        content: prefix + '/add' ,// iframe的url
        success:function (layero,index) {
            top.layerParent = [];
            top.layerParent.push(window);
        }
    });
}

function edit(id) {
    top.layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['1000px', '600px'],
        content: prefix + '/edit/' + id ,// iframe的url
        success:function (layero,index) {
            top.layerParent = [];
            top.layerParent.push(window);
        }
    });
   // window.open( prefix + '/edit/'+id,"","location=no")
}

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'purchaseId': id
            },
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}

function resetPwd(id) {
}

function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function (i, row) {
            ids[i] = row['purchaseId'];
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: prefix + '/batchRemove',
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function () {

    });
}