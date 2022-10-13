package com.jh.ch10_notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.jh.ch10_notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding= ActivityMainBinding.inflate(layoutInflater) //뷰 바인딩 기법일 경우
        setContentView(binding.root)
        binding.notificationButton.setOnClickListener{
            val manager =getSystemService(NOTIFICATION_SERVICE)
                as NotificationManager
            val builder :NotificationCompat.Builder
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                //26버젼 이상
                val channelId="one-channel"
                val channelName="My Channel One"
                val channel=NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    //채널의 다양한 정보 설정
                    description="MY Channel one Description"
                    setShowBadge(true)
                    val uri: Uri =RingtoneManager
                        .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                    val audioAttributes=AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .setUsage(AudioAttributes.USAGE_ALARM)
                        .build()
                    setSound(uri,audioAttributes)
                    enableVibration(true)
                }
                //채널을 NotificationManager에 등록
                manager.createNotificationChannel(channel)
                //채널을 이용하여 builder 생성
                builder=NotificationCompat.Builder(this, channelId)
        }else{
            builder =NotificationCompat.Builder(this)
        }
            builder.run {
                setSmallIcon(R.drawable.small)
                setWhen(System.currentTimeMillis())
                setContentTitle("홍길동")
                setContentText("안녕하세요")
                setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.big))
            }
            val KEY_TEXT_REPLY = "key_text_reply"
            var replyLabel: String ="답장"
            val remoteInput: RemoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run {
                setLabel(replyLabel)
                build()
            }
            val replyIntent = Intent(this, ReplyReceiver::class.java)
            val replyPendingIntent = PendingIntent.getBroadcast(
                this, 30, replyIntent, PendingIntent.FLAG_MUTABLE)
            builder.addAction(
                NotificationCompat.Action.Builder(
                    R.drawable.send,
                    "답장",
                    replyPendingIntent
                ).addRemoteInput(remoteInput).build()
            )
            manager.notify(11, builder.build())
        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuItem1: MenuItem? =menu?.add(0,0,0,"menu1")
        val menuItem2: MenuItem? =menu?.add(0,1,0,"menu2")
        return super.onCreateOptionsMenu(menu)
    }
}

