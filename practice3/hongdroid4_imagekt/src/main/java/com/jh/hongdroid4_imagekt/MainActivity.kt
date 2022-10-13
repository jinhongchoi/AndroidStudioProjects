package com.jh.hongdroid4_imagekt

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn_toast:Button=findViewById(R.id.btn_toast)
        val iv_profile :ImageView=findViewById(R.id.iv_profile)

        btn_toast.setOnClickListener(){
            iv_profile.setImageResource(R.drawable.robot)// 이미지 뷰에 새로운 이미지 set한다 ! /R은 resource폴더!
            Toast.makeText(this@MainActivity,"버튼이 클릭 되었습니다.", Toast.LENGTH_SHORT).show()

        }//end btn_toast

        // 이 외에도 버튼이나 백그라운드 쪽으로 경로를 설정해 놓으면 클릭시 선택된 부분의 이미지를 바꿀 수 있다!
    }
}