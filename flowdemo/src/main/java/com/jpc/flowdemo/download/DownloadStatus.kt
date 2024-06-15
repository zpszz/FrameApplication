package com.jpc.flowdemo.download

import java.io.File

/**
 * 下载状态的密封类
 * 使用密封类，可以限制类的继承，保证所有的下载状态都在这里定义
 */
sealed class DownloadStatus {
    data object None: DownloadStatus()
    data class Progress(val value: Int): DownloadStatus()
    data class Error(val throwable: Throwable): DownloadStatus()
    data class Done(val file: File): DownloadStatus()
}