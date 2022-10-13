package com.jh.hongdroid10_recyclerviewkt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ProfileAdapter (val profileList: ArrayList<Profiles>): RecyclerView.Adapter<ProfileAdapter.CustomViewHolder>()//  : 콜론은 자바에서 상속 받는 개념이랑 같다
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder { // 연결될 화면 열기
        val view=LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)//inflate() 붙이는 거
         //list_item 의 정보들을 끌고와서 view에 담은거!
        return CustomViewHolder(view).apply {
            itemView.setOnClickListener { //리사이클릭 뷰 클릭했을때의 이벤트 작성
                var curPos : Int = adapterPosition // curPos 현재 클릭한 포지션을 의미
                var profile : Profiles = profileList.get(curPos)
                Toast.makeText(parent.context,"이름 : ${profile.name} \n나이 : ${profile.age} \n직업 :${profile.job}",Toast.LENGTH_SHORT ).show()//parent.context -> adapter 랑 연결된 파일을 의미

            }

        } //아랫쪽에 CustomViewHolder 쪽에view가 위에 view가 들어간다 매개변수 개념으로
    }

    override fun getItemCount(): Int {
        return profileList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.gender.setImageResource(profileList.get(position).gender) // 현재 클릭한 위치 및 선택된 위치가 가져와짐!(position -> 0번째부터 시작됨)
        holder.name.text = profileList.get(position).name
        holder.age.text =profileList.get(position).age.toString() //age 가 int형태이기때문에 toString을 통해 문자자체를 출력하게 함!
        holder.job.text =profileList.get(position).job
        //문자를 출력하는 거 완료!
    }

    class CustomViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){ //클래스 안에 클래스 -> 내부 클래스
        val gender = itemView.findViewById<ImageView>(R.id.iv_profile) //성별
        val name = itemView.findViewById<TextView>(R.id.tv_name)// 이름
        val age = itemView.findViewById<TextView>(R.id.tv_age)// 나이
        val job = itemView.findViewById<TextView>(R.id.tv_job)// 직업
    }


}
