package com.example.android.catadog.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.catadog.ItemData;
import com.example.android.catadog.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private List<ItemData> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;


    public RecyclerViewAdapter(Context context, List<ItemData> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }


    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nombreRaza.setText(mData.get(position).getNombre());
        Picasso.get().load(mData.get(position).getURLImagen()).fit().placeholder(R.drawable.ic_dog_placeholder).into(holder.Imagen);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nombreRaza;
        ImageView Imagen;

        ViewHolder(View itemView) {
            super(itemView);
            nombreRaza = itemView.findViewById(R.id.nombre);
            Imagen = itemView.findViewById(R.id.preview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, mData.get(getAdapterPosition()).getNombre());
        }
    }


    ItemData getItem(int id) {
        return mData.get(id);
    }


    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public void setData(List<ItemData> data){
        this.mData = data;
        notifyDataSetChanged();
    }


    public interface ItemClickListener {
        void onItemClick(View view, String position);
    }
}
