package com.bookrecommend.service.impl;

import com.bookrecommend.dao.AdminDao;
import com.bookrecommend.pojo.Admin;
import com.bookrecommend.pojo.PageInfo;
import com.bookrecommend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin adminLogin(Admin admin) {
        Admin admin1 = adminDao.adminLogin(admin);
        return admin1;
    }

    @Override
    public int resetLoginTime() {
        return adminDao.resetLoginTime();
    }

    @Override
    public PageInfo<Admin> findPageInfo(Integer a_id, String username, String phone, Integer pageIndex, Integer pageSize) {
        PageInfo<Admin> pi = new PageInfo<Admin>();
        pi.setPageIndex(pageIndex);
        pi.setPageSize(pageSize);
        Integer totalCount = adminDao.totalCount(a_id,username,phone);
        if (totalCount>0){
            pi.setTotalCount(totalCount);
            List<Admin> adminList = adminDao.getAdminList(a_id,username,phone,(pi.getPageIndex()-1)*pi.getPageSize(),pi.getPageSize());
            pi.setList(adminList);
        }
        return pi;
    }

    @Override
    public int addAdmin(Admin admin) {
        return adminDao.addAdmin(admin);
    }

    @Override
    public int delAdmin(Integer a_id) {
        return adminDao.delAdmin(a_id);
    }

    @Override
    public int deleteSelectedAdmin(String ck) {
        return adminDao.deleteSelectedAdmin(ck);
    }

    @Override
    public Admin findAdminById(Integer a_id) {
        Admin admin = adminDao.findAdminById(a_id);
        return admin;
    }

    @Override
    public int updateAdmin(Admin admin) {
        return adminDao.updateAdmin(admin);
    }
}
