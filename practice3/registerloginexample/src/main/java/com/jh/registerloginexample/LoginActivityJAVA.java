package com.jh.registerloginexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivityJAVA extends AppCompatActivity {

    private EditText et_id, et_pass;
    private Button btn_login, btn_register;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id=findViewById(R.id.et_id);
        et_pass=findViewById(R.id.et_pass);
        btn_login=findViewById(R.id.btn_login);
        btn_register=findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LoginActivityJAVA.this, RegisterActivityJAVA.class);
                // intent하면 화면을 넘어가게 하는 것!
                // ex) Intent intent= new Intent(현재 클래스명.this, 넘어갈 클래스명.class);
                startActivity(intent);

            }
        });//end btn_register

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText에 현재 입력되어있는 값을 get(가져온다) 해온다.
                String userID= et_id.getText().toString();
                String userPassword= et_pass.getText().toString();

                Response.Listener<String>responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success= jsonObject.getBoolean("success");
                            if(success){//회원 등록에 성공한 경우
                                String userID= jsonObject.getString("userID");
                                String userPassword= jsonObject.getString("userPassword");
                                Toast.makeText(getApplicationContext(),"로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivityJAVA.this, MainActivityJAVA.class);
                                intent.putExtra("userId",userID);
                                intent.putExtra("userPassword",userPassword); //intent에 담는 과정! 담아서 보내는 과정.
                                startActivity(intent);
                            }else{//회원 등록에 실패한 경우
                                Toast.makeText(getApplicationContext(),"로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };//end Response.Listener

                //loginphp에 담아서 연결하는 과정 / volley에 담아서 서버에 요청
                LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener);
                RequestQueue queue= Volley.newRequestQueue(LoginActivityJAVA.this);
                queue.add(loginRequest);


            }
        });//end btn_login

    }

}
