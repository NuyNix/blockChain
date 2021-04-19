package blockChain;

import com.google.gson.GsonBuilder;

import java.security.MessageDigest;

public class StringUtil {
    //将Sha256应用到一个字符串并返回结果
    public static String applySha256(String input){
        try {
//            获取消息摘要
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            获取消息摘要的字节数组
            byte[] hash = digest.digest(input.getBytes("UTF-8"));

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
//                数字摘要不是使用base64进行编码的 所以要将数据转换成16进制的
                String hex = Integer.toHexString(0xff & hash[i]);
//                如果生成的字符只有一个的话  在前面补0 先将0压入buffer
                if(hex.length() == 1) hexString.append('0');
//                然后再将原本的数据压入buffer
                hexString.append(hex);
            }
//            返回生成的哈希值
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    //返回JSON格式数据
    public static String getJson(Object o) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(o);
    }

    //返回难度字符串目标，与散列比较。难度5将返回“00000”
    public static String getDificultyString(int difficulty) {
        return new String(new char[difficulty]).replace('\0', '0');
    }

    public static void main(String[] args) {
        System.out.println(getDificultyString(5));
    }

}
