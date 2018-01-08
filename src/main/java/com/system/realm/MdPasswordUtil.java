
package com.system.realm;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

public class MdPasswordUtil {

    public static String encodePassword(String userId, String clientPassword) {

        String mdstr = "";
        String strEncode = userId + clientPassword;
        byte[] btKey = new byte[strEncode.getBytes().length + 1];
        System.arraycopy(strEncode.getBytes(), 0, btKey, 0, strEncode.getBytes().length);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(btKey);
            byte[] btDigest = md.digest();
            BASE64Encoder encoder = new BASE64Encoder();
            mdstr = encoder.encode(btDigest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mdstr;
    }
}