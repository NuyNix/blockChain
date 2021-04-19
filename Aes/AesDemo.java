
package Aes;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AesDemo {
    public static void main(String[] args) throws Exception {
        String input = "AES加密算法的实现";
//        高级加密算法的密钥必须是16位的
        String key = "1234567812345678";
//        设置算法
        String transformation = "AES";
//        设置算法类型
        String algorithm = "AES";
//        调用加密的方法
        String encryptAES = encryptAES(input, key, transformation, algorithm);
        System.out.println("加密:" + encryptAES);

        String s = decryptAES(encryptAES, key, transformation, algorithm);
        System.out.println("解密:" + s);
    }

//    数据解密
    private static String decryptAES(String encryptDES, String key, String transformation, String algorithm) throws Exception {
//        创建解密对象
        Cipher cipher = Cipher.getInstance(transformation);
//        创建解密规则
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
//        解密初始化
        cipher.init(2, secretKeySpec);
//        调用方法对数据解密
        byte[] bytes = cipher.doFinal(Base64.decode(encryptDES));
        return new String(bytes);
    }

    private static String encryptAES(String input, String key, String transformation, String algorithm) throws Exception {
//        创建加密对象
        Cipher cipher = Cipher.getInstance(transformation);
//        设置加密规则
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm);
//        初始化加密
        cipher.init(1, sks);
//        调用方法对数据加密
        byte[] bytes = cipher.doFinal(input.getBytes());
//        转码
        String encode = Base64.encode(bytes);
        return encode;
    }
}
