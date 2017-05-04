package com.lin.poweradapter.example.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.AnimationUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by owp on 2017/4/27.
 */

public class Utils {

    public static View fadeIn(@NonNull Context context, @NonNull View view) {
        return fadeIn(context, view, true);
    }

    public static View fadeIn(@NonNull Context context, @NonNull View view, boolean animate) {
        if (view != null)
            if (animate)
                view.startAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_in));
            else
                view.clearAnimation();
        return view;
    }

    public static String readFromAssets(Context context, String fileName) {
        StringBuffer strbuffer = new StringBuffer();
        String line;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(context.getResources().getAssets().open(fileName)));
            while ((line = reader.readLine()) != null) {
                strbuffer.append(line).append("\r\n");
            }
        } catch (IOException e) {

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return strbuffer.toString();
    }

}
