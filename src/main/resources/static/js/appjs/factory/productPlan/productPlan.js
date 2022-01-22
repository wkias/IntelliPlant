
var prefix = "/factory/productPlan"
$(function() {
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
						sidePagination: "client", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						responseHandler: function(data){
							return data.rows;
						},
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								// limit: params.limit,
								// offset:params.offset
					           // name:$('#searchName').val(),
					           // username:$('#searchName').val()
							};
						},
						onDblClickRow:function (row,$element) {//双击事件
							detail(row.orderContentDO.contentId);
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
									field : 'planState',
									title : '计划状态'
								},
																{
									field : 'planCode', 
									title : '计划编号' 
								},
																{
									title : '产品名称',
									formatter:function (value, row, index) {
										if(row.productDefinitionDO!=null){
											return row.productDefinitionDO.productName
										}
									}
								},

																{
									title : '完成进度',
									formatter:function (value, row, index) {
										if(row.orderContentDO!=null){
											//小数转换为百分比 保留两位小数
											return ((row.completedQuantity/row.orderContentDO.count)*100).toFixed(2)+"%";
										}
									}
								},
								{
									field : 'startDate',
									title : '计划开工时间'
								},
								{
									field : 'endDate',
									title : '计划完工时间'
								},

																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.orderContentDO.contentId
												+ '\')"><i class="fa fa-edit"></i>编辑</a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.planId
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var g = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="detail(\''
											+ row.orderContentDO.contentId
											+ '\')"><i class="fa fa-edit"></i>查看</a> ';
										var f;
										if(row.planId!=null){
											f = '<a class="btn btn-success btn-sm" href="#" title="开始计划"  mce_href="#" onclick="startPlan(\''
												+ row.planId
												+ '\')"><i class="fa fa-key"></i>开始计划</a> ';
										}else{
											f = '<a class="btn btn-success btn-sm" href="#" title="开始计划"  mce_href="#" onclick="edit(\''
												+ row.orderContentDO.contentId
												+ '\')"><i class="fa fa-key"></i>开始计划</a> ';
										}
										return  f + e ;
									}
								} ]
					});
}
var opt = {};
function reLoad() {
	opt = {
		query: {
			planCode: $('#planCode').val(),
			productName: $('#productName').val(),
			startDate: $('#startDate').val(),
			endDate: $('#endDate').val()	//为方便url传参，模糊查询在mapper中实现
		}
	}
	$('#exampleTable').bootstrapTable('refresh',opt);
}
function reset() {
	$('#planCode').val("")
	$('#productName').val("")
	$('#startDate').val("")
	$('#endDate').val("")
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {//流程
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '1000px', '600px' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	console.log("test top layer open")
	top.layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '1000px', '600px' ],
		content : prefix + '/edit/' + id ,// iframe的url
		success:function (layero,index) {
			top.layerParent=[]
			top.layerParent.push(window)
		}
	});
}
function detail(id) {
	top.layer.open({
		type : 2,
		title : '详情',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '1000px', '600px' ],
		content : prefix + '/detail/' + id ,// iframe的url
		success:function (layero,index) {
			top.layerParent = window
		}
	});
}
function startPlan(id) {
		$.ajax({
			cache : true,
			type : "POST",
			url : "/factory/productPlan/update",
			data : {"planId":id,"planState":'已开始'},
			async : false,
			error : function(request) {
				parent.layer.alert("Connection error");
			},
			success : function(data) {
				if (data.code == 0) {
					parent.layer.msg("计划已开始");
					reLoad();
				} else {
					parent.layer.alert(data.msg)
				}
			}
		});
}
function complete() {
	var rows = $('#exampleTable').bootstrapTable('getSelections');
	if (rows.length == 0) {
		layer.msg("请选择要完成的计划");
		return;
	}
	var plans=new Array();
	var hasUncompleted=false;
	$.each(rows, function(i, row) {
		if(row.orderContentDO==null
			||row.completedQuantity<row.orderContentDO.count){
			hasUncompleted=true;
			console.log(row.orderContentDO);
			console.log(row.completedQuantity);
			console.log(row.orderContentDO.count)
		}
		var obj={};
		obj["planId"] = row['planId'];
		obj["planState"]=row['planState']==3?4:2
		plans[i]=obj;
	});
	if(hasUncompleted){
		layer.confirm('选中项有完成进度不足100%的计划，确认继续？', {
			btn : [ '确定', '取消' ]
		},function () {
			$.ajax({
				type : 'POST',
				data : JSON.stringify(plans),
				contentType:"application/json",
				url : prefix + '/complete',
				success : function(r) {
					if (r.code == 0) {
						layer.msg(r.msg);
						reLoad();
					} else {
						layer.msg(r.msg);
					}
				}
			});
		})
	}else{
		$.ajax({
			type : 'POST',
			data : JSON.stringify(plans),
			contentType:"application/json",
			url : prefix + '/complete',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}

}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'planId' : id
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
			ids[i] = row['planId'];
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