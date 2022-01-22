var prefix = "/factory/processDefinition"
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
                queryParams: function (params) {
                    return {
                        limit: params.limit,
                        offset: params.offset,
                        processName: $('#processName').val(),
                        processCode: $('#processCode').val(),
                        state: 1
                    };
                },
                columns: [
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
                            if (value == 0)
                                return '<button type="button" class="btn  btn-default btn-sm"\n' +
                                    '<i class="fa fa-crosshairs" aria-hidden="true"></i>未启用\n' +
                                    '</button>';
                            else
                                return '<button type="button" class="btn  btn-primary btn-sm"\n' +
                                    '<i class="fa fa-crosshairs" aria-hidden="true"></i>已启用\n' +
                                    '</button>';
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
                        formatter: function (value, row, index) {
                            return "<a class='btn btn-primary btn-sm' title='选择' onclick='select("
                                + JSON.stringify(row)
                                + ")'><i class='fa fa-ticket'></i></a> ";
                        }
                    }]
            });
}

function reset() {
    $('#processName').val("");
    $('#processCode').val("");
    $('#exampleTable').bootstrapTable('refresh');
}

function select(row) {
    parent.loadProcess(row);
    let index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}