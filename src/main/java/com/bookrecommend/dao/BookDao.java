package com.bookrecommend.dao;

import com.bookrecommend.pojo.Books;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface BookDao {
    Integer totalCount(@Param("book_name") String book_name, @Param("author") String author, @Param("publishing_house") String publishing_house, @Param("ISBN") String ISBN, @Param("label_name") String label_name, @Param("sort") String sort);
    List<Books> getBookList(@Param("book_name") String book_name, @Param("author") String author, @Param("publishing_house") String publishing_house, @Param("ISBN") String ISBN, @Param("label_name") String label_name, @Param("sort") String sort, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);
    int addBook(Books book);
    int delBook(Integer book_id);
    int deleteSelectedBook(@Param("ck") String ck);
    Books findBookById(Integer book_id);
    int updateBook(Books book);
    List<Books> getPopularBook();
    List<Books> getRandBook(Integer count);
    List<Books> getNewBook(Integer count);
    List<Books> findAllByIds(@Param("ids") List<Integer> ids);
    int getBookCount(Integer book_id);
    int updateBookCount(Integer book_id);
    int upBookCount(Integer book_id);
    String getBookISBN(Integer book_id);
    List<Map<String,Object>> getBookChartData();
}
