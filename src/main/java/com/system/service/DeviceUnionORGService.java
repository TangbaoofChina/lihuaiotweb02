package com.system.service;

import com.system.po.DataTablePageing;
import com.system.po.DeviceInfo;

import java.util.List;

public interface DeviceUnionORGService {
    DataTablePageing selectDeviceByORGIdPaging(Integer pageNumber,Integer pageSize,String orgId) throws Exception;
}
