package Kaser;
import java.util.*;

public class kaser {
    public static void main(String[] args) {
        String str;
        int key ;
        Scanner input = new Scanner(System.in);
        System.out.print("请输入您需要加密的文本：");
        str = input.nextLine();
        System.out.print("请输入您的密钥（0~25）：");
        key = input.nextInt();
        String result = encrypt(str,key);
        System.out.println( "加密后的文本" + result);
        String result1 = decrypt(result,key);
        System.out.println("解密后的文本" + result1);
    }

    //    加密函数
    public static String encrypt(String clearText,int key){
        char[] chars0 = clearText.toCharArray();
        String chars1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] upperChar = chars1.toCharArray();
        String chars2 = "abcdefghijklmnopqrstuvwxyz";
        char[] lowChar = chars2.toCharArray();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < chars0.length; i++) {
            int c = chars0[i];
            if (c >= 65 && c <= 90){
//               获取字符在顺序表里面的位置
                int order = getUpperCharOrder(chars0[i]);
//                按照给定的密钥进行移位操作
                order = (order + key) % 26;
                sb.append(upperChar[order]);
            }else if(c >= 97 && c <= 122){
                int order = getLowCharOrder(chars0[i]);
                order = (order + key) % 26;
                sb.append(lowChar[order]);
            }else{
                sb.append(' ');
                continue;
            }
        }

        return sb.toString();
    }

    public static String decrypt(String cipherText,int key){
        char[] chars0 = cipherText.toCharArray();
        String chars1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] upperChar = chars1.toCharArray();
        String chars2 = "abcdefghijklmnopqrstuvwxyz";
        char[] lowChar = chars2.toCharArray();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < chars0.length; i++) {
            int c = chars0[i];
            if (c >= 65 && c <= 90){
                int order = getUpperCharOrder(chars0[i]);
                order = order - key;
                if (order < 0){
                    order = order + 26;
                }
                sb.append(upperChar[order]);
            }else if(c >= 97 && c <= 122){
                int order = getLowCharOrder(chars0[i]);
                order = order - key;
                if (order < 0){
                    order = order + 26;
                }
                sb.append(lowChar[order]);
            }else {
                sb.append(' ');
                continue;
            }
        }

        return sb.toString();
    }
    //    大写字符顺序表
    public static int getUpperCharOrder(char c){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] Char = chars.toCharArray();
        int order = 0;
        for (int i = 0; i < Char.length; i++) {
            if (Char[i] == c){
                order = i;
            }
        }
        return order;
    }

    //    小写顺序字符表
    public static int getLowCharOrder(char c){
        String chars = "abcdefghijklmnopqrstuvwxyz";
        char[] Char = chars.toCharArray();
        int order = 0;
        for (int i = 0; i < Char.length; i++) {
            if (Char[i] == c){
                order = i;
            }
        }
        return order;
    }
}
