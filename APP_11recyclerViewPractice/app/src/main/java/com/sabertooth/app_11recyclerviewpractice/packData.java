package com.sabertooth.app_11recyclerviewpractice;

import android.graphics.Bitmap;

public class packData {
    Bitmap profilePic;
    String about_, name_, perks;
    Integer best_bowling,best_batting,RPL,Rashes;

    packData(String A, String B, String C, Bitmap X,int bb,Integer bBat,Integer trRPL,Integer trRashes) {
        profilePic = X;
        about_ = B;
        name_ = A;
        perks = C;
        best_bowling=bb;
        best_batting=bBat;
        RPL=trRPL;
        Rashes=trRashes;
    }
}