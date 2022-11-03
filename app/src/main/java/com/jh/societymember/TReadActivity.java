package com.jh.societymember;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TReadActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamread);

        recyclerView = (RecyclerView) findViewById(R.id.recyceler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        adapter= new Adapter();
        for(int i=0; i<100; i++){
            String str = i+"번째 아이템";
            adapter.setArrayData(str);
        }

        recyclerView.setAdapter(adapter);

    }

}

