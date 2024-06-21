package com.bookrecommend.service.impl;


import com.bookrecommend.dao.UserDao;
import com.bookrecommend.pojo.PageInfo;
import com.bookrecommend.pojo.User;
import com.bookrecommend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public User userLogin(User user) {
        User user1 = userDao.userLogin(user);
        return user1;
    }

    @Override
    public PageInfo<User> findPageInfo(Integer u_id, String username, String phone, Integer pageIndex, Integer pageSize) {
        PageInfo<User> pi = new PageInfo<User>();
        pi.setPageIndex(pageIndex);
        pi.setPageSize(pageSize);
        Integer totalCount = userDao.totalCount(u_id,username,phone);
        if (totalCount>0){
            pi.setTotalCount(totalCount);
            List<User> userList = userDao.getUserList(u_id,username,phone,(pi.getPageIndex()-1)*pi.getPageSize(),pi.getPageSize());
            pi.setList(userList);
        }
        return pi;
    }

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public int delUser(Integer u_id) {
        return userDao.delUser(u_id);
    }

    @Override
    public int deleteSelectedUser(String ck) {
        return userDao.deleteSelectedUser(ck);
    }

    @Override
    public User findUserById(Integer u_id) {
        User user = userDao.findUserById(u_id);
        return user;
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int updatePassword(String login_password, Integer u_id) {
        return userDao.updatePassword(login_password,u_id);
    }

    @Override
    public List<Map<Integer, Integer>> getUserChartData() {
        List<Map<Integer,Integer>> list = userDao.getUserChartData();
        return list;
    }
}
