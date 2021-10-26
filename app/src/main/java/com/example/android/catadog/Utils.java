package com.example.android.catadog;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<String> razasJSONtoList(String response) throws JSONException {
        List<String> result  = new ArrayList<String>();
        JSONObject resp = new JSONObject(response);
        JSONObject razas = resp.getJSONObject("message");
        razas.keys().forEachRemaining(key -> {
            try {
                JSONArray subrazas = razas.getJSONArray(key);
                if (subrazas.length() == 0) {
                    result.add(key);
                }
                for (int i = 0; i < subrazas.length(); i++) {
                    result.add(key  + "-" + subrazas.get(i).toString() );
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        return result;
    }

    public static int calculateNoOfColumns(Context context, float columnWidthDp) { // For example columnWidthdp=180
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (screenWidthDp / columnWidthDp + 0.5); // +0.5 for correct rounding to int.
        return noOfColumns;
    }
}
