package com.system.mapper;

import com.system.po.DeviceMessage;
import com.system.po.PagingVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceMessageMapper {
    //分页查询设备实时数据
    List<DeviceMessage> selectRealDeviceMsgByPaging(PagingVO pagingVO);
    //根据名称模糊查询设备
    List<DeviceMessage> selectRealDeviceMsgByName(String sDeviceName);

    //查询设备总数
    Integer getCountDevice();
    //所有设备实时数据
    List<DeviceMessage> selectRealDeviceMessage();
    //指定设备历史数据
    List<DeviceMessage> selectHisDevMsgByTimeAndID(@Param("deviceId") String sDeviceId,
                                                   @Param("startDate") String sStartTime,
                                                   @Param("endDate") String sEndDate);
    List<DeviceMessage> selectHisDevMsgByTimeAndIDs(@Param("deviceIds") String[] deviceIds,
                                                    @Param("startDate") String sStartTime,
                                                    @Param("endDate") String sEndDate);
    List<DeviceMessage> selectHisDevMsgByTimeAndIDsAndPage(@Param("smallIndex") Integer smallIndex,
                                                           @Param("bigIndex") Integer bigIndex,
                                                           @Param("deviceIds") String[] deviceIds,
                                                           @Param("startDate") String sStartTime,
                                                           @Param("endDate") String sEndDate);
}
