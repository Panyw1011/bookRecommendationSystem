package com.bookrecommend.service;

import com.bookrecommend.pojo.PageInfo;
import com.bookrecommend.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User userLogin(User user);
    PageInfo<User> findPageInfo(Integer u_id, String username, String phone, Integer pageIndex, Integer pageSize);
    int addUser(User user);
    int delUser(Integer u_id);
    int deleteSelectedUser(String ck);
    User findUserById(Integer u_id);
    int updateUser(User user);
    int updatePassword(String login_password, Integer u_id);
    List<Map<Integer,Integer>> getUserChartData();
}
