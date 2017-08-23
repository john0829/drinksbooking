package com.joh0829nny.drinksbooking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;



public class MainActivity extends AppCompatActivity {


    Button check;
    TextView drink_name;
    TextView drink_sugar;
    TextView drink_ice;
    MyAdapter mAdapter = new MyAdapter();
    String drink_Name = "";
    String drink_Sugar = "";
    String drink_Ice = "";
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        check = (Button) findViewById(R.id.check);

        check.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(MainActivity.this, ChooseForm.class);
                startActivityForResult(i, 0);
            }


        });

        ArrayList<String> myDataset = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            myDataset.add(Integer.toString(i));
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.list_view);
        mAdapter.add_data(myDataset);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0){
            if(resultCode == 101){
                Bundle b = data.getExtras();
                drink_Name = b.getString("drink_level");
                drink_Sugar = b.getString("sugar_level");
                drink_Ice = b.getString(("ice_level"));

                drink_name = (TextView) findViewById(R.id.drink_name);
                drink_sugar = (TextView) findViewById(R.id.drink_sugar);
                drink_ice = (TextView) findViewById(R.id.drink_ice);

                mAdapter.add_drinkNameList(drink_Name);
                mAdapter.add_sugarLevelList(drink_Sugar);
                mAdapter.add_iceLevelList(drink_Ice);

                drink_name.setText(drink_Name);
                drink_sugar.setText(drink_Sugar);
                drink_ice.setText(drink_Ice);

            }
        }
    }


    private RecyclerView mRecyclerView;

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<String> mData;
        private List<String> drinkName = new ArrayList<>();
        private List<String> sugarLevel = new ArrayList<>();
        private List<String> iceLevel = new ArrayList<>();

        public MyAdapter(){}

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
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
                    Toast.makeText(MainActivity.this, "Item " + position + " is clicked.", Toast.LENGTH_SHORT).show();
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(MainActivity.this, "Item " + position + " is long clicked.", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }
}
