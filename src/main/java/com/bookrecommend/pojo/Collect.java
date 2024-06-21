package com.bookrecommend.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Collect {
    private Integer co_id;
    private Integer book_id;
    private String book_name;
    private String author;
    private String img_url;
}
