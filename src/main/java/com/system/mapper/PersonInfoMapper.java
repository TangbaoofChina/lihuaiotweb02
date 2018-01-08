package com.system.mapper;

import com.system.po.PagingVO;
import com.system.po.PeopleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonInfoMapper {
    List<PeopleInfo> selectPeopleInfoByORGID(@Param("nodeId") String nodeId);
    List<PeopleInfo> selectAllPeopleInfo();
    List<PeopleInfo> selectPeopleByPeopleId(@Param("peopleId") String peopleId);
    Integer updatePeopleByPeopleId(PeopleInfo peopleInfo);
    Integer insertPeople(@Param("peopleInfo") PeopleInfo peopleInfo);
}
