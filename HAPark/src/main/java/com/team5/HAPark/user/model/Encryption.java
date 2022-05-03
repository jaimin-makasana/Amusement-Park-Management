package com.team5.HAPark.user.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {

    //I referenced this source to create this password encryption method
    //https://www.baeldung.com/sha-256-hashing-java
    public static String encryptPassword(String password) throws NoSuchAlgorithmException {

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder encryptedPassword = new StringBuilder();

        for (byte b:bytes){
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1){
                encryptedPassword.append('0').append(hex);
            } else {
                encryptedPassword.append(hex);
            }
        }
        return encryptedPassword.toString();
    }
}
