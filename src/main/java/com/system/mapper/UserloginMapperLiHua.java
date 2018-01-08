package com.system.mapper;

import com.system.po.Userlogin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserloginMapperLiHua {
    List<Userlogin> selectByName(@Param("sName") String sName);
}
