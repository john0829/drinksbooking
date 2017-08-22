package com.joh0829nny.drinksbooking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {


    Button check;
    TextView drink_name;
    TextView drink_sugar;
    TextView drink_ice;

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

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0){
            if(resultCode == 101){
                Bundle b = data.getExtras();
                String str1 = b.getString("drink_level");
                String str2 = b.getString("sugar_level");
                CharSequence str3 = b.getString(("ice_level"));

                drink_name = (TextView) findViewById(R.id.drink_name);
                drink_sugar = (TextView) findViewById(R.id.drink_sugar);
                drink_ice = (TextView) findViewById(R.id.drink_ice);

                drink_name.setText(str1);
                drink_sugar.setText(str2);
                drink_ice.setText(str3);

            }
        }
    }
}
