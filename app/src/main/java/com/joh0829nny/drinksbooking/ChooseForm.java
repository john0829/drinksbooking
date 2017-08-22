package com.joh0829nny.drinksbooking;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ChooseForm extends AppCompatActivity {

    Button sent_btn;
    String sugar = "";
    Button select_drink;
    String ice_opt = "";
    String drink_name = "";
    int select_number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_form);




        select_drink = (Button) findViewById(R.id.drink_type);
        select_drink.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                final String[] list_item = {"紅茶","綠茶","奶茶","烏龍茶"};
                int  c = 0;
                AlertDialog.Builder dialog_list = new AlertDialog.Builder(ChooseForm.this);
                dialog_list.setTitle("飲料選擇");

                dialog_list.setItems(list_item,new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface,int which){
                        Toast.makeText(ChooseForm.this,list_item[which], Toast.LENGTH_LONG).show();
                        select_number = which;

                    }
                });
                dialog_list.show();
                drink_name = list_item[select_number];

            }

        });

        RadioGroup rg1 = (RadioGroup) findViewById(R.id.radio_group_sugar);
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group,int checkedId){
                switch(checkedId){
                    case R.id.drink_sugar1:
                        sugar = "正常";
                        break;
                    case R.id.drink_sugar2:
                        sugar = "少糖";
                        break;
                    case R.id.drink_sugar3:
                        sugar = "半糖";
                        break;
                    case R.id.drink_sugar4:
                        sugar = "微糖";
                        break;
                    case R.id.drink_sugar5:
                        sugar = "無糖";
                        break;
                }
            }
        });
        RadioGroup rg2 = (RadioGroup) findViewById(R.id.radio_group_ice);
        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group,int checkedId){
                switch(checkedId){
                    case R.id.drink_ice1:
                        ice_opt = "正常";
                        break;
                    case R.id.drink_ice2:
                        ice_opt = "少冰";
                        break;
                    case R.id.drink_ice3:
                        ice_opt = "微冰";
                        break;
                    case R.id.drink_ice4:
                        ice_opt = "去冰";
                        break;
                }
            }
        });
        sent_btn = (Button) findViewById(R.id.send_drink);
        sent_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                onResume();
                if(drink_name == ""){
                    Toast.makeText(ChooseForm.this, "select drink", Toast.LENGTH_SHORT).show();
                }
                else if(ice_opt == ""){
                    Toast.makeText(ChooseForm.this, "select ice level", Toast.LENGTH_SHORT).show();
                }
                else if(sugar == ""){
                    Toast.makeText(ChooseForm.this, "select sugar level", Toast.LENGTH_SHORT).show();
                }else {

                    AlertDialog.Builder dialog = new AlertDialog.Builder(ChooseForm.this);
                    StringBuffer buf = new StringBuffer();
                    buf.append("飲料名稱：");
                    buf.append(drink_name+"\n");
                    buf.append("甜度：");
                    buf.append(sugar+"\n");
                    buf.append("冰塊：");
                    buf.append(ice_opt+"\n");
                    dialog.setTitle("訂單確認");
                    dialog.setMessage(buf);



                    dialog.setNegativeButton("wrong", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(ChooseForm.this,"我要修改",Toast.LENGTH_LONG).show();
                        }
                    });

                    dialog.setPositiveButton("correct", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int x) {
                            Toast.makeText(ChooseForm.this,"我確定",Toast.LENGTH_LONG).show();

                            Intent i = new Intent();
                            Bundle b = new Bundle();
                            b.putString("sugar_level", sugar);
                            b.putString("drink_level", drink_name);
                            b.putString("ice_level", ice_opt);
                            i.putExtras(b);
                            setResult(101, i);
                            finish();
                        }
                    });

                    dialog.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int x) {Toast.makeText(ChooseForm.this,"取消",Toast.LENGTH_LONG).show();
                        }
                    });
                    dialog.show();


                }
            }




        });


    }
}
