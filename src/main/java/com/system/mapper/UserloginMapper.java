package com.system.mapper;

import com.system.po.Userlogin;
import com.system.po.UserloginExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserloginMapper {
    int countByExample(UserloginExample example);

    int deleteByExample(UserloginExample example);

    int deleteByPrimaryKey(String userid);

    int insert(Userlogin record);

    int insertSelective(Userlogin record);

    List<Userlogin> selectByExample(UserloginExample example);

    List<Userlogin> selectByName(@Param("sName") String sName);

    Userlogin selectByPrimaryKey(String userid);

    int updateByExampleSelective(@Param("record") Userlogin record, @Param("example") UserloginExample example);

    int updateByExample(@Param("record") Userlogin record, @Param("example") UserloginExample example);

    int updateByPrimaryKeySelective(Userlogin record);

    int updateByPrimaryKey(Userlogin record);
}