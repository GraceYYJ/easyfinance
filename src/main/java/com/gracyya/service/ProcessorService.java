package com.gracyya.service;

import com.gracyya.model.Processor;
import java.util.List;

public interface ProcessorService {
    public int deleteByPrimaryKey(Long id);
    public int insert(Processor record);
    public Processor selectByPrimaryKey(Long id);
    public List<Processor> getAllProcessors();
    public int updateByPrimaryKey(Processor record);
    public int updateProcessor(Long id, String sitename, String domain, String starturl,
                               String linkstr, String bodytextstr,String pubtimestr,
                               String sourcestr, String titlestr, String helpurlstr);

}
