var parentNode;
var nowTreeNode;
var nextNode;
var tableColumns;
var selectORG;
var selectPerson;
var setting = {
    view: {
        dblClickExpand: false,
        showLine: true,
        selectedMulti: false,
        fontCss: getFontCss
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: ""
        }
    },
    callback: {
        beforeClick: function (treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("tree");

            nextNode = treeNode.getNextNode();
            parentNode = treeNode.getParentNode();
            nowTreeNode = treeNode;
            treeNodeHighLightClear();
            selectPeopleByTreeId();
            if (treeNode.isParent) {
                zTree.expandNode(treeNode);

            } else {

            }
        }
    }
};

function FindZTreeNode(txtSearch) {
    $.ajax({
        url: "/lihuaiotweb01/zTree/selectZTreeNode",
// 数据发送方式
        type: "POST",
// 接受数据格式
        dataType: "json",
// 要传递的数据
        data: {},
        success: function (result) {
            if (JSON.stringify(result) !== '[]') {
                var json = eval(result); //数组
                treeNodes = json;
                $.fn.zTree.init($("#tree"), setting, treeNodes);
                searchTreeNodeByName(txtSearch);
            } else {
                alert("未查询到树形组织数据");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

function getFontCss(treeId, treeNode) {
    return (!!treeNode.highlight) ? {color: "#A60000", "font-weight": "bold"} : {
        color: "#333",
        "font-weight": "normal"
    };
}

function searchTreeNode() {
    var txtSearch = document.getElementById("txtSearchTreeNode").value;
    searchTreeNodeByName("");
    searchTreeNodeByName(txtSearch);
}

function treeNodeHighLightClear() {
    var zTree = $.fn.zTree.getZTreeObj("tree");
    var nodeList = [];
    nodeList = zTree.getNodesByParamFuzzy("name", "");
    for (var i = 0, l = nodeList.length; i < l; i++) {
        nodeList[i].highlight = false;
        zTree.updateNode(nodeList[i]);
    }
}

function searchTreeNodeByName(txtSearch) {
    var zTree = $.fn.zTree.getZTreeObj("tree");
    var nodeList = [];
    nodeList = zTree.getNodesByParamFuzzy("name", txtSearch);
    if (txtSearch === "") {
        for (var i = 0, l = nodeList.length; i < l; i++) {
            nodeList[i].highlight = false;
            zTree.updateNode(nodeList[i]);
        }
        zTree.setting.view.expandSpeed = "";
        zTree.expandAll(false);
        zTree.setting.view.expandSpeed = "fast";
    }
    else {
        for (var i = 0, l = nodeList.length; i < l; i++) {
            nodeList[i].highlight = true;
            zTree.updateNode(nodeList[i]);
            zTree.expandNode(nodeList[i], true);
            zTree.selectNode(nodeList[i]);
        }
    }
}

//请求服务数据时所传参数
function queryParams(params) {
    var queryParameter = nowTreeNode.id;
    return {
        pageNumber: params.offset + 1,
        //每页多少条数据
        pageSize: params.limit,
        sORGId: queryParameter,
    };
}

function selectPeopleByTreeId() {

    $('#table_id_example').bootstrapTable('destroy');

    $('#table_id_example').bootstrapTable({
        //是否显示行间隔色
        striped: true,
        //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        cache: false,
        //是否显示分页（*）
        pagination: true,
        //是否启用排序
        sortable: false,
        //排序方式
        sortOrder: "asc",
        //每页的记录行数（*）
        pageSize: 10,
        //可供选择的每页的行数（*）
        pageList: [10, 25, 50, 100],
        //是否显示搜索
        search: false,
        //data:json,
        //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据
        url: "/lihuaiotweb01/peopleUnionORG/selectPeopleByORGId",
        contentType: "application/x-www-form-urlencoded",//必须要有！！！！
        method: 'post',                      //请求方式（*）
        dataType: "json",
        //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
        //queryParamsType:'',
        ////查询参数,每次调用是会带上这个参数，可自定义
        queryParamsType: 'limit',//查询参数组织方式
        queryParams: queryParams,
        //分页方式：client客户端分页，server服务端分页（*）
        sidePagination: "server",
        locale: 'zh-CN',//中文支持
        toolbar: '#toolbar',//指定工作栏

        columns: tableColumns,
    });
}

function initTable() {
    var questionColumns = [];
    $.ajax({
        type: 'POST',
        data: {},
        url: '/lihuaiotweb01/peopleUnionORG/PeopleHead',
        dataType: "json",
        success: function (result) {
            /*alert("1");*/
            var json = eval(result); //数组
            for (var i = 0; i < json.length; i++) {
                var temp = {field: json[i].data, title: json[i].title, align: json[i].align};//手动拼接columns
                questionColumns.push(temp);
            }
            ;
            var temp1 = {
                field: 'operation',
                title: '操作',
                formatter: function (value, row, index) {
                    var s = '<a class = "changeORG" href="javascript:void(0)">转移</a>';
                    var d = '<a class = "removeORG" href="javascript:void(0)">删除</a>';
                    return s + ' ' + d;
                },
                events: 'operateEvents'
            };
            questionColumns.push(temp1);
            tableColumns = questionColumns;
            $('#table_id_example').bootstrapTable('destroy');
            $('#table_id_example').bootstrapTable({
                columns: questionColumns,
            });
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

function changeUnionORG(personId, personName, orgId, orgName) {
    $.ajax({
        type: 'POST',
        data: {personId: personId, personName: personName, orgId: orgId, orgName: orgName},
        url: '/lihuaiotweb01/peopleUnionORG/changeUnionORG',
        dataType: "text",
        success: function (result) {
            /*alert("1");*/
            alert(result);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

window.operateEvents = {
    'click .changeORG': function (e, value, row, index) {
        initPeople();
        var $txt = $('.addNew-content').find('input');
        $($txt[0]).val(row.personName);
        selectPerson = row;
        initORG();
        $('#addNew-popup').show('slow');
        addNew();
    },
    'click .removeORG': function (e, value, row, index) {

    }
};

function addNewPersonAndORG() {
    initPeople();
    initORG();
    $('#addNew-popup').show('slow');
    addNew();
}

function saveUnionORG() {
    changeUnionORG(selectPerson.personId, selectPerson.personName, selectORG.id, selectORG.name)
    $('#addNew-popup').hide('slow');
    selectPeopleByTreeId();
}

/**
 * 新增页面
 * */
function addNew() {
    //保存按钮
    /*    $('#addNew-save').bind('click', function (e) {
            var a=document.getElementById("code").value;
            changeUnionORG(a,selectORG.id)
            $('#addNew-popup').hide('slow');
        });*/
    //取消按钮
    $('#addNew-cancel').bind('click', function (e) {
        $('#addNew-popup').hide('slow');
    });
    //x按钮
    $('#addNew-close').bind('click', function (e) {
        $('#addNew-popup').hide('slow');
    });
}

function initPeople() {
    $("#name").bsSuggest('init', {
        /*url: "/rest/sys/getuserlist?keyword=",
        effectiveFields: ["userName", "email"],
        searchFields: [ "shortAccount"],
        effectiveFieldsAlias:{userName: "姓名"},*/
        clearable: true,
        url: "/lihuaiotweb01/peopleUnionORG/selectPersonNode",
        idField: "personId",
        keyField: "personName",
        processData: function (json) {// url 获取数据时，对数据的处理，作为 getData 的回调函数
            var i, len, data = {value: []};
            if (!json || json.length == 0) {
                return false;
            }
            len = json.length;
            for (i = 0; i < len; i++) {
                data.value.push({
                    "personName": json[i].personName,
                    "personId": json[i].personId,
                    "orgName": json[i].orgName,
                });
            }
            console.log(data);
            return data;
        }
    }).on('onDataRequestSuccess', function (e, result) {
        console.log('onDataRequestSuccess: ', result);
    }).on('onSetSelectValue', function (e, keyword, data) {
        console.log('onSetSelectValue: ', keyword, data);
        selectPerson = data
    }).on('onUnsetSelectValue', function () {
        console.log('onUnsetSelectValue');
    });
}


function initORG() {
    $("#org").bsSuggest('init', {
        /*url: "/rest/sys/getuserlist?keyword=",
        effectiveFields: ["userName", "email"],
        searchFields: [ "shortAccount"],
        effectiveFieldsAlias:{userName: "姓名"},*/
        clearable: true,
        url: "/lihuaiotweb01/peopleUnionORG/selectZTreeNode",
        idField: "id",
        keyField: "name",
        processData: function (json) {// url 获取数据时，对数据的处理，作为 getData 的回调函数
            var i, len, data = {value: []};
            if (!json || json.length == 0) {
                return false;
            }
            len = json.length;
            for (i = 0; i < len; i++) {
                data.value.push({
                    "name": json[i].name,
                    "id": json[i].id,
                });
            }
            console.log(data);
            return data;
        }
    }).on('onDataRequestSuccess', function (e, result) {
        console.log('onDataRequestSuccess: ', result);
    }).on('onSetSelectValue', function (e, keyword, data) {
        console.log('onSetSelectValue: ', keyword, data);
        selectORG = data
    }).on('onUnsetSelectValue', function () {
        console.log('onUnsetSelectValue');
    });
}

