package com.planet.treeplantations.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static boolean isNetworkAvailable(Context con) {
        try {
            ConnectivityManager cm = (ConnectivityManager) con.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void hideKeyboard(Activity activity) {
     /*   InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
*/

        InputMethodManager inputManager = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View currentFocusedView = activity.getCurrentFocus();
        if (currentFocusedView != null) {
            inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    public static String getDateWithMonth(String date) {
        String outputDate = "";
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yyyy, hh:mm a");
        try {
            if (date != null) {
                Date date1 = input.parse(date);
                outputDate = output.format(date1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDate;
    }



}
