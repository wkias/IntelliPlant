var prefix = "/factory/receiveRecord";
$(function () {
    load();
    loadDict();
});

function loadDict() {
    $.ajax({
        url: '/common/dict/list/receive_state',
        success: function (data) {
            var html = "";
            html += "<option value=''>选择状态</option>";
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#state").append(html);
            $("#state").val('');
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
                method: 'get',
                url: prefix + "/list",
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true,
                dataType: "json",
                pagination: true,
                singleSelect: false,
                pageSize: 10,
                pageNumber: 1,
                showColumns: false,
                sidePagination: "server",
                queryParams: function (params) {
                    return {
                        limit: params.limit,
                        offset: params.offset,
                        contractCode: $('#searchCode').val()
                    };
                },
                columns: [
                    {
                        field: 'contractCode',
                        title: '合同编号'
                    },
                    {
                        field: 'contractName',
                        title: '合同名称'
                    },
                    {
                        field: 'contractType',
                        title: '合同类型'
                    },
                    {
                        visible: false,
                        field: 'planBatchName',
                        title: '回款计划期次'
                    },
                    {
                        visible: false,
                        field: 'recordBatchName',
                        title: '回款记录期次'
                    },
                    {
                        field: 'receiveTypeName',
                        title: '回款类型',
                    },
                    {
                        field: 'receiveAmount',
                        title: '回款金额'
                    },
                    {
                        visible: false,
                        field: 'paymentMethodName',
                        title: '付款方式',
                    },
                    {
                        visible: false,
                        field: 'date',
                        title: '回款日期'
                    },
                    {
                        visible: false,
                        field: 'payeeName',
                        title: '收款人'
                    },
                    {
                        visible: false,
                        field: 'note',
                        title: '备注'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            return '<a class="btn btn-success btn-sm"' + s_attachment_h + ' href="#" title="附件"  mce_href="#" onclick="file(\''
                                + row.file
                                + '\')"><i class="fa fa-clone"></i></a> ';
                        },
                    }]
            });
}

function file(fileName) {
    if (fileName === "null") {
        layer.msg("没有上传附件");
        return;
    }
    location.href = prefix + "/file/" + fileName;
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
