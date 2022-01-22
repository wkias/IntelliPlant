var prefix = "/factory/contractManage"
$(function () {
    load();
    loadDict();
});
function loadDict(){
    $.ajax({
        url: '/common/dict/list/contract_type',
        success: function (data) {
            var html = "";
            // 加载数据
            html+="<option value=''>所有</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#contractType").append(html);
            $("#contractType").chosen({
                maxHeight: 200
            });
            $("#contractType").trigger("chosen:updated");
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
                onDblClickRow:function (row,$element) {//双击事件
                   // if(s_details_h!='hidden'){
                        details(row.contractId);
                   // }
                },
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset
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
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        visible: false,
                        field: 'contractId',
                        title: '合同ID'
                    },
                    {
                        visible:false,
                        field:'fileName'
                    },
                    {
                        field: 'contractCode',
                        title: '合同编号'
                    },
                    {
                        field: 'contractName',
                        title: '合同名称'
                    },
                    {
                        field: 'typeName',
                        title: '合同类型'
                    },
                    {
                        field: 'dutyUserName',
                        title: '合同负责人'
                    },
                    {
                        field: 'startDate',
                        title: '开始日期',
                    },
                    {
                        field: 'endDate',
                        title: '截止日期',
                    },
                    {
                        field: 'stateName',
                        title: '状态'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.contractId
                                + '\')"><i class="fa fa-edit"></i>编辑</a> ';
                            var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="附件"  mce_href="#" onclick="downloadFile(\''
                                + row.fileName
                                + '\')"><i class="fa fa-download"></i>附件</a> ';
                            var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                                + row.contractId
                                + '\')"><i class="fa fa-key"></i></a> ';
                            return e + d;
                        }
                    }]
            });
}
function downloadFile(fileName){
    var  url="/factory/equipmentManage/files/"+fileName;
    window.location=url;
}
var opt = {};

function reLoad() {//搜索（带参刷新）
    //$('#exampleTable').bootstrapTable('refresh');
    opt = {
        query: {
            contractType: $('#contractType').val(),
            contractCode: $('#contractCode').val(),
            contractName: $('#contractName').val()	//为方便url传参，模糊查询在mapper中实现
        }
    }
    $('#exampleTable').bootstrapTable('refresh',opt);
}
function reset() {//重置
    $('#contractType').val("");
    $('#contractCode').val("");
    $('#contractName').val("");
    $('#exampleTable').bootstrapTable('refresh')
}
function add() {
    // layer.open({
    //     type: 2,
    //     title: '增加',
    //     maxmin: true,
    //     shadeClose: false, // 点击遮罩关闭层
    //     area: ['800px', '520px'],
    //     content: prefix + '/add' // iframe的url
    // });
	window.open( prefix + '/add','新增合同','top=100,left=100,height=600,width=1000,location=no')
}
function details(id){
    // layer.open({
    //     type:2,
    //     tile:"详情",
    //     maxmin:true,
    //     shadeClose : false, // 点击遮罩关闭层
    //     area : [ '800px', '520px' ],
    //     content:prefix+'/details/'+id
    // })
    window.open( prefix + '/details/'+id,"合同详情","top=100,left=100,height=600,width=1000,location=no")
}
function edit(id) {
    // layer.open({
    //     type: 2,
    //     title: '编辑',
    //     maxmin: true,
    //     shadeClose: false, // 点击遮罩关闭层
    //     area: ['800px', '520px'],
    //     content: prefix + '/edit/' + id // iframe的url
    // });
	window.open( prefix + '/edit/'+id,"编辑合同","top=100,left=100,height=600,width=1000,location=no")
}

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'contractId': id
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
            ids[i] = row['contractId'];
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
//导出
function exportFile() {
    let url = prefix + "/export";
    if (JSON.stringify(opt) != '{}') {
        url += "?contractType=" + opt.query.contractType + "&contractCode=" + opt.query.contractCode + "&contractName=" + opt.query.contractName;
        console.log("url:" + url);
    }
    location.href = url;
}