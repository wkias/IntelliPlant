let prefix = "/productManage/processInspectionReport";
$().ready(function () {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get',
                url: prefix + "/listItems",
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
                        processInspectionReportId: $('#processInspectionReportId').val()
                    };
                },
                columns: [
                    {
                        field: 'processName',
                        title: '工序名称'
                    },
                    {
                        field: 'componentName',
                        title: '部件名称'
                    },
                    {
                        field: 'unit',
                        title: '数量单位'
                    },
                    {
                        field: 'qualifiedNum',
                        title: '合格/返工/报废',
                        formatter: function (value, row, index) {
                            return '<a class="btn btn-primary btn-sm" title="合格">' + row.qualifiedNum + '</a>' +
                                '<a class="btn btn-info btn-sm" title="返工">' + row.reworkNum + '</a>' +
                                '<a class="btn btn-danger btn-sm" title="报废">' + row.scrapNum + '</a>';
                        }
                    }]
            });
}
