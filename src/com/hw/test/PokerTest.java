package com.hw.test;

import org.junit.Assert;
import org.junit.Test;

public class PokerTest {
    @Test
    public void PokerTest1(){
        PokerWinner instance = new PokerWinner();
        String result = instance.getWinner("2H 3D 5S 9C KD","2C 3H 4S 8C AH");
        Assert.assertEquals("White wins - high card:A",result);
    }

    @Test
    public void PokerTest2(){
        PokerWinner instance = new PokerWinner();
        String result = instance.getWinner("2H 4S 4C 2D 4H","2S 8S AS QS 3S");
        Assert.assertEquals("Black wins - HuLu",result);
    }

    @Test
    public void PokerTest3(){
        PokerWinner instance = new PokerWinner();
        String result = instance.getWinner("2H 3D 5S 9C KD","2C 3H 4S 8C KH");
        Assert.assertEquals("Black wins - high card:9",result);
    }

    @Test
    public void PokerTest4(){
        PokerWinner instance = new PokerWinner();
        String result = instance.getWinner("2H 3D 5S 9C KD","2D 3H 5C 9S KH");
        Assert.assertEquals("Tie",result);
    }
}
