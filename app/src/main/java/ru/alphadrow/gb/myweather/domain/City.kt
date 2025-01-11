package ru.alphadrow.gb.myweather.domain

import android.os.Parcel
import android.os.Parcelable

data class City(val name:String, val lat:Double, val lon:Double):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(), //почему String vs String? ?
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeDouble(lat)
        parcel.writeDouble(lon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<City> {
        override fun createFromParcel(parcel: Parcel): City {
            return City(parcel)
        }

        override fun newArray(size: Int): Array<City?> {
            return arrayOfNulls(size)
        }
    }
}
