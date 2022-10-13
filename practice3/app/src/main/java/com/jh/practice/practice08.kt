package com.jh.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.lang.Math.abs
import java.util.*

class androidpractice08 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)//켜졌을 때 나와야 될 화면을 기입하는 곳!

        startMain()
    }//end onCreate

    var k=1
    val pointlist = mutableListOf<Float>()
    //()안에 아무것도 안넣어 주는게 비워준 상태!
    var p=0
    var isBlind = true
    //가려주는 코드

    fun startMain(){
        k=1
        isBlind==true
        setContentView(R.layout.activity_start)
        val btn_start: Button = findViewById(R.id.btn_start)
        val btn_minus: Button = findViewById(R.id.btn_minus)
        val btn_plus: Button = findViewById(R.id.btn_plus)
        val tv_num : TextView = findViewById(R.id.tv_goal)
        val btn_blind : Button = findViewById(R.id.btn_blind)

        btn_blind.setOnClickListener(){
            isBlind=!isBlind
            if(isBlind==true){
                btn_blind.text="Blind 모드 켜기"
            }else{
                btn_blind.text="Blind 모드 끄기"
            }
        }

        btn_plus.setOnClickListener(){

            p++
            tv_num.text=p.toString()

            // 맨 위에다가 전역변수로 선언하고 people을 늘리면 밑에  main 창에 자동으로 people에 대한 정보가 이동함!
            // 자바와 차이점!
        }//end btn_plus

        btn_minus.setOnClickListener(){
            if(p>0){
                p--

                tv_num.text=p.toString()
            }else{
                p=0
            }
        }//end btn_minus

        btn_start.setOnClickListener(){
            // btn.setOnClickListener() -> 버튼을 눌렀을때 실행되게 하는 메소드
            main()
            k=1
        }//end btn_start

    }//end startMain




    fun main(){
        setContentView(R.layout.activity_main)

        var timerTask : Timer? =null;

        var stage =1
        val tv1: TextView = findViewById(R.id.tv_time)
        var tv2: TextView = findViewById(R.id.tv_goal)
        var tv3: TextView = findViewById(R.id.tv_point)
        var tv4: TextView = findViewById(R.id.textViewMan)
        val btn: Button = findViewById(R.id.btn_start)
        val btn_return : Button = findViewById(R.id.btn_return)
        var time = 0;
        var isRunning =false //시작과 정지만 있을때 사용!
        val random = Random()
        val num = random.nextInt(1001) //0~1000까지 랜덤한 숫자를 뽑아주는 코드
        var people=p
        tv4.text = "참가자 $k"

        fun start(){

            timerTask=kotlin.concurrent.timer(period = 10){

                time++

                var mill = time
                runOnUiThread{
                    if(isBlind==false ){
                        tv1.text ="???"

                    }else if(isBlind==true){
                        tv1.text = (mill.toFloat()/100).toString()
                    }

                }

            }
        }//end start()

        fun stop(){
            timerTask?.cancel()

        }//end stop()

        //밖에다가 해야지 유지된다! 버튼 안쪽으로 넣어놓으면 버튼을 눌렀을때만 적용되기 때문에 다시 1로 돌아온다!
        btn.text = "시작"
        btn.setOnClickListener() {

            stage++


            if(stage ==2){

                tv2.text=((num.toFloat())/100).toString()
                start()


                btn.text = "정지"

            }else if(stage ==3){
                stop()
                if(k<people){
                    btn.text = "초기화"
                }else{
                    btn.text = "다음"

                }


                var point = abs(time-num).toFloat()/100
                tv3.text=point.toString()
                pointlist.add(point)
                stage=0 // 늘어났던 stage를 0으로 만들어 다시 처음부터 늘어나게 해줘야 반복 가능
            }else if(stage ==1){
                if(k<people){
                    k++
                    main()
                }else{
                    println(pointlist)

                    end()
                }

            }
        }//end btn

        btn_return.setOnClickListener(){
            isBlind==true
            pointlist.clear() // 배열 초기화! .clear
            p=0
            k=1
            startMain()
        }

    }//end main

    fun end(){
        setContentView(R.layout.activity_end)
        val text_last : TextView = findViewById(R.id.text_last)
        var text_people : TextView = findViewById(R.id.text_people)
        var text_score : TextView = findViewById(R.id.text_score)
        val btn_init: Button = findViewById(R.id.btn_init)

        k=pointlist.indexOf(pointlist.maxOrNull())+1 //indexOf-> 배열에서 원하는 인덱스값 나오게!
        text_people.text = "참가자 "+(k)
        text_score.text =pointlist.maxOrNull().toString()//list에서 최대값 나오게


        btn_init.setOnClickListener(){
            isBlind==true
            pointlist.clear() // 배열 초기화! .clear
            p=0

            startMain()

        }

    }//end end()


}//end Class