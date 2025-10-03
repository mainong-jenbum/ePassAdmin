package com.jenbumapps.e_passbordumsa_admin.utility;

import android.app.Activity;
import android.widget.Toast;

import com.jenbumapps.e_passbordumsa_admin.R;

public class Utility {

    private static long exitTime = 0;
    public static void exitApp(Activity context){

        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(context, R.string.press_again_exit_app, Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            context.finish();
        }
    }
}
