package com.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.system.mapper.DeviceInfoMapper;
import com.system.po.DataTablePageing;
import com.system.po.DeviceInfo;
import com.system.service.DeviceUnionORGService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceUnionORGServiceImpl implements DeviceUnionORGService {
    @Autowired
    private DeviceInfoMapper deviceInfoMapper;

    @Override
    public DataTablePageing selectDeviceByORGIdPaging(Integer pageNumber,Integer pageSize,String orgId) throws Exception {
        DataTablePageing dataTablePageing = new DataTablePageing();
        Integer bigIndex = 0;
        Integer smallIndex = 0;
        smallIndex = pageNumber-1;
        bigIndex = smallIndex + pageSize;
        List<DeviceInfo> deviceInfoList = new ArrayList<DeviceInfo>();
        if (orgId.equals("0002")) {
            deviceInfoList = deviceInfoMapper.selectDeviceByORGNULL("0002");
        }else {
            deviceInfoList = deviceInfoMapper.selectDeviceByORGId(orgId);
        }
        //截取部分字符串
        List<DeviceInfo> deviceInfoListSub = new ArrayList<DeviceInfo>();
        if(bigIndex > deviceInfoList.size())
            bigIndex = deviceInfoList.size();
        deviceInfoListSub.addAll(deviceInfoList.subList(smallIndex,bigIndex));
        String jsonString = JSON.toJSONString(deviceInfoListSub);
        dataTablePageing.setTotal(deviceInfoList.size());
        dataTablePageing.setsReturnJson(jsonString);
        return dataTablePageing;
    }
}
