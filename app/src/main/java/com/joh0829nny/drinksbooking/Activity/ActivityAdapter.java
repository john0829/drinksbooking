package com.joh0829nny.drinksbooking.Activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.joh0829nny.drinksbooking.MainActivity;
import com.joh0829nny.drinksbooking.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joh0829nny on 2017/9/8.
 */



public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {
    private List<String> mData;
    private List<String> drinkName = new ArrayList<>();
    private List<String> sugarLevel = new ArrayList<>();
    private List<String> iceLevel = new ArrayList<>();
    private Context context;

    public ActivityAdapter(Context context){
        this.context = context;
    }

    public void add_drinkNameList(String drink_name){
        this.drinkName.add(drink_name);
    }
    public void add_sugarLevelList(String sugar_level){
        this.sugarLevel.add(sugar_level);
    }
    public void add_iceLevelList(String ice_level){
        this.iceLevel.add(ice_level);
    }
    public void add_data(List<String> data) {
        mData = data;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView_drinkname;
        public TextView mTextView_icelevel;
        public TextView mTextView_sugarlevel;

        public ViewHolder(View v) {
            super(v);
            mTextView_drinkname = (TextView) v.findViewById(R.id.info_text_drinkname);
            mTextView_icelevel = (TextView) v.findViewById(R.id.info_text_icelevel);
            mTextView_sugarlevel = (TextView) v.findViewById(R.id.info_text_sugarlevel);
        }
    }


    @Override
    public ActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //int list_position = Integer.parseInt(mData.get(position));
        System.out.println("drink"+ position);
        if(drinkName.size() > position)
        {
            holder.mTextView_drinkname.setText(drinkName.get(position));
            holder.mTextView_icelevel.setText(iceLevel.get(position));
            holder.mTextView_sugarlevel.setText(sugarLevel.get(position));
        }else{
            holder.mTextView_drinkname.setText("");
            holder.mTextView_icelevel.setText("");
            holder.mTextView_sugarlevel.setText("");

        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Item " + position + " is clicked.", Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "Item " + position + " is long clicked.", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

