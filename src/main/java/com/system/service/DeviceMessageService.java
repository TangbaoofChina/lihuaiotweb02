package com.system.service;


import com.system.po.DataTablePageing;
import com.system.po.DeviceInfo;
import com.system.po.DeviceMessage;
import com.system.po.ParameterCharts;

import java.util.List;

public interface DeviceMessageService {
    Integer getCountDevice() throws Exception;

    List<DeviceMessage> selectRealDeviceMessage() throws Exception;
    List<DeviceMessage> selectRealDeviceMsgByPaging(Integer toPageNo) throws Exception;
    List<DeviceMessage> selectRealDataByName(String sDeviceName) throws Exception;
    List<DeviceMessage> selectHisDeviceMessageByTimeAndId(String sDeviceId, String sStartDate, String sEndDate) throws Exception;

    //多个设备同一个参数的曲线
    ParameterCharts selectHisDevMsgByTimeAndIDs(String[] sDeviceIds, String sQueryParam, String sStartDate, String sEndDate) throws Exception;
    //多个设备同一个参数的列表
    String selectHisDevMsgByTimeAndIDs(List<DeviceInfo> deviceInfoList, String[] sDeviceIds, String sQueryParam, String sStartDate, String sEndDate) throws Exception;
    //多个设备同一个参数的列表;分页
    DataTablePageing selectHisDevMsgByTimeAndIDsAndPage(Integer pageNumber, Integer pageSize, List<DeviceInfo> deviceInfoList, String[] sDeviceIds, String sQueryParam, String sStartDate, String sEndDate) throws Exception;

}
