
var prefix = "/productManage/processInspection"
$(function() {
	load();
    reLoad();
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
                                detail(row.processInspectionId);
                            }
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
									field : 'processName',
									title : '关联工序名称'
                                     /*formatter:function (value,row,index) {
                                       if(row.process!=null){
                                          return row.process.processName;
                                            }
                                                                 }*/
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
                                       title: '质检项目名称',
                                      formatter:function (value,row,index) {
                                        if(row.items!=null){
                                            let html="";
                                            $.ajax({
                                                type:"get",
                                                url:"/productManage/processInspection/itemsList/"+row.processInspectionId,
                                                async:false,//同步请求
                                                success:function (data) {
                                                    for(let i=0;i<data.length-1;i++){
                                                        html+=data[i].items.projectName+"；";
                                                    }
                                                    html+=data[data.length-1].items.projectName
                                                }
                                            })
                                            return html;
                                                        }
                                                                    }
								},



                            {
                                title : '操作',
                                field : 'id',
                                align : 'center',
                                formatter : function(value, row, index) {
                                    var d = '<a class="btn btn-primary btn-sm  " href="#" title="编辑"  mce_href="#" onclick="edit(\''
                                        + row.processInspectionId
                                        + '\')"><i class="fa fa-remove"></i>编辑</a> ';
                                    var f = '<a class="btn btn-success btn-sm" href="#" title="启用"  mce_href="#" onclick="start(\''
                                        + row.processInspectionId
                                        + '\')"><i class="fa fa-key"></i>启用</a> ';
                                    var g = '<a class="btn btn-danger btn-sm" href="#" title="停用"  mce_href="#" onclick="stop(\''
                                        + row.processInspectionId
                                        + '\')"><i class="fa fa-key"></i>停用</a> ';
                                    if(row.state=='1')
                                    {
                                        return d +g;
                                    }else{
                                        return d +f;
                                    }
                                }
                            } ]
					});
}

function detail(id) {
    top.layer.open({
        type : 2,
        title : '工序质检信息详情',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area: ['1000px', '600px'],
        content : prefix + '/details/' + id ,// iframe的url
        success: function (layero, index) {
            top.layerParent = window
        }
    });
}

var opt = {};
function reLoad() {//搜索（带参刷新）
    //$('#exampleTable').bootstrapTable('refresh');
    opt = {
        query: {
            processName: $('#processName').val(),
            state: $('#state').val()	//为方便url传参，模糊查询在mapper中实现
        }
    }
    $('#exampleTable').bootstrapTable('refresh',opt);
}
function reset(){//重置
    $('#processName').val("");
    $('#state').val("");
    $('#exampleTable').bootstrapTable('refresh')
}

function start(processInspectionId) {
    $.ajax({
        type:"post",
        url:prefix+"/update",
        data:"processInspectionId="+processInspectionId+"&state="+1,
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
function stop(processInspectionId) {
    $.ajax({
        type:"post",
        url:prefix+"/update",
        data:"processInspectionId="+processInspectionId+"&state="+0,
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

function add(processId) {
	top.layer.open({
		type : 2,
		title : '新增质检',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
        area: ['1000px', '600px'],
		content : prefix + '/add', // iframe的url
        success:function (layero,index) {

            var body = layer.getChildFrame('body', index);
            var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
            //console.log(body.html()) //得到iframe页的body内容
            //console.log(body.find('#product'))
            console.log(body.find('#process'))
            $process=$(body.find('#process'));
            console.log(iframeWin)
            $process.val(processId);
            $process.trigger("chosen:updated");
            top.layerParent = window
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
        content: prefix + '/edit/' + id,// iframe的url
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
				'processInspectionId' : id
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
			ids[i] = row['processInspectionId'];
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