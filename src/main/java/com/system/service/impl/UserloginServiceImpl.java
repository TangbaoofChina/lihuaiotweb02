package com.system.service.impl;

import com.system.mapper.UserloginMapper;
import com.system.mapper.UserloginMapperLiHua;
import com.system.po.Userlogin;
import com.system.po.UserloginExample;
import com.system.service.UserloginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jacey on 2017/6/29.
 */
@Service
public class UserloginServiceImpl implements UserloginService {

    @Autowired
    private UserloginMapper userloginMapper;

    @Autowired
    private UserloginMapperLiHua userloginMapperLiHua;

    public Userlogin findByName(String name) throws Exception {
        UserloginExample userloginExample = new UserloginExample();

        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andUsernameEqualTo(name);

        List<Userlogin> list = userloginMapper.selectByExample(userloginExample);
        return list.get(0);
    }

    public Userlogin findByNameLiHua(String name) throws Exception {

        List<Userlogin> list = userloginMapperLiHua.selectByName(name);
        return list.get(0);
    }

    public void save(Userlogin userlogin) throws Exception {
        userloginMapper.insert(userlogin);
    }

    public void removeByName(String name) throws Exception {
        UserloginExample userloginExample = new UserloginExample();

        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andUsernameEqualTo(name);

        userloginMapper.deleteByExample(userloginExample);
    }

    @Override
    public void updateByName(String name, Userlogin userlogin) throws Exception {
        UserloginExample userloginExample = new UserloginExample();

        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andUsernameEqualTo(name);

        userloginMapper.updateByExample(userlogin, userloginExample);
    }

}
