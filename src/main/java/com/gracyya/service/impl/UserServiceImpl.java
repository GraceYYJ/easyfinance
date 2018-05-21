package com.gracyya.service.impl;

import com.gracyya.dao.MyuserMapper;
import com.gracyya.model.Myuser;
import com.gracyya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/5/15.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private MyuserMapper myuserMapper;
    public Myuser getUserById(Long id){
        return myuserMapper.selectByPrimaryKey(id);
    }
    public Myuser getByName(String name){return myuserMapper.selectByName(name);}
    public Myuser getPasswordByName(String name){return myuserMapper.getPasswordByName(name);}
}
