package com.jh.sharedkt

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {// 해당 액티비티가 처음 실행될 때 한 번 수행하는 곳(초기화)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //todo: 저장된 데이터를 로드 하는 구문
        loadData() // edit text 저장되어있던 값을 setText

    }

    private fun loadData() {
        val et_hello : EditText =findViewById(R.id.et_hello)
        val pref = getSharedPreferences("pref",0)// 0번은 저장 옵션
        et_hello.setText(pref.getString("name",""))
    // 1번째 인자에서는 저장할 당시의 키 값을 적어줌, 2번째는 키 값에 데이터가 존재하지 않을 경우 대체 데이터를 가져옴(현재는 공백을 넣은거)
    }


    private fun saveData() {
        val et_hello : EditText =findViewById(R.id.et_hello)

        val pref = getSharedPreferences("pref",0)// 0번은 저장 옵션
        val edit = pref.edit() // 수정모드
        edit.putString("name",et_hello.text.toString())// 1번째 인자에는 키 값을, 2번째 인자에는 실제 담아둘 값
    //String 형태로 넣는다!(editText에 Text저장 )
        edit.apply() // 담아놓은 값을 저장 완료!
    }

    override fun onDestroy() { //액티비티가 종료되는 시점이 다가올 때 호출
        super.onDestroy()

        saveData() //edit text 값을 저장 -> 꼭 destrot가 아닌 다양한 곳에 활용 가능!

        //앱이 종료되는 시점이 다가올때 값을 저장하는 작업을 수행
    }

    // SharedPreferences -> 앱을 내리거나 닫았을때 데이터가 잠시 남아 있는 상황!

}