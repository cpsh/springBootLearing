package com.example.service;

import com.example.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by shichp on 2016/12/23.
 */
public interface ReadingListRepository  extends JpaRepository<Book,Long>{
    List<Book> findByReader(String reader);
}
