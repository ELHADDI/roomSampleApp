package com.example.roomapp.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.ListFragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.model.User
import kotlinx.android.synthetic.main.rv_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_row,parent,false))
    }

    override fun getItemCount(): Int {
        return  userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.tv_id.text = currentItem.id.toString()
        holder.itemView.tv_first_name.text = currentItem.first_name
        holder.itemView.tv_last_name.text = currentItem.last_name
        holder.itemView.tv_age.text=currentItem.age.toString()

        holder.itemView.rv_row_item_container.setOnClickListener {
            val bundle = bundleOf("id" to currentItem.id.toString())
            holder.itemView.findNavController().navigate(R.id.action_listFragment_to_updateFragment,bundle)
        }
    }

    fun setDataList(users: List<User>) {
        userList = users
        notifyDataSetChanged()
    }


}