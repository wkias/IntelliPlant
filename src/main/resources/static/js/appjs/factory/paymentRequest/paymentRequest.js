
var prefix = "/factory/paymentRequest"
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
						onDblClickRow: function (row, $element) {//双击事件
							if (s_details_h != 'hidden') {
								details(row.paymentRequestId);
							}
						},
						queryParams : function(params) {
							let va = 0;
							if (params.valueOf("queryAll") === true) {
								va = 1;
							}
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
								formNumber: $('#searchFormNumber').val(),
								dateBefore: $('#searchDateBefore').val(),
								dateLater: $('#searchDateLate').val(),
								queryAll: va
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
								field : 'formNumber',
								title : '单据编号'
							},
							{visible:false,
								field : 'userId',
								title : '请款人ID'
							},
							{
								field : 'userName',
								title : '借款人'
							},
							{
								field : 'paymentState',
								title : '单据状态',
									formatter: function (value, row, index) {
										if (value == '0') {return '<span>草稿</span>'}
										else if (value == '1') {return '<span>审批中</span>'}
										else if (value == '2') {return '<span>待还款</span>'}
										else if (value == '3') {return '<span>已完成</span>'}
										else if (value == '4') {return '<span>已超期</span>'}
									}
							},
							{
								field : 'createTime',
								title : '创建时间'
							},
							{
								field : 'businessReasons',
								title : '业务事由'
							},
							{
								field : 'collectionAccount',
								title : '收款账户'
							},
							{
								title: '操作',
								field: 'id',
								align: 'center',
								formatter: function (value, row, index) {
									var d = '<a class="btn btn-primary btn-sm ' +  + '" href="#" mce_href="#" title="附件" onclick="files(\''
										+ row.files
										+ '\')"><i class="fa fa-download"></i></a> ';
									var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
										+ row.paymentRequestId
										+ '\')"><i class="fa fa-edit"></i></a> ';
									if (row.paymentState == 0) {
										return e + d;
									} else {
										return d;
									}
								}
							}]
					});
}
function files(filesName) {
	if (filesName === "null") {
		layer.msg("没有上传附件");
		return;
	}
	location.href = prefix + "/file/" + filesName;
}

function reLoad() {
	var dateBefore = $('#searchDateBefore').val();
	var dateLater = $('#searchDateLate').val();
	if (dateBefore != null && dateBefore != "" && dateLater != null && dateLater != "" && dateBefore > dateLater) {
		layer.msg("请输入正确的日期范围");
		return;
	}
	$('#exampleTable').bootstrapTable('refresh');
}
function reset() {
	$('#searchFormNumber').val(""),
		$('#searchDateBefore').val(""),
		$('#searchDateLate').val("")


	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	console.log("test top layer open")
	top.layer.open({
		type: 2,
		title: '增加',
		maxmin: true,
		shadeClose: false, // 点击遮罩关闭层
		area: ['1000px', '600px'],
		content: prefix + '/add', // iframe的url
		success: function (layero, index) {
			top.layerParentCP = window
		}
	});
}

function edit(id) {
	console.log("test top layer open")
	top.layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id, // iframe的url
		success: function (layero, index) {
			top.layerParent = window}
	});
}

function details(id) {
	console.log("test top layer open")
	top.layer.open({
		type: 2,
		tile: "详情",
		maxmin: true,
		shadeClose: false, // 点击遮罩关闭层
		area: ['1000px', '600px'],
		content: prefix + '/details/' + id,
		success: function (layero, index) {
			top.layerParent = window
		}
	})
}
function queryAll() {
	let opt = {
		query: {
			queryAll: true
		}
	};
	$('#exampleTable').bootstrapTable('refresh', opt);
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'paymentRequestId' : id
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
			ids[i] = row['paymentRequestId'];
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