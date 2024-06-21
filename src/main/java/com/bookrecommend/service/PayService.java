package com.bookrecommend.service;

import com.bookrecommend.pojo.Pay;
import com.bookrecommend.pojo.PageInfo;

public interface PayService {
    PageInfo<Pay> findPageInfo(String reason, String is_pay, Integer pageIndex, Integer pageSize);
    int addPay(Pay pay);
    Pay findPayById(Integer b_id);
    int updatePay(Pay pay);
}
