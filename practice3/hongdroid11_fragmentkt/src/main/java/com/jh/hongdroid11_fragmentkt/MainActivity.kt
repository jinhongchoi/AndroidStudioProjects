package com.jh.hongdroid11_fragmentkt

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_fragment1 :Button = findViewById(R.id.btn_fragment1)
        val btn_fragment2 :Button = findViewById(R.id.btn_fragment2)
        val btn_fragment3 :Button = findViewById(R.id.btn_fragment3)

        setFrag(0) //최초 실행시 나올 화면

        btn_fragment1.setOnClickListener(){
            setFrag(0)
        }
        btn_fragment2.setOnClickListener(){
            setFrag(1)
        }
        btn_fragment3.setOnClickListener(){
            setFrag(2)
        }

    }

    private fun setFrag(fragNum: Int) { //fragment 불러오고 교체해주는 함수
        var ft= supportFragmentManager.beginTransaction()

        when(fragNum)//when은 자바에서 switch 믄
        {
            0->{
                ft.replace(R.id.main_frame, Fragment1()).commit()
            }
            1->{
                ft.replace(R.id.main_frame, Fragment2()).commit()
            }
            2->{
                ft.replace(R.id.main_frame, Fragment3()).commit()
            }
        }

    }//setFrag
}