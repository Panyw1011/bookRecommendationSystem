package com.bookrecommend.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class Pay {
    private Integer b_id;
    private Integer reason;
    private Integer arrears_day;
    private BigDecimal money;
    private Integer is_pay;
    private String comment;
}
