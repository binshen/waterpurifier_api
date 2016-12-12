package com.waterpurifier.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;

/**
 * Created by bin.shen on 11/12/2016.
 */

public class Common {

    public static String getMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getRandom() {
        int randomInt = (int)((Math.random() * 9 + 1) * 100000);
        return String.valueOf(randomInt);
    }

    public static void sendMessage(String tel, String code) {

    }
}
