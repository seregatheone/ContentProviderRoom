package com.example.a15.fragments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a15.R
import com.example.a15.models.Contact

class RecViewAdapter : RecyclerView.Adapter<RecViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    private var contactList : List<Contact> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = contactList[position]
        val listHolder = holder.itemView

        //note
        val note = listHolder.findViewById<TextView>(R.id.note)
        note.text = currentItem.note

        //note
        val id = listHolder.findViewById<TextView>(R.id.id)
        id.text = currentItem.id.toString()

        //note
        val name = listHolder.findViewById<TextView>(R.id.name)
        name.text = currentItem.name
    }

    override fun getItemCount(): Int = contactList.size


    @SuppressLint("NotifyDataSetChanged")
    fun setContactListData(list: List<Contact>){
        contactList = list
        notifyDataSetChanged()
    }

}