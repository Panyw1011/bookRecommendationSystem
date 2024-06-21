package com.bookrecommend.dao;


import com.bookrecommend.pojo.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CollectDao {
    int collect(@Param("u_id") Integer u_id, @Param("book_id") Integer book_id);
    Integer totalCount(@Param("u_id") Integer u_id);
    List<Collect> getCollectById(@Param("u_id") Integer u_id, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);
    int delCollect(Integer co_id);
}
