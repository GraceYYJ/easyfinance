package com.gracyya.service;

import com.gracyya.model.Myuser;

/**
 * Created by Administrator on 2018/5/16.
 */
public interface UserService {
    public Myuser getUserById(Long id);
    public Myuser getByName(String name);
    public Myuser getPasswordByName(String name);
}
