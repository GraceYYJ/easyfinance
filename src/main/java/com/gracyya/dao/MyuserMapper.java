package com.gracyya.dao;

import com.gracyya.model.Myuser;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyuserMapper {
    Myuser selectByPrimaryKey(Long id);
    Myuser selectByName(String name);
    Myuser getPasswordByName(String name);
}