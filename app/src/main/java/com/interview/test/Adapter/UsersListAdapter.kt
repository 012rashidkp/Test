package com.interview.test.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.interview.test.Models.Users
import com.interview.test.ViewModel.UsersViewModel
import com.interview.test.databinding.UsersLayoutBinding

class UsersListAdapter(private val context: Context,var items:List<Users>):RecyclerView.Adapter<UsersListAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
      val binding=UsersLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
     val datas=items[position]
        with(holder){
            binding.firstname.text=datas.first_name
            binding.lastname.text=datas.last_name
            binding.fulltname.text= {"${datas.first_name} ${datas.last_name}"}.toString()
            binding.email.text=datas.email
            binding.itemimg.load(datas.avatar)
        }
    }

    override fun getItemCount(): Int {
       return items.size
    }
    class ItemViewHolder(val binding: UsersLayoutBinding):RecyclerView.ViewHolder(binding.root) {

    }
}