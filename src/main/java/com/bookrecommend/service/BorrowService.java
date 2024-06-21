package com.bookrecommend.service;

import com.bookrecommend.pojo.Borrow;
import com.bookrecommend.pojo.PageInfo;

import java.util.List;

public interface BorrowService {
    PageInfo<Borrow> findPageInfo(Integer book_id, String username, String state, Integer pageIndex, Integer pageSize);
    int addBorrow(Borrow borrow);
    Borrow findBorrowById(Integer b_id);
    int updateBorrow(Borrow borrow);
    int borrow(Integer book_id, Integer u_id);
    List<Borrow> findBorrowByUerId(Integer u_id);
    int renew(Integer b_id);
    int retu(Integer b_id);
    int isBorrow(Integer book_id, Integer u_id);
    Borrow findBorrowByBId(Integer b_id);
}
