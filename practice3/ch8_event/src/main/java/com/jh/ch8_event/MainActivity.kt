package com.jh.ch8_event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.ClickableSpan
import android.widget.Button
import android.widget.TextView
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        main()
    }
        fun main() {

            val btn_start: Button = findViewById(R.id.btn_start)
            val btn_stop: Button = findViewById(R.id.btn_stop)
            val btn_reset: Button = findViewById(R.id.btn_reset)
            var tv_time: TextView = findViewById(R.id.tv_time)
            var timerTask: Timer? = null;

            var time = 0

            btn_start.setOnClickListener() {

                timerTask = kotlin.concurrent.timer(period = 10) {

                    time++
                    var mill = time
                    runOnUiThread() {
                        tv_time.text = (mill.toFloat() / 100).toString()
                    }
                }

            }//end start

            btn_stop.setOnClickListener(){
                timerTask?.cancel()
            }

            btn_reset.setOnClickListener(){
                if(timerTask?.cancel().toString()=="false"){

                }else{
                    time=0
                    tv_time.text=time.toString()
                }

            }

        }//end main

}//emd class