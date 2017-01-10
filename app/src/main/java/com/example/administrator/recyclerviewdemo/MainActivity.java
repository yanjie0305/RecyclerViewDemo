package com.example.administrator.recyclerviewdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<String> dataList;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        initData();
        myAdapter = new MyAdapter();
        ItemTouchCallBack itemTouchCallBack = new ItemTouchCallBack(myAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallBack);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        recyclerView.setAdapter(myAdapter);
    }

    private void initData() {
        dataList = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            dataList.add(i+"");
        }
    }
    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> implements MoveSwipeListenner{

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item,parent , false);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.text1.setText(dataList.get(position));
            holder.text1.setTextColor(Color.WHITE);
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        @Override
        public void onMove(int oldPosition, int newPosition) {
            Collections.swap(dataList,oldPosition,newPosition);
           this.notifyItemMoved(oldPosition,newPosition);
        }

        @Override
        public void onSwipe(int adapterPosition) {
            dataList.remove(adapterPosition);
            this.notifyItemRemoved(adapterPosition);

        }
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

       public TextView text1;

        public MyViewHolder(View itemView) {
            super(itemView);
            text1 = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
