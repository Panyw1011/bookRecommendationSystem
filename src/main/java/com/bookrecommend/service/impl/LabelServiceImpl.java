package com.bookrecommend.service.impl;


import com.bookrecommend.dao.LabelDao;
import com.bookrecommend.pojo.Label;
import com.bookrecommend.pojo.PageInfo;
import com.bookrecommend.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LabelServiceImpl implements LabelService {
    @Autowired
    private LabelDao labelDao;

    @Override
    public PageInfo<Label> findPageInfo(Integer l_id, String label_name, Integer pageIndex, Integer pageSize) {
        PageInfo<Label> pi = new PageInfo<Label>();
        pi.setPageIndex(pageIndex);
        pi.setPageSize(pageSize);
        Integer totalCount = labelDao.totalCount(l_id,label_name);
        if (totalCount>0){
            pi.setTotalCount(totalCount);
            List<Label> labelList = labelDao.getLabelList(l_id,label_name,(pi.getPageIndex()-1)*pi.getPageSize(),pi.getPageSize());
            pi.setList(labelList);
        }
        return pi;
    }

    @Override
    public int addLabel(Label label) {
        return labelDao.addLabel(label);
    }

    @Override
    public int delLabel(Integer l_id) {
        return labelDao.delLabel(l_id);
    }

    @Override
    public int deleteSelectedLabel(String ck) {
        return labelDao.deleteSelectedLabel(ck);
    }

    @Override
    public Label findLabelById(Integer l_id) {
        Label label = labelDao.findLabelById(l_id);
        return label;
    }

    @Override
    public int updateLabel(Label label) {
        return labelDao.updateLabel(label);
    }

    @Override
    public List<Label> getHotList() {
        List<Label> labelList = labelDao.getHotLabel();
        return labelList;
    }
}
