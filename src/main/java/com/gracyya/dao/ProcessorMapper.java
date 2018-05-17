package com.gracyya.dao;

import com.gracyya.model.Processor;
import com.gracyya.model.ProcessorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProcessorMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Processor record);
    Processor selectByPrimaryKey(Long id);
    int updateByPrimaryKey(Processor record);
}