package com.system.mapper;


import com.system.po.DeviceInfo;
import com.system.po.PagingVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceInfoMapper {
    Integer getCountDevice();
    List<DeviceInfo> selectDeviceInfoByPaging(PagingVO pagingVO);
    List<DeviceInfo> selectDeviceInfo();
    DeviceInfo selectDeviceInfoById(String deviceId);
    Integer updateDeviceByPrimaryKey(DeviceInfo deviceInfo);
    Integer updateDeviceInfoByNodeId(@Param("sSerialNum")String sSerialNum,@Param("sNodeId")String sNodeId);
    List<DeviceInfo> selectDeviceInfoByIDs(@Param("deviceIds") String[] deviceIds);
    List<DeviceInfo> selectDeviceByORGId(String orgId);
    List<DeviceInfo> selectDeviceByORGNULL(String orgId);

}
