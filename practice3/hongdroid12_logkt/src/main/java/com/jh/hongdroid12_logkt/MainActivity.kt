package com.jh.hongdroid12_logkt

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var a: Int =0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        a=2

        if(a==2){
            Log.e("if 문", "Enter") //Log.d는 밑에 로그캣에서 형식을 debug로 한 것! d이기 때문!
        }else if(a==1){
            Log.d("if 문", "Enter2") //Log.d는 밑에 로그캣에서 형식을 debug로 한 것! d이기 때문!
        }

    }
}