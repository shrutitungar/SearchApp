package com.sample.searchapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sample.searchapp.R;
import com.sample.searchapp.data.SearchResult;

import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder> {

    private Context mContext;
    private List<SearchResult> mImageList;

    public SearchResultAdapter(Context context, List<SearchResult> imageList) {
        mContext = context;
        mImageList = imageList;
    }

    @NonNull
    @Override
    public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_search_result_image, parent, false);
        return new SearchResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultViewHolder holder, int position) {
        holder.ivCover.setOnClickListener(view -> {

        });
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    class SearchResultViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivCover;

        public SearchResultViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCover = itemView.findViewById(R.id.iv_cover);
        }
    }
}
