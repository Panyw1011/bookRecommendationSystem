package com.bookrecommend.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Label {
    private Integer l_id;
    private String label_name;
    private Integer book_count;
}
