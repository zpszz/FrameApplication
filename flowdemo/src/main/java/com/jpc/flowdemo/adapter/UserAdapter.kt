package com.jpc.flowdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jpc.flowdemo.databinding.ItemUserBinding
import com.jpc.flowdemo.db.User

class UserAdapter(private val context: Context): RecyclerView.Adapter<BindingViewHolder>() {
    private val data = ArrayList<User>()
    fun setData(data: List<User>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(context), parent, false)
        return BindingViewHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val item = data[position]
        val binding = holder.binding as ItemUserBinding
        binding.tvUser.text = "${item.id}, ${item.name}, ${item.className}"
    }
}