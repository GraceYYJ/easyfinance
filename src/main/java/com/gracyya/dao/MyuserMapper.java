package com.gracyya.dao;

import com.gracyya.model.Myuser;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

public interface MyuserMapper {
    Myuser selectByPrimaryKey(Long id);
    Myuser selectByName(String name);
    Myuser getPasswordByName(String name);
}