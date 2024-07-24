package com.esgi.promocare_android.models.offer

import android.os.Parcel
import android.os.Parcelable

data class OfferModel(
    val uuid: String?,
    val status: String?,
    val annonceId: String?,
    val userId: String?,
    val text: String?,
    val updatedAt: String?,
    val createdAt: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(uuid)
        parcel.writeString(status)
        parcel.writeString(annonceId)
        parcel.writeString(userId)
        parcel.writeString(text)
        parcel.writeString(updatedAt)
        parcel.writeString(createdAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OfferModel> {
        override fun createFromParcel(parcel: Parcel): OfferModel {
            return OfferModel(parcel)
        }

        override fun newArray(size: Int): Array<OfferModel?> {
            return arrayOfNulls(size)
        }
    }
}
