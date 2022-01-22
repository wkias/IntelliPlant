
var prefix = "/factory/custermerInformation"
$(function() {
    load();
    loadCustermerType();
});

function loadCustermerType() {
    var html = "";
    $.ajax({
        url: '/common/dict/list/custermer_type',
        success: function (data) {
            // 加载数据
            html+="<option value=''>所有</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $(".chosen-select").append(html);
            $(".chosen-select").chosen({
                maxHeight: 200
            });
            //$(".chosen-select").val($("#Ttype").val());
            $(".chosen-select").trigger("chosen:updated");
            // 点击事件
            // $('.chosen-select').on('change', function (e, params) {
            //
            // });
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
                    if(s_details_h!='hidden'){
                        details(row.custermerId);
                    }
                },
                queryParams : function(params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset:params.offset,
                        //custermerType:$('#searchCustermerType').val(),
                        //custermerName:$('#searchCustermerName').val()
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
                        field : 'custermerId',
                        title : '客户ID'
                    },
                    {
                        field : 'custermerName',
                        title : '客户名称（全称）'
                    },
                    {
                        field : 'custermerType',
                        title : '客户类型'
                    },
                    {
                        visible:false,
                        field : 'taxpayerIdNumbers',
                        title : '纳税人识别号'
                    },
                    {
                        visible:false,
                        field : 'socialCreditCode',
                        title : '统一社会信用代码'
                    },
                    {
                        field : 'address',
                        title : '地址'
                    },
                    {
                        field : 'custermerTel',
                        title : '客户电话'
                    },
                    {
                        field : 'bank',
                        title : '开户行'
                    },
                    {
                        field : 'bankAccount',
                        title : '开户行账号'
                    },
                    {
                        visible:false,
                        field : 'email',
                        title : '电子邮箱'
                    },
                    {
                        visible:false,
                        field : 'fax',
                        title : '传真'
                    },
                    {
                        field : 'name',
                        title : '主联系人'
                    },
                    {
                        field : 'contactPersonTel',
                        title : '主联系人电话'
                    },
                    {
                        visible:false,
                        field : 'createUserId',
                        title : '创建者'
                    },
                    {
                        visible:false,
                        field : 'createTime',
                        title : '创建时间'
                    },
                    {
                        title : '操作',
                        field : 'id',
                        align : 'center',
                        formatter : function(value, row, index) {
                            return "<a class='btn btn-primary btn-sm' title='选择' onclick='select("
                                + JSON.stringify(row)
                                + ")'><i class='fa fa-ticket'></i>选择</a> ";
                        }
                    } ]
            });
}

var opt = {};
function select(row) {
    let index = parent.layer.getFrameIndex(window.name); //获取窗口索引rn fmt;
    parent.document.getElementById("custermerId").setAttribute("value", row.custermerId);
    parent.document.getElementById("custermerName").setAttribute("value", row.custermerName);
    parent.document.getElementById("contact").setAttribute("value", row.name);
    parent.document.getElementById("contactTel").setAttribute("value", row.contactPersonTel);
    parent.layer.close(index);
}
function reLoad() {//搜索（带参刷新）
    //$('#exampleTable').bootstrapTable('refresh');
    opt = {
        query: {
            custermerType: $('.chosen-select').val(),
            custermerName: $('#searchCustermerName').val()
            //为方便url传参，模糊查询在mapper中实现
        }
    };
    console.log("reload:" + JSON.stringify(opt));
    $('#exampleTable').bootstrapTable('refresh', opt);
}

function reset(){//重置
    $('.chosen-select').val("");
    $('#searchCustermerName').val("");
    $('#exampleTable').bootstrapTable('refresh')
}


function details(custermerId){
    layer.open({
        type:2,
        tile:"详情",
        maxmin:true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content:prefix+'/details/'+custermerId
    })
}

function person(id) {
    layer.open({
        type : 2,
        title : '联系人',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/person/' + id // iframe的url
    });
}



function exportFile() {
    let url = prefix + "/exportFile";
    if(JSON.stringify(opt)!='{}'){
        url+="?custermerName="+opt.query.custermerName+"&custermerType="+opt.query.custermerType;
        console.log("url:"+url);
    }
    location.href=url;
}
function add() {
    layer.open({
        type : 2,
        title : '增加',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/add' // iframe的url
    });
}
function edit(id) {
    layer.open({
        type : 2,
        title : '编辑',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/edit/' + id // iframe的url
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
                'custermerId' : id
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
            ids[i] = row['custermerId'];
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