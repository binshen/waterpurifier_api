package com.waterpurifier.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by bin.shen on 14/12/2016.
 */

public class Sms {

    public static String request(String httpUrl, String httpArg) throws Exception {
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        URL url = new URL(httpUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        InputStream is = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String strRead = reader.readLine();
        if (strRead != null) {
            sbf.append(strRead);
            while ((strRead = reader.readLine()) != null) {
                sbf.append("\n");
                sbf.append(strRead);
            }
        }
        reader.close();
        return sbf.toString();
    }

    public static String md5(String plainText) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(plainText.getBytes());
        byte b[] = md.digest();
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }

    public static String encodeUrlString(String str, String charset) throws Exception {
        if (str == null)
            return str;
        return java.net.URLEncoder.encode(str, charset);
    }
}
