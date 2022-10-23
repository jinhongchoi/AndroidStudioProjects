package com.jh.firebaselistexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<User>arrayList;
    private Context context;

    public CustomAdapter(ArrayList<User> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // CustomViewHolder -> 우리가 만든 layout을 연결해주는 역할
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder holder= new CustomViewHolder(view);
        return holder;

    }//end CustomViewHolder

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        // main 에서 받아온 값을 User.java 에 담아서 onbindviewholder 로 뿌려주고 glind에 가서 load를 하는 구조
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfile())
                .into(holder.iv_profile); //여기까지 해야 들어간다
        holder.tv_id.setText(arrayList.get(position).getId());
        holder.tv_pw.setText(String.valueOf(arrayList.get(position).getPw()));
        holder.tv_userName.setText(arrayList.get(position).getUserName());

    }

    @Override
    public int getItemCount() {
        // 삼항 연산자
        return (arrayList !=null ? arrayList.size() :0);
        // arrayList !=null  참 일경우  arrayList.size()(왼쪽) 실행 / 거짓일 경우 0(오른쪽) 실행
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_profile;
        TextView tv_id;
        TextView tv_pw;
        TextView tv_userName;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView); //super 상속 받은거
            this.iv_profile= itemView.findViewById(R.id.iv_profile);
            this.tv_id= itemView.findViewById(R.id.tv_id);
            this.tv_pw= itemView.findViewById(R.id.tv_pw);
            this.tv_userName= itemView.findViewById(R.id.tv_userName);
        }
    }//end CustomViewHolder
}
