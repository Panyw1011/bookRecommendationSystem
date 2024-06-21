package com.bookrecommend.service;

import com.bookrecommend.pojo.Collect;
import com.bookrecommend.pojo.PageInfo;


public interface CollectService {
    int collect(Integer u_id, Integer book_id);
    PageInfo<Collect> findPageInfo(Integer u_id, Integer pageIndex, Integer pageSize);
    int delCollect(Integer co_id);
}
