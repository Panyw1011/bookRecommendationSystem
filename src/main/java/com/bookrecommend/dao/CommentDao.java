package com.bookrecommend.dao;

import com.bookrecommend.pojo.Comment;
import com.bookrecommend.pojo.Ratings;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentDao {
    Integer totalCount(@Param("c_id") Integer c_id, @Param("book_name") String book_name, @Param("username") String username, @Param("comment") String comment, @Param("book_id") Integer book_id);
    List<Comment> getCommentList(@Param("c_id") Integer c_id, @Param("book_name") String book_name, @Param("username") String username, @Param("comment") String comment, @Param("book_id") Integer book_id, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);
    int delComment(Integer c_id);
    int deleteSelectedComment(@Param("ck") String ck);
    int approve(Integer c_id);
    int addComment(@Param("book_id") Integer book_id, @Param("u_id") Integer u_id, @Param("comment") String comment);
    int addRating(Ratings ratings);
}
