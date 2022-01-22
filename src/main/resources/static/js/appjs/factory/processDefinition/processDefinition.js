var prefix = "/factory/processDefinition"
$(function () {
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
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list", // 服务器数据的加载地址
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                singleSelect: false, // 设置为true将禁止多选
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams: function (params) {
                    let va = 1;
                    if (params.valueOf("state") === 0) {
                        va = 0;
                    }else if(params.valueOf("state") === 1){
                        va = 1;
                    }else if(params.valueOf("state") === 2){
                        va = 1 || 0;
                    }
                    return {
                        limit: params.limit,
                        offset: params.offset,
                        state: va,
                    };
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        visible: false,
                        field: 'processId',
                        title: '工序定义id'
                    },
                    {
                        field: 'processCode',
                        title: '工序编号'
                    },
                    {
                        field: 'processName',
                        title: '工序名称'
                    },
                    {
                        field: 'state',
                        title: '状态',
                        align: 'center',
                        formatter: function (value, row, index) {
                            if (value == '0') {
                                return '<span class="label label-danger">已停用</span>';
                            } else if (value == '1') {
                                return '<span class="label label-primary">启用中</span>';
                            }
                        }
                    },
                    {
                        field: 'description',
                        title: '工序描述'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        width: '200px',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.processId
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-success btn-sm" href="#" mce_href="#" title="启用" onclick="changeState(\''
                                + row.processId + '\',\'' + row.state
                                + '\')"><i class="fa fa-hourglass-start"></i>启用</a> ';
                            var f = '<a class="btn btn-danger btn-sm" href="#" mce_href="#" title="停用" onclick="changeState(\''
                                + row.processId + '\',\'' + row.state
                                + '\')"><i class="fa fa-square-o">停用</i></a> ';
                            if (row.state == 0) {
                                return e + d;
                            } else {
                                return e + f;
                            }
                        }
                    }]
            });
}

var opt = {};

function reLoad() {//搜索（带参刷新）
    opt = {
        query: {
            state: $('#state').val(),
            processName: $('#processName').val(),
            processCode: $('#processCode').val(),//为方便url传参，模糊查询在mapper中实现
        }
    }
    $('#exampleTable').bootstrapTable('refresh', opt);
}

function reset() {//重置
    $('#state').val("");
    $('#processName').val("");
    $('#processCode').val("");
    $('#exampleTable').bootstrapTable('refresh')
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
            top.layerParent = window
        }
    });
}

function edit(id) {
    console.log("test top layer open")
    top.layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['1000px', '600px'],
        content: prefix + '/edit/' + id ,// iframe的url
        success: function (layero, index) {
            top.layerParent = window
        }
    });
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
                'processId': id,
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

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'processId': id
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
            ids[i] = row['processId'];
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