package com.system.mapper;

import com.system.po.zTreeNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ORGzTreeMapper {
    List<zTreeNode> selectORGInfo();
    int insertORGInfo(@Param("parentId") String parentId,@Param("childName") String childName);
    List<zTreeNode> selectORGInfoByNameAndParentId(@Param("parentId") String parentId,@Param("sNodeName") String sNodeName);
    List<zTreeNode> selectORGInfoByNodeId(@Param("nodeId") String nodeId);
    int deleteORGInfoByNodeId(@Param("nodeId") String nodeId);
    List<zTreeNode>  selectORGInfoByParentId(@Param("nodeId") String nodeId);
    int updateORGInfoByNodeId(@Param("nodeId")String nodeId,@Param("nodeName")String nodeName);
}
