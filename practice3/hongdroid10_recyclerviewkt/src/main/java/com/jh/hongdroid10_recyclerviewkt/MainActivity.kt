package com.jh.hongdroid10_recyclerviewkt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rv_profile: RecyclerView= findViewById(R.id.rv_profile)

        val profileList = arrayListOf(
            Profiles(R.drawable.man, "홍드로이드",27,"안드로이드 앱 개발자"), //Profiles.kt 의 들어가야 될 값을 순서대로 집어 넣는다!
            Profiles(R.drawable.woman, "김드로이드",28,"안드로이드 앱 개발자"),
            Profiles(R.drawable.man, "이드로이드",29,"안드로이드 앱 개발자"),
            Profiles(R.drawable.woman, "박드로이드",30,"안드로이드 앱 개발자"),
            Profiles(R.drawable.man, "홍드로이드",27,"안드로이드 앱 개발자"),
            Profiles(R.drawable.woman, "김드로이드",28,"안드로이드 앱 개발자"),
            Profiles(R.drawable.man, "이드로이드",29,"안드로이드 앱 개발자"),
            Profiles(R.drawable.woman, "박드로이드",30,"안드로이드 앱 개발자")

        )
        //this -> mainActivity가 가지고 있는 정보(값)들
        rv_profile.layoutManager=LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        rv_profile.setHasFixedSize(true)

        rv_profile.adapter =ProfileAdapter(profileList) // 만들어놨던 ProfileAdapter 최초 생성!
    }
}