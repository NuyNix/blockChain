package blockChain;

import java.util.ArrayList;
public class NoobChain {
    //存放所有的区块集合
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 5;//挖矿的难度，数字越大越难

    public static void main(String[] args) {
        System.out.println("正在创建一个新的区块....... ");
        addBlock(new Block("我是第一个区块", "0"));//创世块

        System.out.println("正在创建一个新的区块....... ");
        addBlock(new Block("我是第二个区块",blockchain.get(blockchain.size()-1).hash));

        System.out.println("正在创建一个新的区块.......");
        addBlock(new Block("我是第三个区块",blockchain.get(blockchain.size()-1).hash));

        System.out.println("区块链是否有效的: " + isChainValid());

        String blockchainJson = StringUtil.getJson(blockchain);
        System.out.println(blockchainJson);
    }

    /**
     * 检查区块链的完整性
     * @return
     */
    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
//        创建一个字符数组  设置数组内的全部数据都是’0‘
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //循环区块链检查散列:
        for(int i=1; i < blockchain.size(); i++) {
//            获取当前区块对象
            currentBlock = blockchain.get(i);
//            获取前一个区块对象
            previousBlock = blockchain.get(i-1);
            //比较注册散列和计算散列:
//            判断
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            //比较以前的散列和注册的先前的散列
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            //检查哈希是否被使用
            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("这个区块还没有被开采。。。");
                return false;
            }

        }
        return true;
    }
    /**
     * 增加一个新的区块
     * @param newBlock
     */
    public static void addBlock(Block newBlock) {
        newBlock.mineBlock(difficulty);
        blockchain.add(newBlock);
    }

}

