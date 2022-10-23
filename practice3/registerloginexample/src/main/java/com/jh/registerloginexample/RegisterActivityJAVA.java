package com.jh.registerloginexample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class RegisterActivityJAVA extends AppCompatActivity {

    private EditText et_id, et_pass, et_name, et_age;
    private Button btn_register;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //아이디 값 찾아주기
        et_id= findViewById(R.id.et_id);
        et_pass=findViewById(R.id.et_pass);
        et_name=findViewById(R.id.et_name);
        et_age=findViewById(R.id.et_age);

        btn_register= findViewById(R.id.btn_register);

        // 회원가입 버튼 클릭 시 수행
        btn_register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // EditText 에 현재 입력되어있는 값을 get 해온다
                String userID= et_id.getText().toString();
                String userPassword= et_pass.getText().toString();
                String userName= et_name.getText().toString();
                int userAge= Integer.parseInt(et_age.getText().toString());


                Log.v("tag", "성공1");

                Response.Listener<String>responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("tag", "성공2");
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success= jsonObject.getBoolean("success");
                            if(success){//회원 등록에 성공한 경우
                                Toast.makeText(getApplicationContext(),"회원 등록에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivityJAVA.this, LoginActivityJAVA.class);
                                Log.v("tag", "성공3");
                                startActivity(intent);
                            }else{//회원 등록에 실패한 경우
                                Toast.makeText(getApplicationContext(),"회원 등록에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                Log.v("tag","실패");
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                };//end Response,Listener


                // 서버로 Volley를 이용해서 요청을 함
                // registerphp에 담아서 연결하는 과정 / volley에 담아서 서버에 요청
                // Volley 는 자바에서 Http 같은 개념 원래 아파치 톰캣 써서 Http로 연결해주고 보내는 개념을을 여기선 Volley로 함!
                // 자세한건 더 찾아보기
                RegisterRequest registerRequest = new RegisterRequest(userID, userPassword, userName, userAge, responseListener);
                RequestQueue queue= Volley.newRequestQueue(RegisterActivityJAVA.this);
                queue.add(registerRequest);
            }
        });//end btn_register

    }
}












