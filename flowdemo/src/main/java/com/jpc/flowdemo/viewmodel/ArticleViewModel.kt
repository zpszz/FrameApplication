package com.jpc.flowdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jpc.flowdemo.model.Article
import com.jpc.flowdemo.paging.ArticlePagingSource
import kotlinx.coroutines.flow.Flow

class ArticleViewModel: ViewModel(){
    // lazy 修饰符表示 articles 变量的延迟初始化，只有在第一次使用 articles 变量时才会初始化
    private val articles by lazy {
        Pager(
            config = PagingConfig(
                pageSize = 8
            ),
            pagingSourceFactory = { ArticlePagingSource() }
        ).flow.cachedIn(viewModelScope) // cachedIn 函数的作用是将数据缓存到 ViewModel 中
    }
    fun loadArticle(): Flow<PagingData<Article>> = articles
}