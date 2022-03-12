package com.example.a15.fragments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.a15.R
import com.example.a15.data.models.Contact

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

        //id
        val id = listHolder.findViewById<TextView>(R.id.id)
//        id.text = currentItem.id.toString()
        id.text = position.toString()

        //name
        val name = listHolder.findViewById<TextView>(R.id.name)
        name.text = currentItem.name

        //pass data between start and change fragments
        holder.itemView.findViewById<ConstraintLayout>(R.id.rowLayout).setOnClickListener {
            val action = StartFragmentDirections.actionStartFragmentToChangeContatcFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = contactList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setContactListData(list: List<Contact>){
        contactList = list
        notifyDataSetChanged()
    }

}