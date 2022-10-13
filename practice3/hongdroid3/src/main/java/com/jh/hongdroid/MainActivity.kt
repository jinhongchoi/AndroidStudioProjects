package com.jh.hongdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv_sendmsg:TextView=findViewById(R.id.tv_sendmsg)
        val btn_a:Button =findViewById(R.id.btn_a)
        btn_a.setOnClickListener(){

            val intent =Intent(this, SubActivity::class.java)
            // 다음 화면으로 이동하기 위한 인텐트 객체 생성

            intent.putExtra("msg",tv_sendmsg.text.toString())//HelloWorld라는 텍스트 값을 담은 뒤 msg라는 키로 잠궜다.
            startActivity(intent)// intent에 저장되어있는 액티비티 쪽으로 이동한다
            finish()// 자기 자신 액티비티를 파괴한다.(사라진다.)-> sub갔다가 여기 다시오면 페이지가 사라져있음!
        }
    }
}