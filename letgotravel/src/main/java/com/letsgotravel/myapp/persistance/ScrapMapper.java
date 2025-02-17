package com.letsgotravle.myapp.persistance;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.letsgotravle.myapp.domain.ScrapVo;

@Mapper
public interface ScrapMapper {
   
    List<ScrapVo> getScrapList(Map<String, Object> paramMap);
    int scrapTotalCount(int midx);
    ScrapVo scrapSelectOne(int sidx);
}
