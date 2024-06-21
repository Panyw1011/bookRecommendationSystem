package com.bookrecommend.controller;

import com.bookrecommend.pojo.*;
import com.bookrecommend.service.BookService;
import com.bookrecommend.service.BorrowService;
import com.bookrecommend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BorrowController {
    @Autowired
    private BorrowService borrowService;
    @Autowired
    private BookService bookService;
    @Autowired
    private CommentService commentService;

    @RequestMapping("/findBorrow")
    public String findBorrow(Integer book_id, String username, String state, Integer pageIndex, Integer pageSize, Model model){
        PageInfo<Borrow> pi = borrowService.findPageInfo(book_id,username,state,pageIndex,pageSize);
        model.addAttribute("pi",pi);
        return "borrow_list";
    }

    @RequestMapping(value = "/addBorrow", method = RequestMethod.POST)
    @ResponseBody
    public String addBorrow(@RequestBody Borrow borrow){
        borrowService.addBorrow(borrow);
        return "borrow_list";
    }

    @RequestMapping("/findBorrowById")
    public String findBorrowById(Integer b_id, Model model){
        Borrow modBorrow = borrowService.findBorrowById(b_id);
        model.addAttribute("modBorrow",modBorrow);
        return "borrow_edit";
    }

    @RequestMapping(value = "/updateBorrow", method = RequestMethod.POST)
    public String updateBorrow(Borrow borrow){
        borrowService.updateBorrow(borrow);
        return "redirect:/findBorrow";
    }

    @RequestMapping("/borrow")
    @ResponseBody
    public int borrow(Integer book_id, Integer u_id){
        int i = bookService.getBookCount(book_id);
        if (i > 0){
            int j = borrowService.isBorrow(book_id,u_id);
            if (j > 0){
                return 2;
            }else {
                borrowService.borrow(book_id,u_id);
                bookService.updateBookCount(book_id);
                return 1;
            }
        }
        return 0;
    }

    @RequestMapping("/user_borrow")
    public String toUserBorrow(Model model){
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        if (user != null){
            List<Borrow> borrowList = borrowService.findBorrowByUerId(user.getU_id());
            model.addAttribute("borrowList",borrowList);
            Integer count = 4;
            List<Books> bookList3 = bookService.getRandBook(count);
            model.addAttribute("randBook",bookList3);
            return "user_borrow";
        }else {
            model.addAttribute("msg","您还没有登录，请先登录");
            return "user_login";
        }
    }

    @RequestMapping("/renew")
    @ResponseBody
    public String renew(Integer b_id){
        borrowService.renew(b_id);
        return "user_borrow";
    }

    @RequestMapping("/retu")
    @ResponseBody
    public String retu(Integer book_id ,Integer Rating ,Integer b_id){
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        String ISBN = bookService.getBookISBN(book_id);
        Long ID = user.getU_id().longValue();
        Long Rating2 = Rating.longValue();
        Long ISBN2 = Long.valueOf(ISBN);
        Ratings rating = new Ratings();
        rating.setID(ID);
        rating.setISBN(ISBN2);
        rating.setRating(Rating2);
        commentService.addRating(rating);
        borrowService.retu(b_id);
        return "user_borrow";
    }

    @RequestMapping("/return_book")
    public String return_book(Integer b_id, Model model){
        Borrow borrow = borrowService.findBorrowByBId(b_id);
        model.addAttribute("re_borrow",borrow);
        return "return_book";
    }
}
