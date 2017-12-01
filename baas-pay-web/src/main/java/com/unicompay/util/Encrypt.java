package com.unicompay.util;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class Encrypt {
    private static final String charSet = "UTF-8";

    private static byte[] desCrypto(byte[] datasource, String password) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes("UTF-8"));

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);

            Cipher cipher = Cipher.getInstance("DES");

            cipher.init(1, securekey, random);

            return cipher.doFinal(datasource);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] decrypt(byte[] src, String password) throws Exception {
        SecureRandom random = new SecureRandom();

        DESKeySpec desKey = new DESKeySpec(password.getBytes("UTF-8"));

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");

        SecretKey securekey = keyFactory.generateSecret(desKey);

        Cipher cipher = Cipher.getInstance("DES");

        cipher.init(2, securekey, random);

        return cipher.doFinal(src);
    }

    public static String MD5(String s) {
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
                'E', 'F' };
        try {
            byte[] btInput = s.getBytes("UTF-8");

            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            mdInst.update(btInput);

            byte[] md = mdInst.digest();

            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; ++i) {
                byte byte0 = md[i];
                str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];
                str[(k++)] = hexDigits[(byte0 & 0xF)];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String crypToDes(String source, String key) {
        if ((source == null) || (source.equals("")))
            return source;
        try {
            return Base64Util.encode(desCrypto(source.getBytes("UTF-8"), MD5(key)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return source;
    }

    public static String decrypt(String src, String key) {
        try {
            if ((src == null) || (src.equals(""))) {
                return src;
            }
            return new String(decrypt(Base64Util.decode(src), MD5(key)), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String str = "中国联通";

        String key = "f@2a#3p$4";
        String mi = crypToDes(str, key);
        System.out.println("加密：" + mi);
        System.out.println("解密：" + decrypt(mi, key));
    }
}