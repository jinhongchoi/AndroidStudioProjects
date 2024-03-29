package com.jh.societymember;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증처리
    private DatabaseReference mDatabaseRef; //  실시간 데이터 베이스! 기존의db개념
    private EditText mEtEmail, metPwd;  //로그인 입력필드

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth =FirebaseAuth.getInstance();
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("SocietyMember");

        mEtEmail=findViewById(R.id.et_email);
        metPwd=findViewById(R.id.et_pwd);

        Button btn_login=findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //로그인 요청하는 버튼
                String strEmail=mEtEmail.getText().toString();
                String strPwd= metPwd.getText().toString();

                mFirebaseAuth.signInWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //로그인 성공!
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            //this = getBaseContext() = Activity Context
                            //getApplicationContext() = getApplication() = Application Context
                            // 위와 같이 this대신 getBaseContext()를 사용하기도 한다.
                            intent.putExtra("strEmail", strEmail);
                            intent.putExtra("strPwd", strPwd);
                            // 다른 액티비티로 넘길때 intent에 담아서 넘긴다! 집어 넣을때는 위와 같이 putExtra 사용함!

                            startActivity(intent);
                            finish(); //현재 액티비티 파괴! (로그인이 완료되면 로그인을 다시할일이 없기 때문)
                        }else{
                            Toast.makeText(LoginActivity.this, "로그인 실패!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        Button btn_register=findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            //new View.OnClickListener() 회원가입버튼을 눌렀을때 일어날때 집어 넣어줌
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
               // (LoginActivity.this, RegisterActivity.class); -> (현재 엑티비티, 옮겨갈 엑티비티)
                startActivity(intent);
            }
        });

    }
}