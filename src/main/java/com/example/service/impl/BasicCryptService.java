package com.example.service.impl;

import com.example.service.CryptService;
import org.apache.axiom.om.util.Base64;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.lang.reflect.Field;
import java.security.Key;

/**
 * Created by shichp on 2016/12/26.
 */
@Service
@Profile({"dev", "prod"})
public class BasicCryptService implements CryptService {


    private static final String KEY_STR = "145key14";
    public static final String UTF_8 = "UTF-8";
    public static final String DES = "DES";
    private static Key key;

    public BasicCryptService() {
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            DESKeySpec keyspec = new DESKeySpec(KEY_STR.getBytes(UTF_8));
            key = keyFactory.generateSecret(keyspec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String decrypt(String encrypted) {
        try {
            Cipher cipher = Cipher.getInstance(DES);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptStrBytes = cipher.doFinal(Base64.decode(encrypted));
            return new String(decryptStrBytes, UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void decryptAll(Object encryptedObj) {
        Class clz = encryptedObj.getClass();
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            try {
                if (field.getGenericType().toString().equals("class java.lang.String")) {
                    field.setAccessible(true);
                    String encrypted = field.get(encryptedObj).toString();
                    field.set(encryptedObj, decrypt(encrypted));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
