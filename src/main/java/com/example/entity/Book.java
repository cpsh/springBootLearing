package com.example.entity;

import lombok.Data;
import org.springframework.context.annotation.Description;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by shichp on 2016/12/23.
 */
@Data
@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String reader;
    //国际标准书号（International Standard Book Number），简称ISBN，是专门为识别图书等文献而设计的国际编号
    private String isbn;
    private String title;
    private String author;
    private String description;
}
