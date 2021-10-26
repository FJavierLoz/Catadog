package com.example.android.catadog;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.android.catadog.adapters.ViewPagerAdapter;
import com.example.android.catadog.async.LoadBreedImagesAsyncTask;

public class ViewPhotosActivity extends AppCompatActivity {

    ViewPager mViewPager;

    ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        String breed = getIntent().getStringExtra("breed");
        new LoadBreedImagesAsyncTask(this).execute(breed);
    }

    public void createViewPager(String[] resultados){
        mViewPager = (ViewPager)findViewById(R.id.viewPager);

        // Initializing the ViewPagerAdapter
        mViewPagerAdapter = new ViewPagerAdapter(ViewPhotosActivity.this, resultados);

        // Adding the Adapter to the ViewPager
        mViewPager.setAdapter(mViewPagerAdapter);
    }
}
