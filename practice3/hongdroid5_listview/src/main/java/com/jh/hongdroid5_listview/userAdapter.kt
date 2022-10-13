package com.jh.hongdroid5_listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class UserAdapter(val context: Context, val UserList: ArrayList<User>) : BaseAdapter()
//ArrayList<User> ->배열 형태는 <> 대괄호 안에 처럼 담아라!의 의미! 그럼 User형태로 담아라 라는 의미!
// : BaseAdapter() -> BaseAdapter() 에서 상속을 받는 다는 의미!
{
    override fun getCount(): Int {
        return UserList.size
    }

    override fun getItem(position: Int): Any {
        return UserList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
       val view: View=LayoutInflater.from(context).inflate(R.layout.list_item_user,null)

        val profile = view.findViewById<ImageView>(R.id.iv_profile)
        //findViewById -> id로부터 iv_profile 을 찾아라 ~ 라는 의미
        val name = view.findViewById<TextView>(R.id.tv_name)
        //<> 안에 들어가는 건 가져올 거의 형태!
        val age = view.findViewById<TextView>(R.id.tv_age)
        val greet = view.findViewById<TextView>(R.id.tv_greet)

        val user= UserList[position]

        profile.setImageResource(user.profile)
        name.text=user.name
        age.text=user.age
        greet.text=user.greet

        return view
    }

}

// ArrayList 는 adapter가 있어야 하고 이렇게 작성한걸 지속적으로 사용하고 mainacticity에서 사용!