
var prefix = "/saleManage/saleManage"
$(function() {
    loadState();
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                        onDblClickRow:function (row,$element) {//双击事件
                            // if(s_details_h!='hidden'){
                            details(row.saleId);
                            // }
                        },
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,

					           // name:$('#searchName').val(),
					           // username:$('#searchName').val()
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
								{
									checkbox : true
								},
																{
									visible:false,
									field : 'saleId', 
									title : '销售单ID' 
								},
																{
                                    visible:false,
									field : 'orderId', 
									title : '订单ID' 
								},
																{
									field : 'saleCode', 
									title : '销售发货单编号' 
								},
																{
									field : 'saleDate', 
									title : '销售发货日期' 
								},
																{
									visible:false,
									field : 'saleState', 
									title : '销售发货单状态' 
								},								{

                                field : 'saleStateName',
                                title : '销售发货单状态'
                                },
																{
									visible:false,
									field : 'saleManagerId', 
									title : '销售发货负责人' 
								},
																{
									visible:false,
									field : 'custermerId', 
									title : '订货单位ID' 
								},
                            		{

                                field : 'custermerName',
                                title : '订货单位'
                            		},
                            {

                                field : 'destination',
                                title : '收货地址'
                            },
																{
									visible:false,
									field : 'remark', 
									title : '备注' 
								},
																{
									visible:false,
									field : 'isDeleted', 
									title : '删除标记' 
								},
																{
									visible:false,
									field : 'creatDate', 
									title : '创建时间' 
								},
																{
									visible:false,
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.saleId
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.saleId
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
												+ row.saleId
												+ '\')"><i class="fa fa-key"></i></a> ';
										return e + d ;
									}
								} ]
					});
}
function reLoad() {
	var custermerName = $("#custermer").val();
	// var ids = new Array();
	// if(name!=""&&name!=null){
    // 	$.ajax({
	// 		async:false,
    //     	url: '/factory/custermerInformation/list',
	// 		type:"GET",
	// 		data:{
    //     	"custermerName":name
	// 			},
    //     	dateType:"JSON",
    //     	success: function (data) {
    //     		if(data.rows.length!=0){
    //       		 	for(var i = 0;i<data.rows.length;i++){
    //        			ids[i] = data.rows[i].custermerId;
	// 	   			}
	//
    //     		}
	// 		}
	//
   	//  		});
    // }
   var opt = {
        query: {
            custermerName:custermerName,
            saleDate:$("#saleDate").val(),
            saleCode:$("#saleCode").val(),
            saleState:$("#saleState").val()

        }
    }
	$('#exampleTable').bootstrapTable('refresh',opt);
}
function reset() {
        $("#custermer").val("");
		$("#saleDate").val("");
        $("#saleCode").val("");
    $("#saleState").val("");


    $('#exampleTable').bootstrapTable('refresh');
}
function details(id){
    top.layer.open({
        type : 2,
        title : '销售单详情',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '1000px', '600px' ],
        content : prefix + '/details/'+id, // iframe的url
        success:function (layero,index) {
            top.layerParent = window
        }
    });
    // window.open( prefix + '/details/'+id,"销售单详情","top=100,left=100,height=900,width=900,location=no")
}
// function loadCustermer(){
//     $.ajax({
//         url: '/factory/custermerInformation/list',
//         type:"GET",
//         dateType:"JSON",
//         success: function (data) {
//             var html = "";
//             // 加载数据
//             html+="<option value=''>所有</option>";
//             for (var i = 0; i < data.total; i++) {
//                 html += '<option value="' + data.rows[i].custermerId + '">' + data.rows[i].custermerName + '</option>'
//             }
//             $("#custermer").append(html);
//             $("#custermer").chosen({
//                 maxHeight: 200
//             });
//             $("#custermer").trigger("chosen:updated");
//         }
//     });
//
// }
function loadState(){
    $.ajax({
        url: '/common/dict/list/sale_state',
		// type:"GET",
		// dateType:"JSON",
        success: function (data) {
            var html = "";
            // 加载数据
            html += "<option value=''>所有</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#saleState").append(html);
            $("#saleState").chosen({
                maxHeight: 200
            });
            $("#saleState").trigger("chosen:updated");
        }
    });

}
function add() {

    // window.open( prefix + '/add',"添加","top=50,left=100,height=1000,width=1000,location=no")

    top.layer.open({
		type : 2,
		title : '增加销售单',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '1000px', '600px' ],
		content : prefix + '/add', // iframe的url
        success:function (layero,index) {
            top.layerParent = window
        },
		end:function () {
			location.reLoad();
        }
	});
}
function edit(id) {
	top.layer.open({
		type : 2,
		title : '编辑销售单',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : ['1000px', '600px'],
		content : prefix + '/edit/' + id, // iframe的url
        success:function (layero,index) {
            top.layerParent = window
        }
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'saleId' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
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
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['saleId'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}