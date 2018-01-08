package com.system.service;


import com.system.po.DeviceInfo;

import java.util.List;

public interface DeviceInfoService {
    Integer getCountDevice() throws Exception;
    List<DeviceInfo>  findByPaging(Integer toPageNo) throws Exception;
    List<DeviceInfo> selectDeviceInfo() throws Exception;
    List<DeviceInfo> selectDeviceInfoByIDs(String[] sDeviceIds) throws Exception;
    DeviceInfo findBySerialNum(String sSerialNum) throws Exception;
    void updateBySerialNum(String sSerialNum,DeviceInfo deviceInfo) throws Exception;
    int updateDeviceInfoByNodeId(String sSerialNum,String sNodeId) throws Exception;
}
