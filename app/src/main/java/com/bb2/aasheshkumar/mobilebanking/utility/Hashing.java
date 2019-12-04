package com.bb2.aasheshkumar.mobilebanking.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {

    public static String hash(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        byte[] digest = messageDigest.digest(str.getBytes());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            stringBuilder.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuilder.toString();
    }

    public static boolean match(String hashed, String str) throws NoSuchAlgorithmException{
        return hashed.equals(hash(str));
    }

}
