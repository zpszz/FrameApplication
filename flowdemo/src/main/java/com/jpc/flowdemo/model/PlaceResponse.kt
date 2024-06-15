package com.jpc.flowdemo.model

import com.google.gson.annotations.SerializedName

data class PlaceResponse(val status: String, val places: List<Place>) {
}
// @SerializedName 注解用于将Kotlin变量名与JSON键对应，因为键可能不符合Kotlin的命名规范
data class Place(val name: String, val location: Location, @SerializedName("formatted_address") val address: String)

data class Location(val lng: String, val lat: String)