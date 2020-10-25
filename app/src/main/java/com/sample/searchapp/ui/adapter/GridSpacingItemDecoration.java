package com.sample.searchapp.ui.adapter;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;
    private boolean includeEdge;

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = spacing;
            }
            outRect.bottom = spacing; // item bottom
        } else {
            outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = 16; // item top
            }
        }
    }


    private int mSizeGridSpacingPx;
    private int mGridSize;

    private boolean mNeedLeftSpacing = false;

//    public GridSpacingItemDecoration(int gridSpacingPx, int gridSize) {
//        mSizeGridSpacingPx = gridSpacingPx;
//        mGridSize = gridSize;
//    }
//
//    @Override
//    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        int frameWidth = (int) ((parent.getWidth() - (float) mSizeGridSpacingPx * (mGridSize - 1)) / mGridSize);
//        int padding = parent.getWidth() / mGridSize - frameWidth;
//        int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewAdapterPosition();
//        if (itemPosition < mGridSize) {
//            outRect.top = 0;
//        } else {
//            outRect.top = mSizeGridSpacingPx;
//        }
//        if (itemPosition % mGridSize == 0) {
//            outRect.left = 0;
//            outRect.right = padding;
//            mNeedLeftSpacing = true;
//        } else if ((itemPosition + 1) % mGridSize == 0) {
//            mNeedLeftSpacing = false;
//            outRect.right = 0;
//            outRect.left = padding;
//        } else if (mNeedLeftSpacing) {
//            mNeedLeftSpacing = false;
//            outRect.left = mSizeGridSpacingPx - padding;
//            if ((itemPosition + 2) % mGridSize == 0) {
//                outRect.right = mSizeGridSpacingPx - padding;
//            } else {
//                outRect.right = mSizeGridSpacingPx / 2;
//            }
//        } else if ((itemPosition + 2) % mGridSize == 0) {
//            mNeedLeftSpacing = false;
//            outRect.left = mSizeGridSpacingPx / 2;
//            outRect.right = mSizeGridSpacingPx - padding;
//        } else {
//            mNeedLeftSpacing = false;
//            outRect.left = mSizeGridSpacingPx / 2;
//            outRect.right = mSizeGridSpacingPx / 2;
//        }
//        outRect.bottom = 0;
//    }
}