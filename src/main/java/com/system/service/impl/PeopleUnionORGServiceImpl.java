package com.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.system.mapper.DeviceInfoMapper;
import com.system.mapper.PersonInfoMapper;
import com.system.po.DataTablePageing;
import com.system.po.DeviceInfo;
import com.system.po.PeopleInfo;
import com.system.service.DeviceUnionORGService;
import com.system.service.PeopleUnionORGService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeopleUnionORGServiceImpl implements PeopleUnionORGService {
    @Autowired
    private PersonInfoMapper personInfoMapper;

    @Override
    public DataTablePageing selectPeopleByORGIdPaging(Integer pageNumber,Integer pageSize,String orgId) throws Exception {
        DataTablePageing dataTablePageing = new DataTablePageing();
        Integer bigIndex = 0;
        Integer smallIndex = 0;
        smallIndex = pageNumber-1;
        bigIndex = smallIndex + pageSize;
        List<PeopleInfo> peopleInfoList = new ArrayList<PeopleInfo>();

        peopleInfoList = personInfoMapper.selectPeopleInfoByORGID(orgId);
        //截取部分字符串
        List<PeopleInfo> peopleInfoListSub = new ArrayList<PeopleInfo>();
        if(bigIndex > peopleInfoList.size())
            bigIndex = peopleInfoList.size();
        peopleInfoListSub.addAll(peopleInfoList.subList(smallIndex,bigIndex));
        String jsonString = JSON.toJSONString(peopleInfoListSub);
        dataTablePageing.setTotal(peopleInfoListSub.size());
        dataTablePageing.setsReturnJson(jsonString);
        return dataTablePageing;
    }

    @Override
    public List<PeopleInfo> selectAllPeopleInfo() throws Exception {
        return personInfoMapper.selectAllPeopleInfo();
    }

    @Override
    public Integer insertUpdatePeople(PeopleInfo peopleInfo) throws Exception {
        if (personInfoMapper.selectPeopleByPeopleId(peopleInfo.getPersonId()).size() >0)
            return personInfoMapper.updatePeopleByPeopleId(peopleInfo);
        else
            return personInfoMapper.insertPeople(peopleInfo);
    }
}
