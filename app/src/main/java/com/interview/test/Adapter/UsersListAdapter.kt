package com.interview.test.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.interview.test.Models.Users
import com.interview.test.databinding.UsersLayoutBinding

class UsersListAdapter(private val context: Context): PagingDataAdapter<Users, UsersListAdapter.MyViewHolder>(DataDifferntiator) {


    override fun onBindViewHolder(holder: UsersListAdapter.MyViewHolder, position: Int) {
        val datas=getItem(position)
        with(holder){
            binding.firstname.text=datas?.first_name
            binding.lastname.text=datas?.last_name
            binding.fulltname.text= {"${datas?.first_name} ${datas?.last_name}"}.toString()
            binding.email.text=datas?.email
            binding.itemimg.load(datas?.avatar)
        }

//        holder.txthq.text=getItem(position)?.airline?.head_quaters
//        holder.txttrips.text="${getItem(position)?.name}, ${getItem(position)?.trips} Trips"
//
//        holder.imglogo.load(getItem(position)?.airline?.logo)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListAdapter.MyViewHolder {
        val binding= UsersLayoutBinding.inflate(LayoutInflater.from(context),parent,false)

        return MyViewHolder(binding)
    }
    class MyViewHolder(val binding:UsersLayoutBinding ):RecyclerView.ViewHolder(binding.root) {


    }



    object DataDifferntiator : DiffUtil.ItemCallback<Users>() {

        override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean {
            return oldItem.id== newItem.id
        }

        override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean {
            return oldItem == newItem
        }
    }
}