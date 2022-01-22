
var prefix = "/productManage/dailyProductionManage"
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
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
                                dailyProductionDate:$('#date').val()
					           // name:$('#searchName').val(),
					           // username:$('#searchName').val()
							};
						},
                        onDblClickRow:function (row,$element) {//双击事件
                            detail(row.dailyProductionId);
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
									field : 'dailyProductionId', 
									title : '生产日报id' 
								},
																{
									field : 'dailyProductionDate', 
									title : '生产日期' 
								},{
                                title : '工序列表' ,
                                formatter:function (value,row,index) {
                                    let html="";
                                    $.ajax({
                                        type:"get",
										data:{
                                        	dailyProductionId:row.dailyProductionId
										},
                                        url:"/productManage/dailyProductionContent/list",
                                        async:false,//同步请求
                                        success:function (data) {
                                            if(data.rows.length==0){return;}
                                            if(data.rows.length==1){
                                                html+=data.rows[0].processDefinitionDO.processName;
                                            }else{
                                            	for(let i=0;i<data.rows.length;i++){

                                                    html+=data.rows[i].processDefinitionDO.processName+",";

                                            	}
                                            }

                                        }
                                    })
                                    return html;
                                }
								},
                            {
                                title : '部件列表' ,
                                formatter:function (value,row,index) {
                                    let html="";
                                    $.ajax({
                                        type:"get",
                                        data:{
                                            dailyProductionId:row.dailyProductionId
                                        },
                                        url:"/productManage/dailyProductionContent/list",
                                        async:false,//同步请求
                                        success:function (data) {
                                            if(data.rows.length==0){return;}
                                            if(data.rows.length==1){
                                                html+=data.rows[0].productDetailDO.productName;
                                            }else{
                                                for(var i=0;i<data.rows.length;i++){

													if(i==data.rows.length-1){
														html+=data.rows[i].productDetailDO.productName;
														}
													else
                                                    html+=data.rows[i].productDetailDO.productName+",";

                                                }
                                            }

                                        }
                                    })
                                    return html;
                                }
                            },
																{
									visible:false,
									field : 'remark', 
									title : '备注' 
								},
																{
                                    visible:false,
									field : 'isDeleted', 
									title : '删除标志' 
								},
																{
                                    visible:false,
									field : 'createDate', 
									title : '创建时间' 
								},
																{
                                   visible:false,
									field : 'createUserId', 
									title : '创建人' 
								},
																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.dailyProductionId
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.dailyProductionId
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
												+ row.dailyProductionId
												+ '\')"><i class="fa fa-key"></i></a> ';
										return e + d ;
									}
								} ]
					});
}
function reLoad() {

	$('#exampleTable').bootstrapTable('refresh');
}
function reset() {
    $('#date').val("");
    $('#exampleTable').bootstrapTable('refresh');
}
function add() {
    // window.open( prefix + '/add',"添加","top=50,left=100,height=1000,width=700,location=no")

    top.layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '1000px', '600px' ],
		content : prefix + '/add', // iframe的url
		success:function () {
            top.layerParent = window;
        },
		end :function () {
			location.reload();
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
    });
}
function edit(id) {
	top.layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '1000px', '600px' ],
		content : prefix + '/edit/' + id, // iframe的url
        success:function () {
            top.layerParent = window;
        },
		end :function () {
			location.reload();
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
				'dailyProductionId' : id
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
			ids[i] = row['dailyProductionId'];
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