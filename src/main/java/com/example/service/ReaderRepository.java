package com.example.service;

import com.example.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by shichp on 2016/12/23.
 */
public interface ReaderRepository extends JpaRepository<Reader, String> {
    Reader findOne(String reader);
}
