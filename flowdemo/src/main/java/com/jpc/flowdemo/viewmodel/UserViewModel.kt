package com.jpc.flowdemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jpc.flowdemo.db.AppDatabase
import com.jpc.flowdemo.db.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class UserViewModel(app: Application): AndroidViewModel(app){
    fun insert(id: String, name: String, className: String){
        viewModelScope.launch {
            AppDatabase.getInstance(getApplication())
                .userDao()
                .insert(User(id.toInt(), name, className))
        }
    }

    fun getAllUser(): Flow<List<User>> {
        return AppDatabase.getInstance(getApplication())
            .userDao()
            .getAllUser()
            .catch { e -> e.printStackTrace() }
            .flowOn(Dispatchers.IO)
    }
}