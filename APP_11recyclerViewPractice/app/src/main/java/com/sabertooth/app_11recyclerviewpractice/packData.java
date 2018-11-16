package com.sabertooth.app_11recyclerviewpractice;

import android.graphics.Bitmap;

public class packData {
    Bitmap profilePic;
    String about_, name_, perks,best_bowling,best_batting,trophies;

    packData(String A, String B, String C, Bitmap X,String bb,String bBat,String tr) {
        profilePic = X;
        about_ = B;
        name_ = A;
        perks = C;
        best_bowling=bb;
        best_batting=bBat;
        trophies=tr;
    }
}