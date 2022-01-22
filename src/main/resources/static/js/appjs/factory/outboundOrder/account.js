var prefix = "/factory/logisticsAccount";
$(function () {
    load();
});

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
                onDblClickRow: function (row, $element) {
                    if (s_details_h !== 'hidden') {
                        details(row.logisticsId);
                    }
                },
                queryParams: function (params) {
                    return {
                        limit: params.limit,
                        offset: params.offset,
                        formId: $('#searchId').val(),
                    };
                },
                columns: [
                    {
                        field: 'formId',
                        title: '表单编号',
                    },
                    {
                        field: 'formType',
                        title: '表单类型'
                    },
                    {
                        field: 'forwardingUnit',
                        title: '发货单位'
                    },
                    {
                        field: 'logisticsNumber',
                        title: '物流台账编号'
                    },
                    {
                        field: 'logisticsCompany',
                        title: '物流公司'
                    },
                    {
                        field: 'receiveUnit',
                        title: '收货单位'
                    },
                    {
                        field: 'totalMoney',
                        title: '总金额'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            return '<a class="btn btn-success btn-sm"' + s_attachment_h + ' href="#" title="附件"  mce_href="#" onclick="files(\''
                                + row.files
                                + '\')"><i class="fa fa-clone"></i></a> ';
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

function logisticsDetail(id) {
    location.href = prefix + "/logisticsDetail/" + id;
}

function details(id) {
    layer.open({
        type: 2,
        tile: "详情",
        maxmin: true,
        shadeClose: false,
        area: ['800px', '520px'],
        content: prefix + '/details/' + id
    })
}