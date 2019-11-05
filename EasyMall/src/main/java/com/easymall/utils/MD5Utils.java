package com.easymall.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    public static String md5(String text) {
        byte[] secret = null;
        try {
            secret = MessageDigest.getInstance("md5").digest(text.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        String md5 = (new BigInteger(1, secret)).toString(16);
        StringBuilder sb = new StringBuilder(md5);
        for (int i = 0; i < 32 - sb.length(); i++) {
            sb.insert(0, "0");
        }
        return sb.toString();
    }
}
