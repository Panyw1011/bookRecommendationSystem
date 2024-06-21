package com.bookrecommend.controller;

import com.bookrecommend.pojo.*;
import com.bookrecommend.service.BookService;
import com.bookrecommend.service.CommentService;
import com.bookrecommend.service.LabelService;
import com.bookrecommend.service.UserService;
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @RequestMapping("/findBook")
    public String findBook(String book_name, String author, String publishing_house, String ISBN, String label_name, String sort, Integer pageIndex, Integer pageSize, Model model){
        PageInfo<Books> pi = bookService.findPageInfo(book_name,author,publishing_house,ISBN,label_name,sort,pageIndex,pageSize);
        model.addAttribute("pi",pi);
        return "book_list";
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    @ResponseBody
    public String addBook(@RequestBody Books book){
        System.out.println(book);
        bookService.addBook(book);
        return "book_list";
    }

    @RequestMapping("/deleteBook")
    @ResponseBody
    public String delBook(Integer book_id){
        bookService.delBook(book_id);
        return "book_list";
    }

    @RequestMapping("/deleteSelectedBook")
    @ResponseBody
    public String deleteSelectedBook(String ck){
        bookService.deleteSelectedBook(ck);
        return "book_list";
    }

    @RequestMapping("/findBookById")
    public String findBookById(Integer book_id, Model model){
        Books modBook = bookService.findBookById(book_id);
        model.addAttribute("modBook",modBook);
        return "book_edit";
    }

    @RequestMapping(value = "/updateBook", method = RequestMethod.POST)
    public String updateBook(Books book){
        bookService.updateBook(book);
        return "redirect:/findBook";
    }

    @RequestMapping("/index")
    public String toIndex(Model model){
        List<Books> bookList = bookService.getPopularBook();
        model.addAttribute("popularBook",bookList);
        Integer count = 4;
        List<Books> bookList3 = bookService.getRandBook(count);
        model.addAttribute("randBook",bookList3);
        List<Books> bookList2 = bookService.getNewBook(count);
        model.addAttribute("newBook",bookList2);
        List<Label> labelList = labelService.getHotList();
        model.addAttribute("hotLabel",labelList);
        return "index";
    }

    @RequestMapping("/allBook")
    public String toAllBook(String book_name, String author, String publishing_house, String ISBN, String label_name, String sort, Integer pageIndex, Integer pageSize, Model model){
        pageSize = 20;
        PageInfo<Books> pi = bookService.findPageInfo(book_name,author,publishing_house,ISBN,label_name,sort,pageIndex,pageSize);
        model.addAttribute("pi",pi);
        return "all_book";
    }

    @RequestMapping("/detail")
    public String bookDetail(Integer c_id, Integer book_id, String book_name, String username, String comment,String ISBN, Integer pageIndex, Integer pageSize, Model model){
        Books book = bookService.findBookById(book_id);
        model.addAttribute("book",book);
        PageInfo<Comment> pi = commentService.findPageInfo(c_id,book_name,username,comment,book_id,pageIndex,200);
        model.addAttribute("comment",pi);
        List<Books> bookList = bookService.getPopularBook();
        model.addAttribute("popularBook",bookList);
        return "detail";
    }

    @RequestMapping("/newBook")
    public String toNewBook(Model model){
        Integer count = 8;
        List<Books> newBook = bookService.getNewBook(count);
        model.addAttribute("pi",newBook);
        return "new_book";
    }

    @RequestMapping("/recommend")
    public String recommend(Model model) throws ClassNotFoundException {
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        if (user != null){
            List<Books> bookList = bookService.getRecommendItemsByUser((long)user.getU_id(),8);
            model.addAttribute("pi",bookList);
            if (bookList.size() < 8){
                int count = 8 - bookList.size();
                List<Books> booksList2 = bookService.getRandBook(count);
                model.addAttribute("pi2",booksList2);
            }
        }else {
            int count = 8;
            List<Books> booksList2 = bookService.getRandBook(count);
            model.addAttribute("pi2",booksList2);
        }
        return "recommend";
    }

    @RequestMapping("/getChartData")
    @ResponseBody
    public Map<String,Object> getChartData(){
        //设置热门图书数据
        List<Map<String,Object>> bookList = bookService.getBookChartData();
        List<Object> bookName = new ArrayList<Object>();
        List<Object> people = new ArrayList<Object>();
        for (int i = 0; i < bookList.size(); i++) {
            bookName.add((bookList.get(i)).get("book_name"));
            people.add((bookList.get(i)).get("number_of_people"));
        }

        //封装图书数据
        Map<String,Object> bookData = new LinkedHashMap<String,Object>();
        bookData.put("book_name",bookName);
        bookData.put("number_of_people",people);

        //设置性别数据
        List<Map<Integer,Integer>> sexList = userService.getUserChartData();

        List<Map<String,Object>> al = new ArrayList<Map<String,Object>>();

        Map<String,Object> am2 = new LinkedHashMap<String, Object>();
        am2.put("name","女");
        am2.put("value",(sexList.get(0)).get("count"));
        al.add(am2);
        Map<String,Object> am3 = new LinkedHashMap<String, Object>();
        am3.put("name","男");
        am3.put("value",(sexList.get(1)).get("count"));
        al.add(am3);


        //封装总数据
        Map<String,Object> data = new LinkedHashMap<String,Object>();
        data.put("book",bookData);
        data.put("sex",al);

        //封装结果
        Map<String,Object> resMap = new LinkedHashMap<String,Object>();
        resMap.put("code",0);
        resMap.put("data",data);

        return resMap;
    }

}
