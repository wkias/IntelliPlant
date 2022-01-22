var prefix = "/factory/inspectionItems";
$(function () {
    load();
    reLoad();
});

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
                    }
                },
                queryParams: function (params) {
                    return {
                        limit: params.limit,
                        offset: params.offset,
                        projectName: $('#projectName').val(),
                    };
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'projectName',
                        title: '项目名称'
                    },
                    {
                        field: 'valueTypeName',
                        title: '值类型'
                    },
                    {
                        field: 'unitName',
                        title: '单位'
                    },
                    {
                        field: 'rangeThreshold',
                        title: '范围阈值'
                    },
                    {
                        field: 'description',
                        title: '说明'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = "<a class='btn btn-primary btn-sm' href='#' mce_href='#' title='选择' onclick='select("
                                + JSON.stringify(row)
                                + ")'><i class='fa fa-edit'></i></a> ";
                            return e;
                        }
                    }]
            })
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function select(row) {
    var layerParent = top.layerParent;
    let index = top.layer.getFrameIndex(window.name); //获取窗口索引
    layerParent.$("#inspectionItemsId").val(row.inspectionItemsId);
    layerParent.$("#projectName").val(row.projectName);
    layerParent.$("#valueType").val(row.valueType);
    layerParent.$("#valueTypeName").val(row.valueTypeName);
    layerParent.$("#unit").val(row.unit);
    layerParent.$("#unitName").val(row.unitName);
    layerParent.$("#rangeThreshold").val(row.rangeThreshold);
    layerParent.$("#description").val(row.description);
    layerParent.$('#exampleTable').bootstrapTable('load', row.details);
    top.layer.close(index);
}
