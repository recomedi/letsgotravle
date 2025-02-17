package com.letsgotravle.myapp.service;

import java.util.List;
import com.letsgotravle.myapp.domain.Criteria;
import com.letsgotravle.myapp.domain.ScrapVo;

public interface ScrapService {
    
    List<ScrapVo> getScrapList(int midx, Criteria cri);
    int scrapTotalCount(int midx);
    ScrapVo scrapSelectOne(int sidx);
}