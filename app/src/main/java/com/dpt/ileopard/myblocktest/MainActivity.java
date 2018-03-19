package com.dpt.ileopard.myblocktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.GsonBuilder;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Block> mChain = new ArrayList<>();
    public static int difficulty = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mChain.add(new Block("Hi im the first block", "0"));
        System.out.println("Trying to Mine block 1... ");
        mChain.get(0).mineBlock(difficulty);

        mChain.add(new Block("Yo im the second block",mChain.get(mChain.size()-1).hash));
        System.out.println("Trying to Mine block 2... ");
        mChain.get(1).mineBlock(difficulty);

        mChain.add(new Block("Hey im the third block",mChain.get(mChain.size()-1).hash));
        System.out.println("Trying to Mine block 3... ");
        mChain.get(2).mineBlock(difficulty);

        System.out.println("\nmChain is Valid: " + isChainValid());

        String mChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(mChain);
        System.out.println("\nThe block chain: ");
        System.out.println(mChainJson);
    }

    public Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //循环区块链并检查 hash 值：
        for(int i=1; i < mChain.size(); i++) {
            currentBlock = mChain.get(i);
            previousBlock = mChain.get(i-1);
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
            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }
}


