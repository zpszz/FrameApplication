package com.jpc.glidedemo

import com.bumptech.glide.annotation.GlideExtension
import com.bumptech.glide.request.BaseRequestOptions

@GlideExtension
object MyGlideExtension {

    fun applyAvatar(requestOptions: BaseRequestOptions<*>, size: Int): BaseRequestOptions<*> {
        return requestOptions.placeholder(R.mipmap.ic_launcher) // 默认显示的图片
            .error(R.mipmap.ic_launcher) // 发生错误时显示的图片
            .override(size) // 指定图片的大小
            .centerCrop() // 居中裁剪
    }
}