package com.jpc.flowdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.jpc.flowdemo.databinding.ItemArticleBinding
import com.jpc.flowdemo.model.Article

class ArticleAdapter(private val context: Context): 
    PagingDataAdapter<Article, BindingViewHolder>(object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }){
    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val article = getItem(position)
        article?.let {
            val binding = holder.binding as ItemArticleBinding
            binding.article = it
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(context), parent, false)
        return BindingViewHolder(binding)
    }
}