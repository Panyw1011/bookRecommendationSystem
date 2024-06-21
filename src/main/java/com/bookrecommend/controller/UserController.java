package com.bookrecommend.controller;

import com.bookrecommend.pojo.PageInfo;
import com.bookrecommend.pojo.User;
import com.bookrecommend.service.UserService;
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
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findUser")
    public String findUser(Integer u_id, String username, String phone, Integer pageIndex, Integer pageSize, Model model){
        PageInfo<User> pi = userService.findPageInfo(u_id,username,phone,pageIndex,pageSize);
        model.addAttribute("pi",pi);
        return "user_list";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public String addUser(@RequestBody User user){
        userService.addUser(user);
        return "user_list";
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public String delUser(Integer u_id){
        userService.delUser(u_id);
        return "user_list";
    }

    @RequestMapping("/deleteSelectedUser")
    @ResponseBody
    public String deleteSelectedUser(String ck){
        userService.deleteSelectedUser(ck);
        return "user_list";
    }

    @RequestMapping("/findUserById")
    public String findUserById(Integer u_id, Model model){
        User modUser = userService.findUserById(u_id);
        model.addAttribute("modUser",modUser);
        return "user_edit";
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUser(User user){
        userService.updateUser(user);
        return "redirect:/findUser";
    }

    @RequestMapping("/user_login")
    public String toUserLogin(){
        return "user_login";
    }

    @RequestMapping("/userLogin")
    public String userLogin(User user, Model model, HttpSession session){
        user.setLogin_password(MD5Util.MD5EncodeUtf8(user.getLogin_password()));
        User user1 = userService.userLogin(user);
        if (user1 != null){
            session.setAttribute("currentUser",user1);
            return "redirect:/index";
        }
        model.addAttribute("msg","用户名或密码错误，请重新登录！");
        return "user_login";
    }

    @RequestMapping("/userLoginOut")
    public String loginOut(HttpSession session){
        session.invalidate();
        return "redirect:/index";
    }

    @RequestMapping("/userInfo")
    public String userInfo(){
        return "user_info";
    }

    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public int updateUserInfo(Integer u_id, String username, Integer u_sex, String birthday, String phone) throws ParseException {
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        Date date = sdf.parse(birthday);
        User user = new User();
        user.setU_id(u_id);
        user.setUsername(username);
        user.setU_sex(u_sex);
        user.setBirthday(date);
        user.setPhone(phone);
        return userService.updateUser(user);
    }

    @RequestMapping("/updatePassword")
    public String update(){
        return "updatePassword";
    }

    @RequestMapping("/updateUserPassword")
    @ResponseBody
    public int updateUserPassword(String password, Integer u_id){
        String login_password = MD5Util.MD5EncodeUtf8(password);
        int i = userService.updatePassword(login_password,u_id);
        return i;
    }

}
