var parentNode;
var nextNode;
var treeNodes;
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
            var inputFather = document.getElementById("inputFather");
            var inputFatherId = document.getElementById("inputFatherId");
            inputFather.value = treeNode.name;
            inputFatherId.value = treeNode.id;
            nextNode = treeNode.getNextNode();
            parentNode = treeNode.getParentNode();
            treeNodeHighLightClear();
            if (treeNode.isParent) {
                zTree.expandNode(treeNode);

            } else {

            }
        }
    }
};

var zNodes = [
    {id: 1, pId: 0, name: "水果", open: true},
    {id: 101, pId: 1, name: "苹果"},
    {id: 102, pId: 1, name: "香蕉"},
    {id: 103, pId: 1, name: "梨"},
    {id: 10101, pId: 101, name: "红富士苹果"},
    {id: 10102, pId: 101, name: "红星苹果"},
    {id: 10103, pId: 101, name: "嘎拉"},
    {id: 10104, pId: 101, name: "桑萨"},
    {id: 10201, pId: 102, name: "千层蕉"},
    {id: 10202, pId: 102, name: "仙人蕉"},
    {id: 10203, pId: 102, name: "吕宋蕉"},
];

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

function addNewChildNode() {
    var inputFather = document.getElementById("inputFather");
    var inputFatherId = document.getElementById("inputFatherId");
    var inputChild = document.getElementById("inputSon");
    var inputFatherVal = inputFather.value;
    var inputFatherIdVal = inputFatherId.value;
    var inputChildVal = inputChild.value;
    $.ajax({
        url: "/lihuaiotweb01/zTree/addZTreeNodeChild",
// 数据发送方式
        type: "POST",
// 接受数据格式
        dataType: "json",
// 要传递的数据
        data: {parentId: inputFatherIdVal, childName: inputChildVal},
        success: function (result) {
            var json = eval(result); //数组
            FindZTreeNode(inputChildVal);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

function removeSelectNode() {
    var inputFatherId = document.getElementById("inputFatherId");
    var inputFatherIdVal = inputFatherId.value;
    $.ajax({
        url: "/lihuaiotweb01/zTree/removeZTreeNode",
// 数据发送方式
        type: "POST",
// 接受数据格式
        dataType: "text",
// 要传递的数据
        data: {nodeId: inputFatherIdVal},
        success: function (result) {
            if (result === "删除成功") {
                if (nextNode != null) {
                    FindZTreeNode(nextNode.name);
                } else {
                    if (parentNode != null) {
                        FindZTreeNode(parentNode.name);
                    }
                }
            }
            else {
                alert(result);
            }

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

function renameSelectNode() {
    var inputFather = document.getElementById("inputFather");
    var inputFatherId = document.getElementById("inputFatherId");
    var inputFatherVal = inputFather.value;
    var inputFatherIdVal = inputFatherId.value;
    $.ajax({
        url: "/lihuaiotweb01/zTree/renameZTreeNode",
// 数据发送方式
        type: "POST",
// 接受数据格式
        dataType: "text",
// 要传递的数据
        data: {nodeId: inputFatherIdVal, nodeName: inputFatherVal},
        success: function (result) {
            if (result === "修改成功") {
                FindZTreeNode(inputFatherVal);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}