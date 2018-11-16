package com.sabertooth.app_11recyclerviewpractice;

import android.graphics.Bitmap;

public class packData {
    Bitmap profilePic;
    String about_, name_, perks;

    packData(String A, String B, String C, Bitmap X) {
        profilePic = X;
        about_ = B;
        name_ = A;
        perks = C;
    }
}