package com.bookrecommend.controller;

import com.bookrecommend.pojo.Admin;
import com.bookrecommend.pojo.PageInfo;
import com.bookrecommend.service.AdminService;
import com.bookrecommend.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/adminLogin")
    public String success(Admin admin, Model model, HttpSession session){
        admin.setLogin_password(MD5Util.MD5EncodeUtf8(admin.getLogin_password()));
        Admin admin1 = adminService.adminLogin(admin);
        if (admin1 != null){
            session.setAttribute("currentAdmin",admin1);
            adminService.resetLoginTime();
            return "homepage";
        }
        model.addAttribute("msg","用户名或密码错误，请重新登录！");
        return "login";
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session){
        session.invalidate();
        return "login";
    }

    @RequestMapping("/myInfo")
    public String toMyInfo(){
        return "admin_info";
    }

    @RequestMapping("/findAdmin")
    public String findAdmin(Integer a_id, String username, String phone, Integer pageIndex, Integer pageSize, Model model){
        PageInfo<Admin> pi = adminService.findPageInfo(a_id,username,phone,pageIndex,pageSize);
        model.addAttribute("pi",pi);
        return "admin_list";
    }

    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    @ResponseBody
    public String addAdmin(@RequestBody Admin admin){
        adminService.addAdmin(admin);
        return "admin_list";
    }

    @RequestMapping("/deleteAdmin")
    @ResponseBody
    public String delAdmin(Integer a_id){
        adminService.delAdmin(a_id);
        return "admin_list";
    }

    @RequestMapping("/deleteSelectedAdmin")
    @ResponseBody
    public String deleteSelectedAdmin(String ck){
        adminService.deleteSelectedAdmin(ck);
        return "admin_list";
    }

    @RequestMapping("/findAdminById")
    public String findAdminById(Integer a_id, Model model){
        Admin modAdmin = adminService.findAdminById(a_id);
        model.addAttribute("modAdmin",modAdmin);
        return "admin_edit";
    }

    @RequestMapping(value = "/updateAdmin", method = RequestMethod.POST)
    public String updateAdmin(Admin admin){
        adminService.updateAdmin(admin);
        return "redirect:/findAdmin";
    }

    @RequestMapping("/updateAdminInfo")
    @ResponseBody
    public int updateAdminInfo(Integer a_id, String username, Integer sex, String birthday, String phone, String email) throws ParseException {
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        Date date = sdf.parse(birthday);
        Admin admin = new Admin();
        admin.setA_id(a_id);
        admin.setUsername(username);
        admin.setSex(sex);
        admin.setBirthday(date);
        admin.setPhone(phone);
        admin.setEmail(email);
        return adminService.updateAdmin(admin);
    }
}
