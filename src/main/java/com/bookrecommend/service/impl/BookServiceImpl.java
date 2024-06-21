package com.bookrecommend.service.impl;


import com.bookrecommend.dao.BookDao;
import com.bookrecommend.pojo.PageInfo;
import com.bookrecommend.pojo.Books;
import com.bookrecommend.service.BookService;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLBooleanPrefJDBCDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BookServiceImpl implements BookService{
    @Autowired
    private BookDao bookDao;

    @Autowired
    DataSource dataSource;

    @Value("${com.bookrecommend.mahout.table-name}")
    private String tableName;

    @Value("${com.bookrecommend.mahout.user-column}")
    private String userColumn;

    @Value("${com.bookrecommend.mahout.item-column}")
    private String itemColumn;

    @Value("${com.bookrecommend.mahout.pref-column}")
    private String prefColumn;

    @Value("${com.bookrecommend.mahout.recommendNum}")
    private int recommendNum;

    private DataModel dataModel = null;
    private Recommender recommender = null;
    private Recommender recommender2 = null;

    @PostConstruct
    public void init() {
        try {
            this.dataModel = new MySQLJDBCDataModel(
                    dataSource,
                    this.tableName,
                    this.userColumn,
                    this.itemColumn,
                    this.prefColumn,
                    null
            );
            Resource resource = new ClassPathResource("static/rating/ratings.txt");
            String path = resource.getFile().getPath();
            DataModel model = new FileDataModel(new File(path));
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);    //基于皮尔逊相关系数计算相似度
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(3,similarity,model);   //对每个用户取固定数量N个最近邻居
            this.recommender = new GenericUserBasedRecommender(dataModel, neighborhood, similarity);    //基于用户的推荐引擎

//            UserSimilarity similarity = new LogLikelihoodSimilarity(dataModel);
//            UserNeighborhood neighborhood = new NearestNUserNeighborhood(10, similarity, dataModel);
//            this.recommender = new GenericBooleanPrefUserBasedRecommender(dataModel, neighborhood, similarity);

//            UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
//            UserNeighborhood neighborhood = new NearestNUserNeighborhood(3, similarity, dataModel);
//            this.recommender = new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public PageInfo<Books> findPageInfo(String book_name, String author, String publishing_house, String ISBN, String label_name, String sort, Integer pageIndex, Integer pageSize) {
        PageInfo<Books> pi = new PageInfo<Books>();
        pi.setPageIndex(pageIndex);
        pi.setPageSize(pageSize);
        Integer totalCount = bookDao.totalCount(book_name,author,publishing_house,ISBN,label_name,sort);
        if (totalCount>0){
            pi.setTotalCount(totalCount);
            List<Books> bookList = bookDao.getBookList(book_name,author,publishing_house,ISBN,label_name,sort,(pi.getPageIndex()-1)*pi.getPageSize(),pi.getPageSize());
            pi.setList(bookList);
        }
        return pi;
    }

    @Override
    public int addBook(Books book) {
        return bookDao.addBook(book);
    }

    @Override
    public int delBook(Integer book_id) {
        return bookDao.delBook(book_id);
    }

    @Override
    public int deleteSelectedBook(String ck) {
        return bookDao.deleteSelectedBook(ck);
    }

    @Override
    public Books findBookById(Integer book_id) {
        Books book = bookDao.findBookById(book_id);
        return book;
    }

    @Override
    public int updateBook(Books book) {
        return bookDao.updateBook(book);
    }

    @Override
    public List<Books> getPopularBook() {
        List<Books> bookList = bookDao.getPopularBook();
        return bookList;
    }

    @Override
    public List<Books> getRandBook(Integer count) {
        List<Books> books = bookDao.getRandBook(count);
        return books;
    }

    @Override
    public List<Books> getNewBook(Integer count) {
        List<Books> bookList = bookDao.getNewBook(count);
        return bookList;
    }

    @Override
    public List<Books> getRecommendItemsByUser(Long u_id, int count) {
        List<Books> bookList = null;
        try{
            List<RecommendedItem> recommendedItemList = recommender.recommend(u_id,count);//得到推荐的结果，count是推荐结果的数目
            List<Integer> itemIds = new ArrayList<Integer>();
            for (RecommendedItem recommendedItem : recommendedItemList) {
                System.out.println(recommendedItem);
                itemIds.add((int) recommendedItem.getItemID());
            }
            System.out.println("User-Based推荐出来的图书ISBN集合"+itemIds);

            //根据商品id查询商品
            if(itemIds!=null &&itemIds.size()>0) {
                bookList = bookDao.findAllByIds(itemIds);
            }else{
                bookList = new ArrayList<>();
            }

            //测试
//            //从数据模型中获取所有用户ID迭代器
//            LongPrimitiveIterator usersIterator = dataModel.getUserIDs();
//            //通过迭代器遍历所有用户ID
//            while (usersIterator.hasNext()) {
//                System.out.println("================================================");
//                //用户ID
//                long userID = usersIterator.nextLong();
//                //用户ID
//
//                List<RecommendedItem> recommendedItems = recommender.recommend(userID, 8);
//                if (recommendedItems.size() > 0) {
//                    System.out.println(userID);
//                    for (RecommendedItem item : recommendedItems) {
//                        System.out.println("推荐的物品"+ item.getItemID()+"预测评分是 "+ item.getValue());
//                    }
//                } else {
//                    System.out.println("无任何物品推荐");
//                }
//            }

        }catch (TasteException e) {
            e.printStackTrace();
        }

        return bookList;
    }

    @Override
    public List<Books> getRecommendItemsByItem(Long u_id, int count) throws ClassNotFoundException {
        List<Books> bookList = null;
        try{
            List<RecommendedItem> recommendedItemList = recommender2.recommend(u_id,count);
            List<Integer> itemIds = new ArrayList<Integer>();
            for (RecommendedItem recommendedItem : recommendedItemList) {
                System.out.println(recommendedItem);
                itemIds.add((int) recommendedItem.getItemID());
            }
            System.out.println("Item-Based推荐出来的图书ISBN集合"+itemIds);

            //根据商品id查询商品
            if(itemIds!=null &&itemIds.size()>0) {
                bookList = bookDao.findAllByIds(itemIds);
            }else{
                bookList = new ArrayList<>();
            }
        }catch (TasteException e) {
            e.printStackTrace();
        }

        return bookList;
    }

    @Override
    public int getBookCount(Integer book_id) {
        return bookDao.getBookCount(book_id);
    }

    @Override
    public int updateBookCount(Integer book_id) {
        return bookDao.updateBookCount(book_id);
    }

    @Override
    public List<Books> findAllByIds(List<Integer> ids) {
        List<Books> bookList = bookDao.findAllByIds(ids);
        return bookList;
    }

    @Override
    public String getBookISBN(Integer book_id) {
        return bookDao.getBookISBN(book_id);
    }

    @Override
    public List<Map<String, Object>> getBookChartData() {
        List<Map<String, Object>> list = bookDao.getBookChartData();
        return list;
    }
}
