package com.bookrecommend.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@ToString
public class Comment {
    private Integer c_id;
    private String book_name;
    private String username;
    private Integer score;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date time;
    private String comment;
    private Integer approval;
}
