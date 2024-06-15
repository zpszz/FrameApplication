package com.jpc.flowdemo.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class NumberViewModel(application: Application): AndroidViewModel(application){
    val number = MutableStateFlow(0) // 给个初始值

    fun increment(){
        number.value++
    }
    fun decrement(){
        number.value--
    }
}