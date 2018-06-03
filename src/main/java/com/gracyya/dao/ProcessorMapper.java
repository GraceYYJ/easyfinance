package com.gracyya.dao;

import com.gracyya.model.Processor;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

public interface ProcessorMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Processor record);
    Processor selectByPrimaryKey(Long id);
    List<Processor> getAllProcessors();
    int updateByPrimaryKey(Processor record);
}