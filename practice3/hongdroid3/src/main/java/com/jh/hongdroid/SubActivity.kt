package com.jh.hongdroid

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
        val  tv_getmsg:TextView= findViewById(R.id.tv_getmsg)

        if (intent.hasExtra("msg")){//hasExtra: intent라는 변수에 hasExtra()괄호 안에 값을 가지고있는지 없는지
            tv_getmsg.text=intent.getStringExtra("msg")// key값 넣기
        }// -> 서브 엑티비티의 존재하는 텍스트 뷰에다가 Helloworld가 넘겨져옴(text 대신 settext사용해도됨! 한마디로 set한다는거!)
        //text만 넘어옴! 색이나 크기같은건 따로 설정해야됨!

    }
}