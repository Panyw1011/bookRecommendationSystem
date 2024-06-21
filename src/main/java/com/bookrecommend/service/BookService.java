package com.bookrecommend.service;

import com.bookrecommend.pojo.PageInfo;
import com.bookrecommend.pojo.Books;

import java.util.List;
import java.util.Map;

public interface BookService {
    PageInfo<Books> findPageInfo(String book_name, String author, String publishing_house, String ISBN, String label_name, String sort, Integer pageIndex, Integer pageSize);
    int addBook(Books book);
    int delBook(Integer book_id);
    int deleteSelectedBook(String ck);
    Books findBookById(Integer book_id);
    int updateBook(Books book);
    List<Books> getPopularBook();
    List<Books> getRandBook(Integer count);
    List<Books> getNewBook(Integer count);
    List<Books> getRecommendItemsByUser(Long u_id, int count) throws ClassNotFoundException;
    List<Books> getRecommendItemsByItem(Long u_id, int count) throws ClassNotFoundException;
    int getBookCount(Integer book_id);
    int updateBookCount(Integer book_id);
    List<Books> findAllByIds(List<Integer> ids);
    String getBookISBN(Integer book_id);
    List<Map<String,Object>> getBookChartData();
}
