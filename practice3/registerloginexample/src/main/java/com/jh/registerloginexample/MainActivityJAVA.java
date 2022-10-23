package com.jh.registerloginexample;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityJAVA extends AppCompatActivity {

    private TextView tv_id, tv_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_id= findViewById(R.id.tv_id);
        tv_pass= findViewById(R.id.tv_pass);

        //intent에 담은걸 꺼내오는 과정
        Intent intent = getIntent();
        String userID= intent.getStringExtra("userID");
        String userPassword= intent.getStringExtra("userPassword");

        // 꺼내온 데이터를 텍스트에 출력하는과정
        tv_id.setText(userID);
        tv_pass.setText(userPassword);

    }//end oncreate
}//end class
