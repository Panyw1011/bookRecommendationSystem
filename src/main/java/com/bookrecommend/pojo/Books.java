package com.bookrecommend.pojo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Books {
    private Integer book_id;
    private String ISBN;
    private String book_name;
    private String author;
    private Integer year_of_publication;
    private Integer pages;
    private String publishing_house;
    private String brief_introduction;
    private String author_introduction;
    private String label_name;
    private Integer number_of_people;
    private String img_url;
    private Integer book_count;
}
