package com.jh.societymember;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jh.societymember.R;



public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;
    private Button button;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        textView= itemView.findViewById(R.id.textView);
        button = itemView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strText=textView.getText().toString();
                Toast.makeText(button.getContext(), strText, Toast.LENGTH_SHORT).show();
            }
        });


    }


}
