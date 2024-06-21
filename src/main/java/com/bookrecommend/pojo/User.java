package com.bookrecommend.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@ToString
public class User {
    private Integer u_id;
    private String login_name;
    private String login_password;
    private String username;
    private Integer u_sex;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;
    private String phone;
}
