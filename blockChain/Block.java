package blockChain;

/*
    区块结构：
        前一个区块的hash值
        这个新区块的哈希值
        保存的数据
        创建数据的事件戳
        挖矿者的工作量证明

*/
import java.util.Date;

public class Block {
    public String hash;
    //上一个区块的hash值
    public String previousHash;
    //每个区块存放的信息，这里我们存放的是一串字符串
    private String data;
    //时间戳
    private long timeStamp;
    //挖矿者的工作量证明
    private int nonce;

    //构造
    public Block(String data,String previousHash ) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        //根据previousHash、data和timeStamp产生唯一hash
        this.hash = calculateHash();
    }

    //基于上一块的内容计算新的散列
    public String calculateHash() {
//        将前一个区块的哈希值 生成当前区块的书简戳 挖矿的计算量证明和当前区块需要保存的数据拼成一个字符串 获取行动到新区块的哈希值
        String calculatedhash = StringUtil.applySha256(previousHash +Long.toString(timeStamp) +Integer.toString(nonce) + data
        );
        return calculatedhash;
    }

    //挖矿
    public void mineBlock(int difficulty) {
        //目标值，difficulty越大，下面计算量越大
        String target = StringUtil.getDificultyString(difficulty);
        //difficulty如果为5，那么target则为 00000
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("创建区块:" + hash);
    }


}
