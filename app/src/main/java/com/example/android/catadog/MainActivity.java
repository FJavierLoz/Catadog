package com.example.android.catadog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.android.catadog.adapters.RecyclerViewAdapter;
import com.example.android.catadog.async.LoadBreedsAsyncTask;
import com.example.android.catadog.integration.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener {

    private static DatabaseHandler db;
    private RecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHandler(this);
        new LoadBreedsAsyncTask(this).execute();
        RecyclerView recyclerView = findViewById(R.id.breed_grid);
        List<ItemData> data = new ArrayList<>();
        int numberOfColumns = Utils.calculateNoOfColumns(this, 105) - 1;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        adapter = new RecyclerViewAdapter(this, data);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    public void saveBreeds(List<String> lista){
        for (int i=0; i < lista.size(); i++){
            db.addRaza(lista.get(i));
        }
    }

    public void updateData(List<ItemData> newData){
        adapter.setData(newData);
        findViewById(R.id.textView2).setVisibility(View.INVISIBLE);
    }

    @Override
    public void onItemClick(View view, String raza) {
        Intent intent = new Intent(this, ViewPhotosActivity.class);
        intent.putExtra("breed", raza);
        startActivity(intent);
    }
}