package com.sample.searchapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sample.searchapp.R;
import com.sample.searchapp.data.SearchResult;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder> {

    private OnSearchItemClickListener mlistener;
    private Context mContext;
    private List<SearchResult> mSearchResultList;

    public SearchResultAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_search_result_image, parent, false);
        return new SearchResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultViewHolder holder, int position) {
        if (/*mSearchResultList.get(position).getImageResult() != null &&*/
                mSearchResultList.get(position).getImageResult().get(0) != null) {
            String imageUrl = mSearchResultList.get(position).getImageResult().get(0).getLink();
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
                    .resize(400, 400)
//                    .centerCrop()
                    .into(holder.ivCover);

            holder.ivCover.setOnClickListener(view -> {
                mlistener.onImageClick(holder.ivCover, mSearchResultList.get(position));
            });
        }
    }

    @Override
    public int getItemCount() {
        return mSearchResultList == null ? 0 : mSearchResultList.size();
    }

    class SearchResultViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivCover;

        public SearchResultViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCover = itemView.findViewById(R.id.iv_cover);
        }
    }

    public void setItems(List<SearchResult> searchResultList) {
        mSearchResultList = searchResultList;
        notifyDataSetChanged();
    }

    public interface OnSearchItemClickListener {
        void onImageClick(View v, SearchResult searchResult);
    }

    public void setOnSearchItemClickListener(OnSearchItemClickListener listener) {
        mlistener = listener;
    }
}