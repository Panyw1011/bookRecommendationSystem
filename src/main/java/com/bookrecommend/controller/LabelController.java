package com.bookrecommend.controller;

import com.bookrecommend.pojo.Label;
import com.bookrecommend.pojo.PageInfo;
import com.bookrecommend.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LabelController {
    @Autowired
    private LabelService labelService;

    @RequestMapping("/findLabel")
    public String findLabel(Integer l_id, String label_name, Integer pageIndex, Integer pageSize, Model model){
        PageInfo<Label> pi = labelService.findPageInfo(l_id,label_name,pageIndex,pageSize);
        model.addAttribute("pi",pi);
        return "label_list";
    }

    @RequestMapping(value = "/addLabel", method = RequestMethod.POST)
    @ResponseBody
    public String addLabel(@RequestBody Label label){
        labelService.addLabel(label);
        return "label_list";
    }

    @RequestMapping("/deleteLabel")
    @ResponseBody
    public String delLabel(Integer l_id){
        labelService.delLabel(l_id);
        return "label_list";
    }

    @RequestMapping("/deleteSelectedLabel")
    @ResponseBody
    public String deleteSelectedLabel(String ck){
        labelService.deleteSelectedLabel(ck);
        return "label_list";
    }

    @RequestMapping("/findLabelById")
    public String findLabelById(Integer l_id, Model model){
        Label modLabel = labelService.findLabelById(l_id);
        model.addAttribute("modLabel",modLabel);
        return "label_edit";
    }

    @RequestMapping(value = "/updateLabel", method = RequestMethod.POST)
    public String updateLabel(Label label){
        labelService.updateLabel(label);
        return "redirect:/findLabel";
    }

    @RequestMapping("/all_label")
    public String findAllLabel(Integer pageIndex, Integer pageSize, Model model){
        Integer l_id = null;
        String label_name = null;
        pageSize = 100;
        PageInfo<Label> pi = labelService.findPageInfo(l_id,label_name,pageIndex,pageSize);
        model.addAttribute("pi",pi);
        return "all_label";
    }
}
