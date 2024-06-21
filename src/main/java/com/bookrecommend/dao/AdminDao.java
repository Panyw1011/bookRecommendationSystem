package com.bookrecommend.dao;

import com.bookrecommend.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminDao {
    Admin adminLogin(Admin admin);
    int resetLoginTime();
    Integer totalCount(@Param("a_id") Integer a_id, @Param("username") String username, @Param("phone") String phone);
    List<Admin> getAdminList(@Param("a_id") Integer a_id, @Param("username") String username, @Param("phone") String phone, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);
    int addAdmin(Admin admin);
    int delAdmin(Integer a_id);
    int deleteSelectedAdmin(@Param("ck") String ck);
    Admin findAdminById(Integer a_id);
    int updateAdmin(Admin admin);
}
