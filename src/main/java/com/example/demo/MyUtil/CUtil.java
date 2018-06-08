package com.example.demo.MyUtil;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;


/**
 * Created by Caoyixian on 2018/1/16 0016.
 */
public class CUtil {


    public static String strToMd5(String text) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] result = messageDigest.digest(text.getBytes("utf-8"));
        // 把每一个byte 做一个与运算 0xff;
        StringBuffer buffer = new StringBuffer();
        for (byte b : result) {
            // 与运算
            int number = b & 0xff;
            String str = Integer.toHexString(number);
            if (str.length() == 1) {
                buffer.append("0");
            }
            buffer.append(str);
        }
        // 标准的md5加密后的结果
        return buffer.toString();
    }

    public static String strToAes(String password, String content) throws Exception {
        //1.构造密钥生成器，指定为AES算法,不区分大小写
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        //2.根据ecnodeRules规则初始化密钥生成器
        //生成一个128位的随机源,根据传入的字节数组
        keygen.init(128, new SecureRandom(password.getBytes()));
        //3.产生原始对称密钥
        SecretKey original_key = keygen.generateKey();
        //4.获得原始对称密钥的字节数组
        byte[] raw = original_key.getEncoded();
        //5.根据字节数组生成AES密钥
        SecretKey key = new SecretKeySpec(raw, "AES");
        //6.根据指定算法AES自成密码器
        Cipher cipher = Cipher.getInstance("AES");
        //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
        cipher.init(Cipher.ENCRYPT_MODE, key);
        //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
        byte[] byte_encode = content.getBytes("utf-8");
        //9.根据密码器的初始化方式--加密：将数据加密
        byte[] byte_AES = cipher.doFinal(byte_encode);
        String pwdStr = new String(byte_AES);
        return pwdStr;
    }


    public static String strToHmacSha256(String text, String key) throws Exception {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);
        byte[] b = mac.doFinal(text.getBytes());
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString();
    }


    public static String mapToHttpBuild(Map data) throws Exception {
        String str = "";
        for (Object key : data.keySet()) {
            String tempString = key.toString() + "=" + data.get(key) + "&";
            str += tempString;
        }
        return str.substring(0, str.length() - 1);
    }

    public static Map httpBuildToMap(String httpBuild) throws Exception {
        String[] tempList = httpBuild.split("&");
        Map<String, String> httpMap = new TreeMap<>();
        for (String i : tempList) {
            String[] parseKeyValue = i.split("=");
            httpMap.put(parseKeyValue[0], parseKeyValue[1]);
        }
        return httpMap;
    }

    public static String getRandomStr() throws Exception {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static Map generateResponseMap(Integer error_code, String msg, Object data) {
        Map<String, Object> ret = new HashMap<>();
        ret.put("error_code", error_code);
        ret.put("msg", msg);
        ret.put("data", data != null ? data : new HashMap<>());
        return ret;
    }


}
