package com.joh0829nny.drinksbooking;
import com.joh0829nny.drinksbooking.Activity.ActivityAdapter;

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
    ActivityAdapter mAdapter = new ActivityAdapter(getBaseContext());
    String drink_Name = "";
    String drink_Sugar = "";
    String drink_Ice = "";
    int count = 0;
    private RecyclerView mRecyclerView;
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
        for (int i = 0; i < 100; i++) {
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
        if (requestCode == 0) {
            if (resultCode == 101) {
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
                mAdapter.notifyDataSetChanged();

                drink_name.setText(drink_Name);
                drink_sugar.setText(drink_Sugar);
                drink_ice.setText(drink_Ice);

            }
        }
    }

}
