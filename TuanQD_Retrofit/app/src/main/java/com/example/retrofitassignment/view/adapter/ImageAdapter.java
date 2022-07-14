package com.example.retrofitassignment.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitassignment.databinding.ItemLayoutBinding;
import com.example.retrofitassignment.model.MarsProperty;
import com.example.retrofitassignment.view.DetailActivity;

import java.util.List;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    private List<MarsProperty> marsPropertyList;
    private final OnItemClickListener onItemClickListener;
    Context mContext;

    public ImageAdapter(OnItemClickListener onItemClickListener, List<MarsProperty> marsPropertyList, Context context) {
        this.marsPropertyList = marsPropertyList;
        this.onItemClickListener = onItemClickListener;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLayoutBinding itemBinding = ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new MyViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.setModel(marsPropertyList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if (marsPropertyList != null) return marsPropertyList.size();
        return 0;
    }

    public void setMarsPropertyList(List<MarsProperty> marsPropertyList) {
        this.marsPropertyList = marsPropertyList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemLayoutBinding binding;

        public MyViewHolder(@NonNull ItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(view, getBindingAdapterPosition(), false);
            int position = getBindingAdapterPosition();
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra("DETAIL_REALESTATE", marsPropertyList.get(position));
            Log.i("marsPropertyList", ""+marsPropertyList.get(position));
            mContext.startActivity(intent);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, boolean isLongClick);
    }

}
