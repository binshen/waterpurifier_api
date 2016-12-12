package com.waterpurifier.utils;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

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

    public static boolean sendMessage(String tel, String code) {
        try {
            TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "appKey", "appSecret");
            AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
            req.setExtend("");
            req.setSmsType("normal");
            req.setSmsFreeSignName("七星博士");
            req.setSmsParamString("{\"code\":\"" + code + "\"}");
            req.setRecNum(tel);
            req.setSmsTemplateCode("SMS_25781236");
            AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
            System.out.println(rsp.getBody());
            return true;
        } catch (ApiException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
