package com.jh.registerloginexample;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
//이건 제자리에서 import 해주면 된다

public class LoginRequest extends StringRequest{

    //서버 URL 설정(PHP 파일 연동)
    final static private String URL ="http://jeans11119.dothome.co.kr/Login.php";
    private Map<String, String>map;

    public LoginRequest(String userID, String userPassword, Response.Listener<String>listener){
        super(Method.POST, URL, listener, null);
        map = new HashMap<>();
        map.put("userID",userID);
        map.put("userPassword",userPassword);

    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
