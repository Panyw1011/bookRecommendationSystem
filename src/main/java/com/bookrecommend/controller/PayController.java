package com.bookrecommend.controller;

import com.bookrecommend.pojo.Pay;
import com.bookrecommend.pojo.PageInfo;
import com.bookrecommend.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PayController {
    @Autowired
    private PayService payService;

    @RequestMapping("/findPay")
    public String findPay(String reason, String is_pay, Integer pageIndex, Integer pageSize, Model model){
        PageInfo<Pay> pi = payService.findPageInfo(reason,is_pay,pageIndex,pageSize);
        model.addAttribute("pi",pi);
        return "pay_list";
    }

    @RequestMapping(value = "/addPay", method = RequestMethod.POST)
    @ResponseBody
    public String addPay(@RequestBody Pay pay){
        payService.addPay(pay);
        return "pay_list";
    }

    @RequestMapping("/findPayById")
    public String findPayById(Integer b_id, Model model){
        Pay modPay = payService.findPayById(b_id);
        model.addAttribute("modPay",modPay);
        return "pay_edit";
    }

    @RequestMapping(value = "/updatePay", method = RequestMethod.POST)
    public String updatePay(Pay pay){
        payService.updatePay(pay);
        return "redirect:/findPay";
    }
}
