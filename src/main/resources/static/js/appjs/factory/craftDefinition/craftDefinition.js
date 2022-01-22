
var prefix = "/factory/craftDefinition"
$(function() {
	load();
	reLoad();
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
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset
					           // name:$('#searchName').val(),
					           // username:$('#searchName').val()
							};
						},
						onDblClickRow:function (row,$element) {//双击事件
								detail(row.craftId);
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
									field : 'craftCode', 
									title : '工艺编号' 
								},
																{
									field : 'craftName', 
									title : '工艺名称' 
								},
																{
									field : 'state', 
									title : '状态',
									align : 'center',
									formatter : function(value, row, index) {
										if (value == '0') {
											return '<span class="label label-danger">已停用</span>';
										} else if (value == '1') {
											return '<span class="label label-primary">启用中</span>';
										}
									}
								},
								{
									title : '工序列表' ,
									formatter:function (value,row,index) {
										let html="";
										$.ajax({
											type:"get",
											url:"/factory/craftDefinition/processList/"+row.craftId,
											async:false,//同步请求
											success:function (data) {
												if(data.length==0){return;}
												for(let i=0;i<data.length-1;i++){
													if(data[i].processDefinition!=null){
														html+=data[i].processDefinition.processName+",";
													}
												}
												if(data[data.length - 1].processDefinition!=null) {
													html += data[data.length - 1].processDefinition.processName
												}
											}
										})
										return html;
									}
								},

																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm " href="#" mce_href="#" title="查看" onclick="detail(\''
												+ row.craftId
												+ '\')"><i class="fa fa-edit"></i>查看</a> ';
										var d = '<a class="btn btn-primary btn-sm " href="#" title="编辑"  mce_href="#" onclick="edit(\''
												+ row.craftId
												+ '\')"><i class="fa fa-edit"></i>编辑</a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="启用"  mce_href="#" onclick="start(\''
												+ row.craftId
												+ '\')"><i class="fa fa-key"></i>启用</a> ';
										var g = '<a class="btn btn-danger btn-sm" href="#" title="停用"  mce_href="#" onclick="stop(\''
												+ row.craftId
												+ '\')"><i class="fa fa-key"></i>停用</a> ';
										if(row.state=='1')
										{
											return  d +g;
										}else{
											return  d +f;
										}
									}
								} ]
					});
}
var opt = {};
function reLoad() {//搜索（带参刷新）
	//$('#exampleTable').bootstrapTable('refresh');
	opt = {
		query: {
			state: $('#state').val(),
			craftName: $('#craftName').val(),
			craftCode: $('#craftCode').val(),
			processName: $('#processName').val()	//为方便url传参，模糊查询在mapper中实现
		}
	}
	$('#exampleTable').bootstrapTable('refresh',opt);
}
function reset(){//重置
	$('#state').val("");
	$('#craftName').val("");
	$('#craftCode').val("");
	$('#processName').val("");
	$('#exampleTable').bootstrapTable('refresh')
}
function add() {
	top.layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' ,// iframe的url
		success:function (layero, index) {
			top.layerParent =[];
			top.layerParent.push(window);
		}
	});
}
function edit(id) {
	top.layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id ,// iframe的url
		success:function (layero, index) {
			top.layerParent =[];
			top.layerParent.push(window);
		}
	});
}
function detail(id) {
	top.layer.open({
		type : 2,
		title : '详情',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/details/' + id ,// iframe的url
		success:function (layero, index) {
			top.layerParent =[];
			top.layerParent.push(window);
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
				'craftId' : id
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
function start(craftId) {
	$.ajax({
		type:"post",
		url:prefix+"/update",
		data:"craftId="+craftId+"&state="+1,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				layer.msg("操作成功");
				reLoad();
			} else {
				layer.alert(data.msg)
			}

		}
	})
}
function stop(craftId) {
	$.ajax({
		type:"post",
		url:prefix+"/update",
		data:"craftId="+craftId+"&state="+0,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				layer.msg("操作成功");
				reLoad();
			} else {
				layer.alert(data.msg)
			}

		}
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
			ids[i] = row['craftId'];
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