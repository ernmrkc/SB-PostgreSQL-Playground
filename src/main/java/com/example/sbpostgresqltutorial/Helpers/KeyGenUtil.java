package com.example.sbpostgresqltutorial.Helpers;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class KeyGenUtil {
    private static final SecretKey KEY;

    static{
        try{
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            keyGenerator.init(256);
            KEY = keyGenerator.generateKey();
        }catch (NoSuchAlgorithmException exception){
            throw new RuntimeException("HmacSHA256 algorithm not found", exception);
        }
    }

    public static SecretKey getKey(){
        return KEY;
    }
}
