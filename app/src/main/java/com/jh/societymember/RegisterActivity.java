package com.jh.societymember;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {


    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증처리
    private DatabaseReference mDatabaseRef; //  실시간 데이터 베이스! 기존의db개념
    private EditText mEtEmail, metPwd, metName, metAge;  //회원가입 입력필드
    private RadioButton rbtn_man, rbtn_woman, rbtn_teamleader, rbtn_teammate;
    private RadioGroup radioGroup, radioGroup2;
    private Button mBtnRegister;        // 회원가입 버튼
    private String Man, member;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth =FirebaseAuth.getInstance();
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("SocietyMember"); //두개로 파이어 베이스와 연결

        mEtEmail=findViewById(R.id.et_email);
        metPwd=findViewById(R.id.et_pwd);
        metName=findViewById(R.id.et_name);
        metAge=findViewById(R.id.et_age);
        rbtn_man=(RadioButton) findViewById(R.id.rbtn_man);
        rbtn_woman=(RadioButton)findViewById(R.id.rbtn_woman);
        rbtn_teamleader=(RadioButton)findViewById(R.id.rbtn_teamleader);
        rbtn_teammate=(RadioButton)findViewById(R.id.rbtn_teammate);
        radioGroup=(RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rbtn_man){
                    Man = (String) rbtn_man.getText().toString();
                }else if (checkedId==R.id.rbtn_woman){
                    Man = (String) rbtn_woman.getText().toString();
                }
            }
        });
        radioGroup2=(RadioGroup)findViewById(R.id.radioGroup2);
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId2) {
                if(checkedId2==R.id.rbtn_teamleader){
                    member = (String) rbtn_teamleader.getText().toString();
                }else if (checkedId2==R.id.rbtn_teammate){
                    member = (String) rbtn_teammate.getText().toString();
                }else if(checkedId2 != 0){
                    return;
                }
            }
        });


        mBtnRegister=findViewById(R.id.btn_register);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //회원가입 처리 시작
                String strEmail=mEtEmail.getText().toString();
                String strPwd= metPwd.getText().toString();
                String strName=metName.getText().toString();
                int strAge=Integer.parseInt(metAge.getText().toString());


                //Firebase Auth 진행
                mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    // 파이어베이스에서 유저를 생성한다! 이메일과패스워드를 이용해서 -> 이건 파이어베이스에서 유저를 생성시 기존의 등록한  id와 pw를 같게하는 거! 컬럼과 상관 없음!
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) { //가입이 성공되었을때 처리되는 메소드
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                            UserAccount account = new UserAccount();
                            account.setIdToken(firebaseUser.getUid()); //로그인을 하게 되면 자동으로 생성되면 고유 토큰
                            account.setEmailId(firebaseUser.getEmail()); // 데이터 베이스에 입력된 것 가져옴
                            account.setPassword(strPwd); // 사용자가 입력한 값을 가지고 옴
                            account.setName(strName);
                            account.setGender(Man);
                            account.setAge(strAge);// 그냥 컬럼을 늘리고 싶으면 account에다가 넣는 방식 사용! 알아서 늘어남!
                            account.setMember(member);

                            // setValue : database에 insert 하는 행위!
                            mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);
                            // account를 넣음으로써 account에 담아뒀던 정보들이 insert된다
                            Toast.makeText(RegisterActivity.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(RegisterActivity.this, "회원가입에 실패하셨습니다.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }
        });
    }
}