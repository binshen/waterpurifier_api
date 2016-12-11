package com.waterpurifier.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

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
}
