package com.bookrecommend.dao;

import com.bookrecommend.pojo.Pay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PayDao {
    Integer totalCount(@Param("reason") String reason, @Param("is_pay") String is_pay);
    List<Pay> getPayList(@Param("reason") String reason, @Param("is_pay") String is_pay, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);
    int addPay(Pay pay);
    Pay findPayById(Integer b_id);
    int updatePay(Pay pay);
}
