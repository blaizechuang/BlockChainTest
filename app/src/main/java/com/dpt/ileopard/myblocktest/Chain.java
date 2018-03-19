package com.dpt.ileopard.myblocktest;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * Created by Blaize on 2018/3/19.
 */

public class Chain {

    public void init() {
        ArrayList<Block> chain = new ArrayList<>();
        chain.add(new Block("Hi im the first block", "0"));
        System.out.println("Trying to Mine block 1... ");
        chain.get(0).mineBlock(Setting.DIFFICULTY);

        chain.add(new Block("Yo im the second block",chain.get(chain.size()-1).hash));
        System.out.println("Trying to Mine block 2... ");
        chain.get(1).mineBlock(Setting.DIFFICULTY);

        chain.add(new Block("Hey im the third block",chain.get(chain.size()-1).hash));
        System.out.println("Trying to Mine block 3... ");
        chain.get(2).mineBlock(Setting.DIFFICULTY);

        System.out.println("\nChain is Valid: " + isChainValid(chain));

        String mChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(chain);
        System.out.println("\nThe block chain: ");
        System.out.println(mChainJson);
    }
    public Boolean isChainValid(ArrayList<Block> chain) {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[Setting.DIFFICULTY]).replace('\0', '0');

        //循环区块链并检查 hash 值：
        for(int i=1; i < chain.size(); i++) {
            currentBlock = chain.get(i);
            previousBlock = chain.get(i-1);
            //比较当前区块存储的 hash 值和计算出来的 hash 值：
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            //比较前一个区块存储的 hash 值和当前区块存储的 previousHash 值：
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }

            //检查 hash 值是否已经存在
            if(!currentBlock.hash.substring( 0, Setting.DIFFICULTY).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }
}
