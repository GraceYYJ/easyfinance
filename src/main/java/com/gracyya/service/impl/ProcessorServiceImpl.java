package com.gracyya.service.impl;

import com.gracyya.dao.ProcessorMapper;
import com.gracyya.model.Processor;
import com.gracyya.service.ProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessorServiceImpl implements ProcessorService{
    @Autowired
    ProcessorMapper processorMapper;
    public int deleteByPrimaryKey(Long id){return processorMapper.deleteByPrimaryKey(id);}
    public int insert(Processor record){return processorMapper.insert(record);}
    public Processor selectByPrimaryKey(Long id){return processorMapper.selectByPrimaryKey(id);}
    public List<Processor> getAllProcessors(){return processorMapper.getAllProcessors();}
    public int updateByPrimaryKey(Processor record){return processorMapper.updateByPrimaryKey(record);}
    public int updateProcessor(Long id, String sitename, String domain, String starturl,
                               String linkstr, String bodytextstr,String pubtimestr,
                               String sourcestr, String titlestr, String helpurlstr){
        Processor processor=new Processor(id,sitename,domain,starturl,linkstr,bodytextstr,
                                            pubtimestr,sourcestr,titlestr,helpurlstr);
        return updateByPrimaryKey(processor);
    }
}
