package com.bookrecommend.controller;

import com.bookrecommend.pojo.Collect;
import com.bookrecommend.pojo.PageInfo;
import com.bookrecommend.pojo.User;
import com.bookrecommend.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CollectController {
    @Autowired
    private CollectService collectionService;

    @RequestMapping("/collect")
    @ResponseBody
    public int collect(Integer u_id, Integer book_id){
        int i = collectionService.collect(u_id,book_id);
        return i;
    }

    @RequestMapping("/user_collect")
    public String toUserCollect(Integer pageIndex, Integer pageSize, Model model){
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        if (user != null){
            pageSize = 16;
            PageInfo<Collect> pi = collectionService.findPageInfo(user.getU_id(), pageIndex, pageSize);
            model.addAttribute("collect",pi);
            return "collect";
        }else {
            model.addAttribute("msg","您还没有登录，请先登录");
            return "user_login";
        }
    }

    @RequestMapping("/delCollect")
    @ResponseBody
    public String delCollect(Integer co_id){
        collectionService.delCollect(co_id);
        return "collect";
    }
}
