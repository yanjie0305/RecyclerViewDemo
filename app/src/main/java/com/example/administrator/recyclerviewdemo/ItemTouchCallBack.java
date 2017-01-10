package com.example.administrator.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by ${郭艳杰} on 2017/1/9.
 */

public class ItemTouchCallBack extends ItemTouchHelper.Callback {
    private final MainActivity.MyAdapter myAdapter;

    public ItemTouchCallBack(MainActivity.MyAdapter myAdapter) {
        this.myAdapter = myAdapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlag = ItemTouchHelper.DOWN|ItemTouchHelper.UP|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
        int swipeFlag = ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
        int i = ItemTouchHelper.Callback.makeMovementFlags(dragFlag, swipeFlag);
        return i;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int oldPosition = viewHolder.getAdapterPosition();
        int newPosition = target.getAdapterPosition();
        myAdapter.onMove(oldPosition,newPosition);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int adapterPosition = viewHolder.getAdapterPosition();
        myAdapter.onSwipe(adapterPosition);

    }
}
