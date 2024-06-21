package com.bookrecommend.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
public class Borrow {
    private Integer b_id;
    private Integer book_id;
    private String img_url;
    private String book_name;
    private Integer u_id;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date borrow_time;
    private Integer remain_day;
    private Integer renew_times;
    private String state;
    private BigDecimal money;
}
