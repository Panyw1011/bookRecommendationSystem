package com.bookrecommend.dao;

import com.bookrecommend.pojo.Label;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LabelDao {
    Integer totalCount(@Param("l_id") Integer l_id, @Param("label_name") String label_name);
    List<Label> getLabelList(@Param("l_id") Integer l_id, @Param("label_name") String label_name, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);
    int addLabel(Label label);
    int delLabel(Integer l_id);
    int deleteSelectedLabel(@Param("ck") String ck);
    Label findLabelById(Integer l_id);
    int updateLabel(Label label);
    List<Label> getHotLabel();
}
