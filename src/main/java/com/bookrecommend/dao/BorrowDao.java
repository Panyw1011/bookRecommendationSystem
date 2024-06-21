package com.bookrecommend.dao;

import com.bookrecommend.pojo.Borrow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BorrowDao {
    Integer totalCount(@Param("book_id") Integer book_id, @Param("username") String username, @Param("state") String state);
    List<Borrow> getBorrowList(@Param("book_id") Integer book_id, @Param("username") String username, @Param("state") String state, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);
    int addBorrow(Borrow borrow);
    Borrow findBorrowById(Integer b_id);
    int updateBorrow(Borrow borrow);
    int borrow(@Param("book_id") Integer book_id, @Param("u_id") Integer u_id);
    List<Borrow> findBorrowByUerId(Integer u_id);
    int renew(Integer b_id);
    int retu(Integer b_id);
    int isBorrow(@Param("book_id") Integer book_id, @Param("u_id") Integer u_id);
    Borrow findBorrowByBId(Integer b_id);
    int payMoney(Integer b_id);
}
