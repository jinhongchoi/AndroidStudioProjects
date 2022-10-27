package com.jh.societymember;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TInsertActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증처리
    private DatabaseReference mDatabaseRef; //  실시간 데이터 베이스! 기존의db개념
    private EditText mEtEmail, metPwd, et_teamname, et_region, et_leader;  //로그인 입력필드
    private RadioGroup radioGroup;
    private RadioButton rbtn_soccer, rbtn_basketball;
    private Button mBtnRegister;
    private String Sports;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teaminsert);

        mFirebaseAuth =FirebaseAuth.getInstance();
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("SocietyMember");

        mEtEmail=findViewById(R.id.et_email);
        metPwd=findViewById(R.id.et_pwd);

        et_teamname=findViewById(R.id.et_teamname);
        et_region=findViewById(R.id.et_region);
        et_leader=findViewById(R.id.et_leader);

        rbtn_soccer=(RadioButton) findViewById(R.id.rbtn_soccer);
        rbtn_basketball=(RadioButton)findViewById(R.id.rbtn_basketball);

        radioGroup=(RadioGroup) findViewById(R.id.radioGroup);
         radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rbtn_soccer){
                    Sports = (String) rbtn_soccer.getText().toString();
                }else if (checkedId==R.id.rbtn_basketball){
                    Sports = (String) rbtn_basketball.getText().toString();
                }
            }
        });


        Button btn_teaminsert=findViewById(R.id.btn_teaminsert);
        btn_teaminsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //회원가입 처리 시작
                String strEmail=mEtEmail.getText().toString();
                String strPwd= metPwd.getText().toString();
                //파이어 베이스에 들어가는 아이디 비번 가져오는 것 부터
                
                
                String teamname=et_teamname.getText().toString();
                String region=et_region.getText().toString();
                String leader=et_leader.getText().toString();


                //Firebase Auth 진행
                mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(TInsertActivity.this, new OnCompleteListener<AuthResult>() {
                    // 파이어베이스에서 유저를 생성한다! 이메일과패스워드를 이용해서 -> 이건 파이어베이스에서 유저를 생성시 기존의 등록한  id와 pw를 같게하는 거! 컬럼과 상관 없음!
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) { //가입이 성공되었을때 처리되는 메소드
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                            TInsertAccount account = new TInsertAccount();
                            account.setTeamName(teamname);
                            account.setRegion(region);
                            account.setTeamName(teamname);
                            account.setSports(Sports);
                            account.setLeader(leader);

                            // setValue : database에 insert 하는 행위!
                            mDatabaseRef.child("TInsertAccount").child(firebaseUser.getUid()).setValue(account);
                            // account를 넣음으로써 account에 담아뒀던 정보들이 insert된다
                            Toast.makeText(TInsertActivity.this, "팀 등록에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(TInsertActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(TInsertActivity.this, "팀 등록에 실패하셨습니다.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }
        });

    }

}
