package com.jh.societymember;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_logout =findViewById(R.id.btn_logout);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //로그아웃하기
//                mFirebaseAuth.signOut(); -> 이게 있어서 파괴가 안됬었음! 이유 알아보기!
                Intent intent= new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
       // mFirebaseAuth.getCurrentUser().delete(); //탈퇴 처리!

        Button btn_TInsert =findViewById(R.id.btn_TInsert);
        btn_TInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2= new Intent(MainActivity.this, TInsertActivity.class);
                startActivity(intent2);
            }
        });// end btn_TInsert

        Button btn_TMInsert =findViewById(R.id.btn_TMInsert);
        btn_TMInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3= new Intent(MainActivity.this, TMInsertActivity.class);
                startActivity(intent3);
            }
        });// end btn_TMInsert

        Button btn_TRead =findViewById(R.id.btn_TRead);
        btn_TRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4= new Intent(MainActivity.this, TReadActivity.class);
                startActivity(intent4);
            }
        });// end btn_TRead

        Button btn_TUpdate =findViewById(R.id.btn_TUpdate);
        btn_TUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, TUpdateActivity.class);
            }
        });// end btn_TUpdate

    }
}