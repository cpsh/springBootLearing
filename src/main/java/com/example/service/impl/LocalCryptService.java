package com.example.service.impl;

import com.example.service.CryptService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by shichp on 2016/12/26.
 */
@Service
@Profile({"local"})
public class LocalCryptService implements CryptService{

    @Override
    public String decrypt(String encrypted) {
        return null;
    }

    @Override
    public void decryptAll(Object object) {

    }
}
