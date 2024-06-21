package com.bookrecommend.dao;

import com.bookrecommend.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserDao {
    User userLogin(User user);
    Integer totalCount(@Param("u_id") Integer u_id, @Param("username") String username, @Param("phone") String phone);
    List<User> getUserList(@Param("u_id") Integer u_id, @Param("username") String username, @Param("phone") String phone, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);
    int addUser(User user);
    int delUser(Integer u_id);
    int deleteSelectedUser(@Param("ck") String ck);
    User findUserById(Integer u_id);
    int updateUser(User user);
    int updatePassword(@Param("login_password") String login_password, @Param("u_id") Integer u_id);
    List<Map<Integer,Integer>> getUserChartData();
}
