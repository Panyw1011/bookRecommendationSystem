package com.bookrecommend.service.impl;


import com.bookrecommend.dao.PayDao;
import com.bookrecommend.pojo.Pay;
import com.bookrecommend.pojo.PageInfo;
import com.bookrecommend.service.PayService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PayServiceImpl implements PayService{
    @Autowired
    private PayDao payDao;

    @Override
    public PageInfo<Pay> findPageInfo(String reason, String is_pay, Integer pageIndex, Integer pageSize) {
        PageInfo<Pay> pi = new PageInfo<Pay>();
        pi.setPageIndex(pageIndex);
        pi.setPageSize(pageSize);
        Integer totalCount = payDao.totalCount(reason,is_pay);
        if (totalCount>0){
            pi.setTotalCount(totalCount);
            List<Pay> payList = payDao.getPayList(reason,is_pay,(pi.getPageIndex()-1)*pi.getPageSize(),pi.getPageSize());
            pi.setList(payList);
        }
        return pi;
    }

    @Override
    public int addPay(Pay pay) {
        return payDao.addPay(pay);
    }

    @Override
    public Pay findPayById(Integer b_id) {
        Pay pay = payDao.findPayById(b_id);
        return pay;
    }

    @Override
    public int updatePay(Pay pay) {
        return payDao.updatePay(pay);
    }

}
