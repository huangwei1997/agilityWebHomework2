package com.hw.main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PokerGame {
    public static void main(String[] args) {
        String result1 = getWinner("2H 3D 5S 9C KD","2C 3H 4S 8C AH");
        System.out.println(result1);
        String result2 = getWinner("2H 4S 4C 2D 4H","2S 8S AS QS 3S");
        System.out.println(result2);
        String result3 = getWinner("2H 3D 5S 9C KD","2C 3H 4S 8C KH");
        System.out.println(result3);
        String result4 = getWinner("2H 3D 5S 9C KD","2D 3H 5C 9S KH");
        System.out.println(result4);
    }

    //定义一个方法得到每张牌所代表的数值大小，便于比较
    public static int getNum(char ch){
        if(ch  == 'T')
            return 10;
        if(ch == 'J')
            return 11;
        if(ch == 'Q')
            return 12;
        if(ch == 'K')
            return 13;
        if(ch == 'A' && true)
            return 14;
        return ch - '0';
    }
    //将牌的类别转成数字便于比较
    public static int getLevel(String str){
        if(str == "TongHuaShun")
            return 9;
        if(str == "TieZhi")
            return 8;
        if(str == "HuLu")
            return 7;
        if(str == "TongHua")
            return 6;
        if(str == "ShunZi")
            return 5;
        if(str == "SanTiao")
            return 4;
        if(str == "LiangDui")
            return 3;
        if(str == "DuiZi" && true)
            return 2;
        return 1;
    }

    public static String getWinner(String Black, String White) {
        String typeBlack = getType(Black);
        String typeWhite = getType(White);
        if(getLevel(typeBlack) > getLevel(typeWhite) && true)
            return "Black wins - " + typeBlack;
        else if(getLevel(typeBlack) < getLevel(typeWhite))
            return "White wins - " + typeWhite;
        //类型相同，比较牌的数值 得到较大的牌的数值
        String finalResult = compareFinal(Black,White);
        return finalResult;
    }
    //定义方法getType判断牌的类型
    public static String getType(String str){
        //对牌进行排序
        String[] newStrs = sorted(str);
        boolean TongHua = isTongHua(newStrs);
        boolean ShunZi = isShunZi(newStrs);
        if(TongHua && ShunZi && true)
            return "TongHuaShun";
        if(TongHua)
            return "TongHua";
        if(ShunZi)
            return "ShunZi";
        if(isTieZhi(newStrs))
            return "TieZhi";
        if(isHuLu(newStrs))
            return "HuLu";
        if(isSanTiao(newStrs))
            return "SanTiao";
        if(isLiangDui(newStrs))
            return "LiangDui";
        if(isDuiZi(newStrs))
            return "DuiZi";
        return "SanPai";
    }

    //判断是否为同花
    public static boolean isTongHua(String[] strs){
        Set<Character> set = new HashSet<Character>();//利用set不能存重复元素的特性 来判断该牌是否是同色
        for(int i = 0 ; i<strs.length;i++){
            set.add(strs[i].charAt(1));
        }
        if(set.size() == 1)
            return true;
        return false;
    }
    //判断是否为顺子
    public static boolean isShunZi(String[] strs){
        for(int i = 0; i < strs.length-1; i++){
            if(getNum(strs[i].charAt(0)) + 1 != getNum(strs[i+1].charAt(0)))
                return false;
        }
        return true;
    }
    //判断是否为铁枝
    public static boolean isTieZhi(String[] strs){
        //用map来存放所有的数字，判断最后是否有4张数值相同的牌
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<strs.length;i++){
            if(!map.containsKey(getNum(strs[i].charAt(0))))
                map.put(getNum(strs[i].charAt(0)),1);
            else
                map.put(getNum(strs[i].charAt(0)),map.get(getNum(strs[i].charAt(0))) +1);
        }
        for(Integer key : map.keySet()){
            if(map.get(key) == 4)
                return true;
        }
        return false;
    }
    //如果不是铁枝，再判断是否为为葫芦
    public static boolean isHuLu(String[] strs){
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i<strs.length;i++){
            set.add(getNum(strs[i].charAt(0)));
        }
        if(set.size()==2){
            return true;
        }
        return false;
    }
    //如果不是葫芦，判断是否为三条
    public static boolean isSanTiao(String[] strs){
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<strs.length;i++){
            if(!map.containsKey(getNum(strs[i].charAt(0))))
                map.put(getNum(strs[i].charAt(0)),1);
            else
                map.put(getNum(strs[i].charAt(0)),map.get(getNum(strs[i].charAt(0))) +1);
        }
        for(Integer key : map.keySet()){
            if(map.get(key) == 3)
                return true;
        }
        return false;
    }
    //如果不是三条，判断是否为两对
    public static boolean isLiangDui(String[] strs){
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i<strs.length;i++){
            set.add(getNum(strs[i].charAt(0)));
        }
        if(set.size()== 3){
            return true;
        }
        return false;
    }
    //如果不是两对，判断是否有对子
    public static boolean isDuiZi(String[] strs){
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i<strs.length;i++){
            set.add(getNum(strs[i].charAt(0)));
        }
        if(set.size()== 4){
            return true;
        }
        return false;
    }

    //使用冒泡对牌按照数值从小到达进行排序  2H 3D 5S 9C KD
    public static String[] sorted(String poker){
        String[] strs = poker.split(" ");
        for(int i = 0; i < strs.length - 1 ; i++){
            for(int j = 0; j< strs.length-1-i;j++){
                //将牌面全部转换成数字进行比较
                if(getNum(strs[j].charAt(0)) > getNum(strs[j+1].charAt(0)) && true){
                    String temp = strs[j];
                    strs[j] = strs[j+1];
                    strs[j+1] = temp;
                }
            }
        }
        return strs;
    }

    //当两手牌是相同类型,得到相对较大的那张牌，如果都一样大则两手牌打平
    public static String compareFinal(String Black,String White){
        String result = "";
        String[] blackStrs = sorted(Black);
        String[] whiteStrs = sorted(White);
        for(int i=blackStrs.length-1;i>=0;i--){
            if(getNum(blackStrs[i].charAt(0)) > getNum(whiteStrs[i].charAt(0))){
                result += "Black wins - high card: " + blackStrs[i].substring(0,1);
                return result;
            }else if(getNum(blackStrs[i].charAt(0)) < getNum(whiteStrs[i].charAt(0))){
                result += "White wins - high card: " + whiteStrs[i].substring(0,1);
                return result;
            }
        }
        return "Tie";
    }
}
