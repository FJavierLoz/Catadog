package com.example.android.catadog.async;

import android.content.Context;
import android.os.AsyncTask;

import com.example.android.catadog.Constants;
import com.example.android.catadog.ViewPhotosActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoadBreedImagesAsyncTask extends AsyncTask<String, Void, List<String>> {

    private final Context context;



    public LoadBreedImagesAsyncTask(Context context) {
        super();

        this.context = context;
    }

    @Override
    protected List<String> doInBackground(String... strings) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(String.format(Constants.URLgetBreedImages, strings[0].replace('-','/'))).build();
        List<String> data = new ArrayList<>();

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                return null;
            }
            JSONObject jsonResponse = new JSONObject(response.body().string());
            JSONArray jsonArray = jsonResponse.getJSONArray("message");
            List<String> resultList = new ArrayList<String>();
            for(int i=0; i < jsonArray.length(); i++){
                resultList.add(jsonArray.get(i).toString());
            }
            return resultList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    protected void onPostExecute(List<String> strings) {
        if(strings!=null){
            ((ViewPhotosActivity)context).createViewPager(strings.toArray(new String[4]));
        }
    }
}
