package com.bookrecommend.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@ToString
public class Admin {
    private Integer a_id;
    private String login_name;
    private String login_password;
    private String username;
    private Integer sex;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;
    private String phone;
    private String email;
    private Integer r_id;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date cre_time;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date login_time;
}
