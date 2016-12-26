package com.example.service;

/**
 * Created by nick on 16/4/14.
 */
public interface CryptService {

    String decrypt(String encrypted);
    void decryptAll(Object object);
}
