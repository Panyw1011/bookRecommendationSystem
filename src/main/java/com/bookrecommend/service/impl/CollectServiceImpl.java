package com.bookrecommend.service.impl;

import com.bookrecommend.dao.CollectDao;
import com.bookrecommend.pojo.Collect;
import com.bookrecommend.pojo.PageInfo;
import com.bookrecommend.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectDao collectionDao;

    @Override
    public int collect(Integer u_id, Integer book_id) {
        return collectionDao.collect(u_id,book_id);
    }

    @Override
    public PageInfo<Collect> findPageInfo(Integer u_id, Integer pageIndex, Integer pageSize) {
        PageInfo<Collect> pi = new PageInfo<Collect>();
        pi.setPageIndex(pageIndex);
        pi.setPageSize(pageSize);
        Integer totalCount = collectionDao.totalCount(u_id);
        if (totalCount>0){
            pi.setTotalCount(totalCount);
            List<Collect> collectList = collectionDao.getCollectById(u_id,(pi.getPageIndex()-1)*pi.getPageSize(),pi.getPageSize());
            pi.setList(collectList);
        }
        return pi;
    }

    @Override
    public int delCollect(Integer co_id) {
        return collectionDao.delCollect(co_id);
    }
}
