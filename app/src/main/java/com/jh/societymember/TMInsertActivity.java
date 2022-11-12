package com.jh.societymember;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TMInsertActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseRef;
    private TextView textView;
    private EditText editText1, editText2;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teammate2);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("SocietyMember");


        textView = (TextView) findViewById(R.id.textView);
        editText1 = (EditText) findViewById(R.id.name);
        editText2 = (EditText) findViewById(R.id.age);
        button = (Button) findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=editText1.getText().toString();
                int age= Integer.parseInt(editText2.getText().toString());

                FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                TMInsertAccount TIMaccount = new TMInsertAccount();
                TIMaccount.setName(name);
                TIMaccount.setAge(age);

                mDatabaseRef.child("TMInsertAccount").child(firebaseUser.getUid()).setValue(TIMaccount);

                Toast.makeText(TMInsertActivity.this, "팀원 등록에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TMInsertActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }

}
