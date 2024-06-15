package com.jpc.flowdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpc.flowdemo.common.Event
import com.jpc.flowdemo.common.LocalEventBus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SharedFlowViewModel: ViewModel(){
    private lateinit var job: Job

    fun startRefresh(){
        job = viewModelScope.launch(Dispatchers.IO) {
            while (true){
                LocalEventBus.postEvent(Event(System.currentTimeMillis()))
            }
        }
    }

    fun finishRefresh(){
        job.cancel() // 取消协程任务
    }
}