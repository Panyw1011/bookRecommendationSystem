package com.bookrecommend.service;

import com.bookrecommend.pojo.PageInfo;
import com.bookrecommend.pojo.Comment;
import com.bookrecommend.pojo.Ratings;

public interface CommentService {
    PageInfo<Comment> findPageInfo(Integer c_id, String book_name, String username, String comment, Integer book_id, Integer pageIndex, Integer pageSize);
    int delComment(Integer c_id);
    int deleteSelectedComment(String ck);
    int approve(Integer c_id);
    int addComment(Integer book_id, Integer u_id, String comment);
    int addRating(Ratings ratings);
}
