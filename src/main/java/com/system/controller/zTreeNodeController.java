package com.system.controller;

import com.alibaba.fastjson.JSON;
import com.system.po.zTreeNode;
import com.system.service.ORGzTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/zTree")
public class zTreeNodeController {

    @Autowired
    private ORGzTreeService orGzTreeService;

    @RequestMapping(value = "selectZTreeNode", method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String selectZTreeNode() throws Exception {

        List<zTreeNode> zTreeNodeList = orGzTreeService.selectORGInfo();

        String jsonString = JSON.toJSONString(zTreeNodeList);

        return jsonString;
    }

    @RequestMapping(value = "addZTreeNodeChild", method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String addZTreeNodeChild(String parentId, String childName) throws Exception {
        List<zTreeNode> zTreeNodeList = orGzTreeService.selectORGInfoByNameAndParentId(parentId, childName);
        if (zTreeNodeList.size() == 0) {
            orGzTreeService.insertORGInfo(parentId, childName);
            zTreeNodeList = orGzTreeService.selectORGInfoByNameAndParentId(parentId, childName);
        }
        String jsonString = JSON.toJSONString(zTreeNodeList);
        return jsonString;
    }

    @RequestMapping(value = "removeZTreeNode", method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String removeZTreeNode(String nodeId) throws Exception {
        List<zTreeNode> zTreeNodeChildList = orGzTreeService.selectORGInfoByParentId(nodeId);
        String jsonString = "删除成功";
        if (zTreeNodeChildList.size() > 0) {
            jsonString = "存在子节点，不能删除";
        } else {
            List<zTreeNode> zTreeNodeList = orGzTreeService.selectORGInfoByNodeId(nodeId);
            if (zTreeNodeList.size() > 0) {
                int iResult = orGzTreeService.deleteORGInfoByNodeId(nodeId);
            }
        }
        return jsonString;
    }

    @RequestMapping(value = "renameZTreeNode", method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String renameZTreeNode(String nodeId,String nodeName) throws Exception {
        String jsonString = "修改成功";

        List<zTreeNode> zTreeNodeList = orGzTreeService.selectORGInfoByNodeId(nodeId);
        if (zTreeNodeList.size() > 0) {
            int iResult = orGzTreeService.updateORGInfoByNodeId(nodeId,nodeName);
        }
        return jsonString;
    }

}
