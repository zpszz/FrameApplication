package com.jpc.glidedemo

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class MyAppGlideModule: AppGlideModule(){

//    override fun applyOptions(context: Context, builder: GlideBuilder) {
//        val memoryCacheSizeBytes: Long = 1024 * 1024 * 20; // 20mb
//        builder.setMemoryCache(LruResourceCache(memoryCacheSizeBytes))
//    }
}