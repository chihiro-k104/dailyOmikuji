package com.example.dailyomikuji;

import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Omikuji {
    public static final int UNDEFINED = -1;
    public static final int DAIKICHI = 0;
    public static final int CHUKICHI = 1;
    public static final int SYOKICHI = 2;
    public static final int KYOU = 3;
    private static int[] omikuji = new int[]{R.drawable.daikichi, R.drawable.chuukichi, R.drawable.syoukichi, R.drawable.kyou};
    private static String[] omikujiText = new String[]{"大吉", "中吉", "小吉", "凶"};
    static public int getSize(){
        return omikuji.length;
    }
    // ランダムなおみくじ画像の要素番号を取得
    static public int omikuji(){
        return new Random().nextInt(getSize());
    }
    // イメージビューに画像のリソースをセットする
    static public void setImage(ImageView image, int num){
        image.setImageResource(omikuji[num]);
    }
    // おみくじの文字を取得
    static public String getText(int num){
        return omikujiText[num];
    }
}
