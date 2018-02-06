package com.example.saad_kamaal.bankingagent.helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.example.saad_kamaal.bankingagent.activities.MainActivity;

/**
 * Created by saad_kamaal on 10/3/2017.
 */

public class UtilHelper {

    private static ProgressDialog waitDialog;

    private static final String LOGIN_SESSION_KEY = "LOGIN_SESSION_KEY";

    private static SharedPreferences getPrefs(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void createLoginSession(Context context, String userId) {
        getPrefs(context).edit().putString(LOGIN_SESSION_KEY, userId).apply();
    }

    @Nullable
    public static String getUserId(Context context) {
        return getPrefs(context).getString(LOGIN_SESSION_KEY, null);
    }

    public static void endLoginSessiong(Context context) {
        getPrefs(context).edit().clear().apply();
    }

    public static void showWaitDialog(Context context, @Nullable String title) {
        try {
            if (waitDialog != null) {
                if (!waitDialog.isShowing()) {
                    waitDialog = new ProgressDialog(context);
                    waitDialog.setTitle(title);
                    waitDialog.setMessage("please wait...");
                    waitDialog.setIndeterminate(true);
                    waitDialog.setCancelable(false);
                    waitDialog.show();
                }
            } else {
                waitDialog = new ProgressDialog(context);
                waitDialog.setTitle(title);
                waitDialog.setMessage("please wait...");
                waitDialog.setIndeterminate(true);
                waitDialog.setCancelable(false);
                waitDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dismissWaitDialog() {
        try {
            if (waitDialog != null && waitDialog.isShowing()) {
                waitDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void showMessage(Context context, String message) {
        new AlertDialog.Builder(context).setTitle("Alert").setMessage(message).show();
    }
}