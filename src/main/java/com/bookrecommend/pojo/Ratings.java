package com.bookrecommend.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Ratings {
    private Long ID;
    private Long ISBN;
    private Long Rating;
}
