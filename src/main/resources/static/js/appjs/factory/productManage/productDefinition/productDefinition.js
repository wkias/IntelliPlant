
var prefix = "/productManage/productDefinition"
$(function() {
	load();
	loadState();
});

function loadState(){
    $.ajax({
        url: '/common/dict/list/state',
        success: function (data) {
            var html = "";
            // 加载数据
            html+="<option value=''>所有状态</option>";
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
                            if(s_details_h!='hidden') {
                                details(row.productId);
                            }
                            },
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset
                                //productCode:$('#searchProductCode').val(),
                                //productName:$('#searchProductName').val(),
                                //state:$('#searchState').val()
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
									field : 'productId', 
									title : '产品定义ID' 
								},
								{
									field : 'productCode', 
									title : '产品编号' 
								},
								{
									field : 'productName', 
									title : '产品名称' 
								},
								{
                                    visible:false,
									field : 'productSize', 
									title : '规格尺寸' 
								},
								{
									field : 'productModel', 
									title : '产品型号' 
								},
                            {
                                visible: false,
                                field: 'unit',
                                title: '单位'
                            },
                            {
                                field: 'unitName',
                                title: '单位'
                            },
                            {
                                field : 'weight',
                                title : '产品单重'
                            },
                            {
                                visible: false,
                                field: 'weightUnit',
                                title: '重量单位'
                            },
                            {
                                field: 'weightUnitName',
                                title: '重量单位'
                            },
                            {
                                field : 'price',
                                title : '单价(元)'
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
                                visible:false,
                                field : 'description',
                                title : '描述:'
                            },
								{
                                    visible:false,
									field : 'isDeleted', 
									title : '删除标记:'
								},
								{
                                    visible:false,
									field : 'createUserId', 
									title : '创建者' 
								},
								{
                                    visible:false,
									field : 'createDate', 
									title : '创建日期' 
								},
								{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.productId
												+ '\')"><i class="fa fa-edit"></i>编辑</a> ';
                                        var d = '<a class="btn btn-success btn-sm" href="#" mce_href="#" title="点击启用" onclick="changeState(\''
                                            + row.productId + '\',\'' + row.state
                                            + '\')"><i class="fa fa-hourglass-start"></i>启用</a> ';
                                        var f = '<a class="btn btn-danger btn-sm" href="#" mce_href="#" title="点击停用" onclick="changeState(\''
                                            + row.productId + '\',\'' + row.state
                                            + '\')"><i class="fa fa-square-o">停用</i></a> ';
                                        if (row.state == 0) {
                                            return e + d;
                                        } else {
                                            return e+f;
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
            productCode: $('#productCode').val(),
            productName: $('#productName').val(),
            state: $('#state').val()	//为方便url传参，模糊查询在mapper中实现
        }
    }
    $('#exampleTable').bootstrapTable('refresh',opt);
}
function reset() {//重置
    $('#productCode').val("");
    $('#productName').val("");
    $('#state').val("");
    $('#exampleTable').bootstrapTable('refresh')
}

function changeState(id, state) {
    var actCh;
    var cmd;
    if (state == 0) {
        cmd = 'start';
        actCh = "确认要启用吗？";
    } else {
        cmd = 'stop';
        actCh = "确认要停用吗？";
    }
    layer.confirm(actCh, {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/changeState",
            type: "post",
            data: {
                'productId': id,
                'cmd': cmd
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

function details(id){
    top.layer.open({
        type:2,
        tile:"定义产品详情",
        maxmin:true,
        shadeClose : false, // 点击遮罩关闭层
        area: ['1000px', '600px'],
        content:prefix+'/details/'+id,
        success: function (layero, index) {
            top.layerParent = window
        }
    })
}

function add() {
    top.layer.open({
		type : 2,
		title : '新增产品定义',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
        area: ['1000px', '600px'],
		content : prefix + '/add', // iframe的url
        success: function (layero, index) {
            top.layerParent = window
        }
	});
}
function edit(id) {

    top.layer.open({
		type : 2,
		title : '编辑产品定义',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
        area: ['1000px', '600px'],
		content : prefix + '/edit/' + id, // iframe的url
        success: function (layero, index) {
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
				'productId' : id
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
			ids[i] = row['productId'];
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