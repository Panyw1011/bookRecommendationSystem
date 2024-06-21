package com.bookrecommend.service;


import com.bookrecommend.pojo.Admin;
import com.bookrecommend.pojo.PageInfo;

public interface AdminService {
    Admin adminLogin(Admin admin);
    int resetLoginTime();
    PageInfo<Admin> findPageInfo(Integer a_id, String username, String phone, Integer pageIndex, Integer pageSize);
    int addAdmin(Admin admin);
    int delAdmin(Integer a_id);
    int deleteSelectedAdmin(String ck);
    Admin findAdminById(Integer a_id);
    int updateAdmin(Admin admin);
}
