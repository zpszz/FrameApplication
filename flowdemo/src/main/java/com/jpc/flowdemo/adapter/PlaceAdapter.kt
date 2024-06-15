package com.jpc.flowdemo.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jpc.flowdemo.databinding.ItemPlaceBinding
import com.jpc.flowdemo.model.Place

class PlaceAdapter(private val context: Context): RecyclerView.Adapter<BindingViewHolder>(){
    private val data = ArrayList<Place>()
    fun setData(data: List<Place>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = ItemPlaceBinding.inflate(LayoutInflater.from(context), parent, false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val item = data[position]
        val binding = holder.binding as ItemPlaceBinding
        binding.apply {
            placeName.text = item.name
            placeAddress.text = item.address
        }
    }

    override fun getItemCount() = data.size
}