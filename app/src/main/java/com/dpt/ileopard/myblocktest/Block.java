package com.dpt.ileopard.myblocktest;

import java.util.Date;

/**
 * Created by Blaize on 2018/3/19.
 */

public class Block {
    public String hash;
    public String previousHash;
    private String data; //我们的数据是一条简单的消息
    private long timeStamp; //从 1/1/1970 起至现在的总毫秒数.
    private int nonce;

    //Block 类的构造方法.
    public Block(String data,String previousHash ) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String calculatedhash = Utils.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        data
        );
        return calculatedhash;
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); //创建一个用 difficulty * "0" 组成的字符串
        //System.out.println("hash: " + hash + ", target: " + target);
        int times = 0;
        while(!hash.substring( 0, difficulty).equals(target)) {
            //System.out.println("hash: " + hash + ", target: " + target);
            nonce ++;
            times++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash + ", times: " + times);
    }
}
