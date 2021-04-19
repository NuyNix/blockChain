package Des;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class desDemo {
    public desDemo() {
    }

    public static void main(String[] args) throws Exception {
//        加密的原文 原文的字节数必须是8的倍数
        String input = "密码学与区块链";
//        密钥
        String key = "12345678";
//        设置加密的算法
//        加密模式 EBC 把一段文本进行加密 使用同一个key进行拆分加密 然后组合到一起
//          CBC ：在加密的时候取决与IV向量 把前面的IV向量进行亦或操作 后面的明文在加密的时候一直依赖与前面加密的key
        String transformation = "DES";
//        设置加密的类型
        String algorithm = "DES";
//        调用加密的方法
//        返回值是加密后的字符串
        String encryptDES = encryptDES(input, key, transformation, algorithm);
        System.out.println("加密:" + encryptDES);
//        调用解密的方法
//        返回值是解密后的字符串
        String s = decryptDES(encryptDES, key, transformation, algorithm);
        System.out.println("解密:" + s);
    }

//    解密的方法
    private static String decryptDES(String encryptDES, String key, String transformation, String algorithm) throws Exception {
//        创建解密的对象
        Cipher cipher = Cipher.getInstance(transformation);
//        创建解密的规则
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
//        初始化密钥向量
//        IvParameterSpec iv = new IvParameterSpec(key.getBytes());
//        加密初始化
        cipher.init(2, secretKeySpec);
//        完成解密的操作
        byte[] bytes = cipher.doFinal(Base64.decode(encryptDES));
        return new String(bytes);
    }
//  加密的方法 四个参数 ： 1.原文 2.密钥 3.加密的算法 4.加密的类型
    private static String encryptDES(String input, String key, String transformation, String algorithm) throws Exception {
//        创建加密的对象
        Cipher cipher = Cipher.getInstance(transformation);
//        创建件加密规则
//        两个参数 1.密钥的字节数组 2.加密的算法
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm);
//      反馈模式中的密码
//        IvParameterSpec iv = new IvParameterSpec(key.getBytes());
//        加密初始化
        cipher.init(1, sks);
        byte[] bytes = cipher.doFinal(input.getBytes());
//        转码 将编码换成base64
        String encode = Base64.encode(bytes);
        return encode;
    }
}
