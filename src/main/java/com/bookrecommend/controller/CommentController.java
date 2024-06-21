package com.bookrecommend.controller;

import com.bookrecommend.pojo.PageInfo;
import com.bookrecommend.pojo.Comment;
import com.bookrecommend.pojo.Ratings;
import com.bookrecommend.service.BookService;
import com.bookrecommend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BookService bookService;

    @RequestMapping("/findComment")
    public String findComment(Integer c_id, String book_name, String username, String comment,Integer book_id, Integer pageIndex, Integer pageSize, Model model){
        PageInfo<Comment> pi = commentService.findPageInfo(c_id,book_name,username,comment,book_id,pageIndex,pageSize);
        model.addAttribute("pi",pi);
        return "comment_list";
    }

    @RequestMapping("/deleteComment")
    @ResponseBody
    public String delComment(Integer c_id){
        commentService.delComment(c_id);
        return "comment_list";
    }

    @RequestMapping("/deleteSelectedComment")
    @ResponseBody
    public String deleteSelectedComment(String ck){
        commentService.deleteSelectedComment(ck);
        return "comment_list";
    }

    @RequestMapping("/approve")
    @ResponseBody
    public int approve(Integer c_id){
        return commentService.approve(c_id);
    }

    @RequestMapping("/addComment")
    @ResponseBody
    public int comment(Integer book_id, Integer u_id, String comment){
        return commentService.addComment(book_id,u_id,comment);
    }


}
