package com.system.service;

import com.system.po.zTreeNode;

import java.util.List;

public interface ORGzTreeService {
    List<zTreeNode> selectORGInfo() throws Exception;
    List<zTreeNode> selectORGInfoByNameAndParentId( String parentId,String sNodeName) throws Exception;
    int insertORGInfo(String parentId,String childName) throws Exception;
    List<zTreeNode>  selectORGInfoByNodeId(String nodeId) throws Exception;
    List<zTreeNode>  selectORGInfoByParentId(String nodeId) throws Exception;
    int deleteORGInfoByNodeId(String nodeId) throws Exception;
    int updateORGInfoByNodeId(String nodeId,String nodeName) throws Exception;
}
