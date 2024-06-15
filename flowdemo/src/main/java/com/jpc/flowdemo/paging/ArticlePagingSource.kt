package com.jpc.flowdemo.paging

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jpc.flowdemo.model.Article
import com.jpc.flowdemo.network.ArticleService
import com.jpc.flowdemo.network.RetrofitClient
import kotlinx.coroutines.delay

class ArticlePagingSource: PagingSource<Int, Article>(){
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        // 获取最近访问的位置
        val anchorPosition = state.anchorPosition

        // 获取最近访问位置所在的页面
        val closestPage = anchorPosition?.let { state.closestPageToPosition(it) }

        // 如果存在前一页，返回前一页的键；否则返回 null
        return closestPage?.prevKey
    }

    /**
     *  prevKey    currentPage    nextKey
     *  null          1             2
     *  1              2            3
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        //delay(2000)
        val currentPage: Int = params.key ?: 0  // 与服务端约定好页码从0开始
        // val pageSize = params.loadSize
        val articleResponse = RetrofitClient.createApi(ArticleService::class.java).getArticles(currentPage)
        val prevKey = if(currentPage == 0) null else currentPage - 1
        val nextKey = if(articleResponse.data.over) null else currentPage + 1 // over为true表示没有数据了
        Log.d("ArticlePagingSource", "prevKey=${prevKey},currentPage=${currentPage}, nextKey=${nextKey}")
        return try {
            LoadResult.Page(
                data = articleResponse.data.datas,
                prevKey = prevKey,
                nextKey = nextKey
            )
        }catch(e: Exception) {
            e.printStackTrace()
            return LoadResult.Error(e)
        }
    }
}