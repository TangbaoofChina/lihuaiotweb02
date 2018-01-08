package com.system.service;

import com.system.po.DataTablePageing;
import com.system.po.PeopleInfo;

import java.util.List;

public interface PeopleUnionORGService {
    DataTablePageing selectPeopleByORGIdPaging(Integer pageNumber, Integer pageSize, String orgId) throws Exception;
    List<PeopleInfo> selectAllPeopleInfo() throws Exception;
    Integer insertUpdatePeople(PeopleInfo peopleInfo) throws Exception;

}
