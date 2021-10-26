package com.example.android.catadog.async;

import android.content.ClipData;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.android.catadog.Constants;
import com.example.android.catadog.ItemData;
import com.example.android.catadog.MainActivity;
import com.example.android.catadog.Utils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class LoadBreedsAsyncTask extends AsyncTask<Void,Void,List<ItemData>> {

    private Context context;

    public LoadBreedsAsyncTask(Context context) {
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<ItemData> getRazas(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(Constants.URLgetAllBreeds).build();
        List<ItemData> data = new ArrayList<>();


        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                return null;
            }
            List<String> nombres =  Utils.razasJSONtoList(response.body().string());
            for(int i=0; i < nombres.size(); i++) {
                request = new Request.Builder().url(String.format(Constants.URLgetRandomBreedImage, nombres.get(i).replace('-','/'))).build();
                response = client.newCall(request).execute();
                if (!response.isSuccessful()) {
                    return null;
                }
                data.add(new ItemData (nombres.get(i),new JSONObject(response.body().string()).getString("message")));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return data;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected List<ItemData> doInBackground(Void... voids) {
        return getRazas();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onPostExecute(List<ItemData> lista) {
        if(lista != null){
            ((MainActivity)context).updateData(lista);
            ((MainActivity)context).saveBreeds(lista.stream().map(ItemData::getNombre).collect(Collectors.toList()));
        }
    }
}
