package com.jpc.flowdemo.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jpc.flowdemo.model.Place
import com.jpc.flowdemo.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class PlaceViewModel(app: Application): AndroidViewModel(app) {
    val places = MutableLiveData<List<Place>>()
    fun searchPlaces(name: String){
        viewModelScope.launch {
            flow{
                val list = RetrofitClient.placeApi.searchPlaces(name)
                //Log.d("PlaceViewModel", list.places.size.toString())
                emit(list)
            }.flowOn(Dispatchers.IO)
                .catch { e -> e.printStackTrace() }
                .collect{
                    places.value = it.places
                    //Log.d("PlaceViewModel", places.value!!.size.toString())
                }
        }
    }
}