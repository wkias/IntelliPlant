var prefix = "/factory/invoiceRecord"
$(function () {
    load();
    loadType();

});

function loadType() {
    var html = "";
    $.ajax({
        url: '/common/dict/list/invoice_type',
        success: function (data) {
            // 加载数据
            html+="<option value=''>请选择开票类型</option>"
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $(".chosen-select").append(html);
            $(".chosen-select").chosen({
                maxHeight: 200
            });
            $(".chosen-select").val($("#Ttype").val());
            $(".chosen-select").trigger("chosen:updated");
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
                onDblClickRow: function (row, $element) {//双击事件
                    if (s_details_h != 'hidden') {
                        details(row.invoiceId);
                    }
                },
                queryParams: function (params) {
                    return {
                        limit: params.limit,
                        offset: params.offset,
                        name: $('#searchName').val(),
                        searchType: $('#searchType').val(),
                    };
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'invoiceName',
                        title: '开票名称'
                    },
                    {
                        field: 'invoiceMoney',
                        title: '开票金额（元）'
                    },
                    {
                        field: 'invoiceDate',
                        title: '开票日期'
                    },
                    {
                        field: 'handlerPerson',
                        title: '经手人'
                    },
                    {
                        field: 'addtionPerson',
                        title: '添加人'
                    },
                    {
                        field: 'addtionDate',
                        title: '添加时间'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',

                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.invoiceId
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm" ' + s_attachment_h + ' href="#" title="附件"  mce_href="#" onclick="exportFile(\''
                                + row.invoiceId
                                + '\')"><i class="fa fa-key"></i></a> ';
                            return e+f;
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

var opt = {};

function reLoad() {//搜索（带参刷新）
    opt = {
        query: {
            invoiceName: $('#invoiceName').val(), //为方便url传参，模糊查询在mapper中实现
        }
    }
    $('#exampleTable').bootstrapTable('refresh', opt);
}

function reset() {//重置
    $('#invoiceName').val("");
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
            top.layerParentCP = window
        }
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

function exportFile() {
  var invoiceName = $('#searchName').val();
 var invoiceType = $('#searchType').val();
 location.href = prefix + "/exportFile" + "?name=" + invoiceName + "&searchType=" + invoiceType;
}


function edit(id) {
    console.log("test top layer open")
    top.layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['1000px', '600px'],
        content: prefix + '/edit/' + id, // iframe的url
        success: function (layero, index) {
            top.layerParent = window
        }
    });
}

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'invoiceId': id
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
    }, function () {
        var ids = [];
        $.each(rows, function (i, row) {
            ids[i] = row['invoiceId'];
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