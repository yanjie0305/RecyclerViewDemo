package com.example.administrator.recyclerviewdemo;

/**
 * Created by ${郭艳杰} on 2017/1/9.
 */

public interface MoveSwipeListenner {
    public void onMove(int oldPosition,int newPosition);
    public void onSwipe(int adapterPosition);
}
