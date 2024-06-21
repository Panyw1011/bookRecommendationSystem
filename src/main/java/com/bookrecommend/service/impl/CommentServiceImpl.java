package com.bookrecommend.service.impl;


import com.bookrecommend.dao.CommentDao;
import com.bookrecommend.pojo.PageInfo;
import com.bookrecommend.pojo.Comment;
import com.bookrecommend.pojo.Ratings;
import com.bookrecommend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentDao commentDao;

    @Override
    public PageInfo<Comment> findPageInfo(Integer c_id, String book_name, String username, String comment, Integer book_id, Integer pageIndex, Integer pageSize) {
        PageInfo<Comment> pi = new PageInfo<Comment>();
        pi.setPageIndex(pageIndex);
        pi.setPageSize(pageSize);
        Integer totalCount = commentDao.totalCount(c_id,book_name,username,comment,book_id);
        if (totalCount>0){
            pi.setTotalCount(totalCount);
            List<Comment> commentList = commentDao.getCommentList(c_id,book_name,username,comment,book_id,(pi.getPageIndex()-1)*pi.getPageSize(),pi.getPageSize());
            pi.setList(commentList);
        }
        return pi;
    }

    @Override
    public int delComment(Integer c_id) {
        return commentDao.delComment(c_id);
    }

    @Override
    public int deleteSelectedComment(String ck) {
        return commentDao.deleteSelectedComment(ck);
    }

    @Override
    public int approve(Integer c_id) {
        return commentDao.approve(c_id);
    }

    @Override
    public int addComment(Integer book_id, Integer u_id, String comment) {
        return commentDao.addComment(book_id,u_id,comment);
    }

    @Override
    public int addRating(Ratings ratings) {
        return commentDao.addRating(ratings);
    }

}
