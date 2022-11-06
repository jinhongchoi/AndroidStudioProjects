package com.jh.societymember;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TReadActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;
    private DatabaseReference mDatabaseRef; //  실시간 데이터 베이스! 기존의db개념
    private TextView textView, tv_leader, tv_sports, tv_region, tv_teamname ;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;

    private ListView listView;
    private ArrayAdapter<String> adapter2;
    List<Object> Array = new ArrayList<Object>();

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_teamread);
//
//        recyclerView = (RecyclerView) findViewById(R.id.recyceler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
//
//        adapter= new Adapter();
//        for(int i=0; i<100; i++){
//            String str = i+"번째 아이템";
//            adapter.setArrayData(str);
//        }
//
//        recyclerView.setAdapter(adapter);
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamread2);

        listView = (ListView) findViewById(R.id.listviewmsg);

//        initDatabase(); //미리 생성해둠 ! 새로 만들 함수!

        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<String>());
        listView.setAdapter(adapter2);


        mDatabaseRef= FirebaseDatabase.getInstance().getReference("SocietyMember").child("TInsertAccount");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adapter2.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    TReadAccount tReadAccount = dataSnapshot.getValue(TReadAccount.class);
                    Array.add(tReadAccount);
                    adapter2.add(String.valueOf(tReadAccount));
                }
                adapter2.notifyDataSetChanged();
                listView.setSelection(adapter2.getCount()-1);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
//
//    private void initDatabase() {
//
//        mDatabase = FirebaseDatabase.getInstance();
//
//        mReference = mDatabase.getReference("SocietyMember");
//        mReference.child("SocietyMember").setValue("check");
//
//
//        mChild = new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                // onChildAdded -> 항목목록을 검색하거나 항목 목록에 대한 추가를 수신 대기
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                //onChildChanged -> 목록의 항목에 대한 변경을 수신
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//                // onChildRemoved -> 목록의 항목 삭제를 수신 대기
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                // onChildMoved -> 순서 있는 목록의 항목 순서 변경을 수신 대기
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        };
//        mReference.addChildEventListener(mChild);
//
//    }//end initData
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mReference.removeEventListener(mChild);
//    }//on destroy


}

