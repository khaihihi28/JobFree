package com.poly.duantotnghiep_jf.Util;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class Alert {
    public static void showSnackbarMessage(View view,String message){
        Snackbar.make(view,message,Snackbar.LENGTH_SHORT).show();
    }
}
