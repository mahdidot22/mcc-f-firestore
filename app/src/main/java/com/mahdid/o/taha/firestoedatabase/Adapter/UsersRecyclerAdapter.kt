package com.mahdid.o.taha.firestoedatabase.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahdid.o.taha.firestoedatabase.R
import com.mahdid.o.taha.firestoedatabase.model.Users
import kotlinx.android.synthetic.main.user_row_item.view.*

class UsersRecyclerAdapter(var context: Context, var list: ArrayList<Users>) :
    RecyclerView.Adapter<UsersRecyclerAdapter.usersViewHolder>() {
    class usersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.tv_username
        var uid = itemView.tv_number
        var address = itemView.tv_address
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): usersViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.user_row_item, parent, false)
        return usersViewHolder(view)
    }

    override fun onBindViewHolder(holder: usersViewHolder, position: Int) {
        holder.name.text = list[position].username
        holder.uid.text = list[position].uid
        holder.address.text = list[position].address
    }

    override fun getItemCount(): Int {
        return list.size
    }
}