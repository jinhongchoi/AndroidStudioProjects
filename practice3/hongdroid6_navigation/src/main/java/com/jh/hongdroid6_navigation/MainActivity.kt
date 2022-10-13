package com.jh.hongdroid6_navigation

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener { //Navigation을 상속받는 과정

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //변수 선언시 화면이 실행되는 Oncreate보다 위에 있으면 안된다! 에뮬 실행 안됨!
        val naviView : NavigationView = findViewById(R.id.naviView)
        val layout_drawer : DrawerLayout=findViewById(R.id.layout_drawer)
        val btn_navi : ImageView = findViewById(R.id.btn_navi)

        btn_navi.setOnClickListener{
            layout_drawer.openDrawer(GravityCompat.START)
        //()괄호 안에 시잘할때 왼쪽(left)에서 올건지 오른쪽(right)에서 올건지 설정
        }
        naviView.setNavigationItemSelectedListener (this)
    //네비게이션 메뉴 아이템에 클릭 속성 부여(클릭 했을때 아래의 메소드가 실행되도록 함)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean { // Navigation 메뉴 클릭시 메뉴아이템 수행하는 메소드
        val layout_drawer : DrawerLayout=findViewById(R.id.layout_drawer)
        when(item.itemId)// ID는 item의ID를 말함
        {
            R.id.access -> Toast.makeText(applicationContext, "접근성",Toast.LENGTH_SHORT).show()
            R.id.email -> Toast.makeText(applicationContext, "이메일",Toast.LENGTH_SHORT).show()
            R.id.message -> Toast.makeText(applicationContext, "메시지",Toast.LENGTH_SHORT).show()
        }// 네비게이션의 메뉴들을 클릭(select) 했을 때 Toast메시지가 뜨게끔 만든거
        layout_drawer.closeDrawers()
        return false//네비게이션 뷰 닫기
    }


    override fun onBackPressed() {
        val layout_drawer : DrawerLayout=findViewById(R.id.layout_drawer)
        if(layout_drawer.isDrawerOpen(GravityCompat.START)){
            layout_drawer.closeDrawers() //layout_drawer가 닫히는거! 한칸씩 닫히는 기능 해주기!
        }else{
            super.onBackPressed() //일반 뒤로가기 기능 실행 (finish)
        }


    }
}