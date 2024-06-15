package com.jpc.flowdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.jpc.flowdemo.databinding.LoadMoreBinding

class ArticleLoadMoreAdapter(private val context: Context): LoadStateAdapter<BindingViewHolder>(){
    override fun onBindViewHolder(holder: BindingViewHolder, loadState: LoadState) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): BindingViewHolder {
        val binding = LoadMoreBinding.inflate(LayoutInflater.from(context), parent, false)
        return BindingViewHolder(binding)
    }
}