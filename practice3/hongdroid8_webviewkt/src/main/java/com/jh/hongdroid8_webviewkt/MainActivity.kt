package com.jh.hongdroid8_webviewkt

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val webView :WebView= findViewById(R.id.webView)

        webView.settings.javaScriptEnabled = true // 자바 스크립트 허용
        /* 웹뷰에서 새 창이 뜨지 않도록 방지하는 구문  -> 인터넷(새창)이 열리는게 아닌 액티비티(내부적) 안에서 열리게끔하는 것!*/
        webView.webViewClient = WebViewClient()
        webView.webChromeClient= WebChromeClient()
        /* 웹뷰에서 새 창이 뜨지 않도록 방지하는 구문  -> 새창이 뜨는건 디폴트로 설정하는 것!*/
        webView.loadUrl("https://www.naver.com") // 링크 주소를 load 함

    }

    override fun onBackPressed() {// 액티비티에서 뒤로가기 실행 가능하도록 하는 로직 구현
        val webView :WebView= findViewById(R.id.webView)
        if(webView.canGoBack()) { // 웹사이트에서 뒤로 갈 페이지가 존재 한다면..(액티비티 메인 안에서)
            webView.goBack() //웹사이트 뒤로가기
        }else{
            super.onBackPressed() //본래의 백버튼 기능 수행(안드로이드)
        }
    }//end onBackPressed()


}