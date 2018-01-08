package com.system.service.impl;

import com.system.mapper.ORGzTreeMapper;
import com.system.po.zTreeNode;
import com.system.service.ORGzTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ORGzTreeServiceImpl implements ORGzTreeService {

    @Autowired
    private ORGzTreeMapper orGzTreeMapper;

    @Override
    public List<zTreeNode> selectORGInfo() throws Exception {
        return orGzTreeMapper.selectORGInfo();
    }

    @Override
    public List<zTreeNode> selectORGInfoByNameAndParentId(String parentId, String sNodeName) throws Exception {
        return orGzTreeMapper.selectORGInfoByNameAndParentId(parentId,sNodeName);
    }

    @Override
    public int insertORGInfo(String parentId, String childName) throws Exception {
        return  orGzTreeMapper.insertORGInfo(parentId,childName);
    }

    @Override
    public List<zTreeNode> selectORGInfoByNodeId(String nodeId) throws Exception {
        return orGzTreeMapper.selectORGInfoByNodeId(nodeId);
    }

    @Override
    public int deleteORGInfoByNodeId(String nodeId) throws Exception {
        return orGzTreeMapper.deleteORGInfoByNodeId(nodeId);
    }

    @Override
    public List<zTreeNode> selectORGInfoByParentId(String nodeId) throws Exception {
        return orGzTreeMapper.selectORGInfoByParentId(nodeId);
    }

    @Override
    public int updateORGInfoByNodeId(String nodeId, String nodeName) throws Exception {
        return orGzTreeMapper.updateORGInfoByNodeId(nodeId,nodeName);
    }
}
