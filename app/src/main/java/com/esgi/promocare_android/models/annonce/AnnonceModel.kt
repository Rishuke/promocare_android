package com.esgi.promocare_android.models.annonce

import android.os.Parcel
import android.os.Parcelable

data class AnnonceModel(
    val uuid: String? = null,
    val companyId: String? = null,
    val location: String? = null,
    val price: Float? = null,
    val promo: Int? = null,
    val status: String? = null,
    val title: String? = null,
    val description:String? = null,
    val type: String? = null,
    val viewTime: Int? = null,
    val updatedAt: String? = null,
    val createdAt: String? = null,
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Float::class.java.classLoader) as? Float,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(uuid)
        parcel.writeString(companyId)
        parcel.writeString(location)
        parcel.writeValue(price)
        parcel.writeValue(promo)
        parcel.writeString(status)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(type)
        parcel.writeValue(viewTime)
        parcel.writeString(updatedAt)
        parcel.writeString(createdAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AnnonceModel> {
        override fun createFromParcel(parcel: Parcel): AnnonceModel {
            return AnnonceModel(parcel)
        }

        override fun newArray(size: Int): Array<AnnonceModel?> {
            return arrayOfNulls(size)
        }
    }
}
