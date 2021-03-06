package com.example.android.catadog.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.android.catadog.R;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;

    String[] images;

    LayoutInflater mLayoutInflater;


    public ViewPagerAdapter(Context context, String[] images) {
        this.context = context;
        this.images = images;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.viewpager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.viewpagerItem);

        Picasso.get().load(images[position]).into(imageView);

        Objects.requireNonNull(container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((LinearLayout) object);
    }
}