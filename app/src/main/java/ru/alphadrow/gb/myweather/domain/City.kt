package ru.alphadrow.gb.myweather.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class City(val name:String, val lat:Double, val lon:Double):Parcelable{
}
