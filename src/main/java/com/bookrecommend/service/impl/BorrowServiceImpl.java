package com.bookrecommend.service.impl;


import com.bookrecommend.dao.BookDao;
import com.bookrecommend.dao.BorrowDao;
import com.bookrecommend.pojo.Borrow;
import com.bookrecommend.pojo.PageInfo;
import com.bookrecommend.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BorrowServiceImpl implements BorrowService{
    @Autowired
    private BorrowDao borrowDao;
    @Autowired
    private BookDao bookDao;

    @Override
    public PageInfo<Borrow> findPageInfo(Integer book_id, String username, String state, Integer pageIndex, Integer pageSize) {
        PageInfo<Borrow> pi = new PageInfo<Borrow>();
        pi.setPageIndex(pageIndex);
        pi.setPageSize(pageSize);
        Integer totalCount = borrowDao.totalCount(book_id,username,state);
        if (totalCount>0){
            pi.setTotalCount(totalCount);
            List<Borrow> borrowList = borrowDao.getBorrowList(book_id,username,state,(pi.getPageIndex()-1)*pi.getPageSize(),pi.getPageSize());
            pi.setList(borrowList);
        }
        return pi;
    }

    @Override
    public int addBorrow(Borrow borrow) {
        return borrowDao.addBorrow(borrow);
    }

    @Override
    public Borrow findBorrowById(Integer b_id) {
        Borrow borrow = borrowDao.findBorrowById(b_id);
        return borrow;
    }

    @Override
    public int updateBorrow(Borrow borrow) {
        return borrowDao.updateBorrow(borrow);
    }

    @Override
    public int borrow(Integer book_id, Integer u_id) {
        return borrowDao.borrow(book_id,u_id);
    }

    @Override
    public List<Borrow> findBorrowByUerId(Integer u_id) {
        List<Borrow> borrowList = borrowDao.findBorrowByUerId(u_id);
        return borrowList;
    }

    @Override
    public int renew(Integer b_id) {
        return borrowDao.renew(b_id);
    }

    @Override
    public int retu(Integer b_id) {
        borrowDao.retu(b_id);
        Borrow borrow = borrowDao.findBorrowById(b_id);
        int i = bookDao.upBookCount(borrow.getBook_id());
        return i;
    }

    @Override
    public int isBorrow(Integer book_id, Integer u_id) {
        return borrowDao.isBorrow(book_id,u_id);
    }

    @Override
    public Borrow findBorrowByBId(Integer b_id) {
        Borrow borrow = borrowDao.findBorrowByBId(b_id);
        return borrow;
    }

}
