var prefix = "/reimburse/"
$(function () {
    consume();
    loan();
});

function consume() {
    $('#consume')
        .bootstrapTable(
            {
                method: 'get',
                url: prefix + "consume/list",
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true,
                dataType: "json",
                singleSelect: false,
                pageSize: 10,
                pageNumber: 1,
                showColumns: false,
                sidePagination: "server",
                queryParams: function (params) {
                    return {
                        reimburseId: $("#reimburseId").val(),
                    };
                },
                columns: [
                    {
                        field: 'consumeType',
                        title: '消费类型'
                    }, {
                        field: 'amount',
                        title: '金额（元）'
                    }, {
                        field: 'payer',
                        title: '付款人'
                    }, {
                        field: 'time',
                        title: '时间',
                        formatter: function (value, row, index) {
                            if (value === null) {
                                return '';
                            } else {
                                return value;
                            }
                        }
                    }, {
                        field: 'invoiceNum',
                        title: '发票数量'
                    }, {
                        field: 'note',
                        title: '备注'
                    }, {
                        field: 'file',
                        title: '文件',
                        align: 'center',
                        formatter: function (value, row, index) {
                            return '<a class="btn btn-primary btn-sm " href="#" mce_href="#" title="附件" onclick="getFile(\''
                                + row.file
                                + '\')"><i class="fa fa-ticket"></i></a> '
                        }
                    }
                ]
            });
}

function loan() {
    $('#loan')
        .bootstrapTable(
            {
                method: 'get',
                url: prefix + "loan/list",
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true,
                dataType: "json",
                singleSelect: false,
                pageSize: 10,
                pageNumber: 1,
                showColumns: false,
                sidePagination: "server",
                queryParams: function (params) {
                    return {
                        reimburseId: $("#reimburseId").val(),
                    };
                },
                columns: [
                    {
                        field: 'loanCode',
                        title: '编号',
                        cellStyle: function (value, row, index) {
                            return {
                                css: {
                                    "white-space": "nowrap",
                                    "text-overflow": "ellipsis",
                                    "overflow": "hidden",
                                    "max-width": "100px"
                                }
                            }
                        },
                        formatter: function (value, row, index) {
                            var span = document.createElement("span");
                            span.setAttribute("title", value);
                            span.innerHTML = value;
                            return span.outerHTML;
                        }
                    }, {
                        field: 'stateName',
                        title: '状态'
                    }, {
                        field: 'date',
                        title: '日期',
                        formatter: function (value, row, index) {
                            if (value === null) {
                                return '';
                            } else {
                                return value;
                            }
                        }
                    }, {
                        field: 'reason',
                        title: '事由',
                    }, {
                        field: 'amount',
                        title: '金额（元）'
                    },
                ]
            });
}

function getFile(file) {
    if (file === 'null' || file === '') {
        layer.msg('没有附件')
    } else {
        location.href = prefix + '/file/' + file;
    }
}

function getaFile() {
    if ($("file").val() === 'null' || $("file").val() === '') {
        layer.msg('没有附件')
    } else {
        location.href = prefix + '/file/' + $("file").val();
    }
}
