package com.jh.hongdroid5_listview

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var UserList = arrayListOf<User>(
        User(R.drawable.image1, "최진홍","32","안녕하세요."),

        User(R.drawable.image1, "122","32","안녕하세요."),
        User(R.drawable.image1, "222","32","안녕하세요."),
        User(R.drawable.image1, "333","32","안녕하세요."),
        User(R.drawable.image1, "444","32","안녕하세요."),
        User(R.drawable.image1, "555","32","안녕하세요."),
        User(R.drawable.image1, "666","32","안녕하세요.")
    )//user.kt 의 양식과 동일하게 적는다. -> user.kt에 있는 양식에 여러개를 list형태로 담았다
    // -> onCreate에다가 adapter와 함께 사용!

    override fun onCreate(savedInstanceState: Bundle?) {// onCreate -> 액티비티의 실행 시작지점
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var listView : ListView= findViewById(R.id.listView)

//        val item = arrayOf("사과","배","딸기","키위","최진홍")//String 형태의 배열을 생성한거!
//        //context : 한 액티비티의 모든 정보르 담고 있다.
//        listView.adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,item)
//        //simple_list_item_1 ->한줄로 표기해주세요.
//        //listview는 adapter가 있어야 값을 보내줄수 있다 ->코드를 꽂으려면 필요한 어댑터라고 생각

        val Adapter=UserAdapter(this,UserList)
        listView.adapter=Adapter

        listView.onItemClickListener=AdapterView.OnItemClickListener{parent,view,position, id->
            val selectItem =parent.getItemAtPosition(position) as User
            Toast.makeText(this, selectItem.name, Toast.LENGTH_SHORT).show()
            // this ->현재 엑티비티!  selectItem.name 이외에 user에 있는 변수들 아무거나 사용해서 원하는 것을 띄우는 것 가능!
        }

    }
}





