package com.bookrecommend.service;

import com.bookrecommend.pojo.Label;
import com.bookrecommend.pojo.PageInfo;

import java.util.List;

public interface LabelService {
    PageInfo<Label> findPageInfo(Integer l_id, String label_name, Integer pageIndex, Integer pageSize);
    int addLabel(Label label);
    int delLabel(Integer l_id);
    int deleteSelectedLabel(String ck);
    Label findLabelById(Integer l_id);
    int updateLabel(Label label);
    List<Label> getHotList();
}
