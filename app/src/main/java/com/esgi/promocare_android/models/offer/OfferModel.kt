package com.esgi.promocare_android.models.offer

import android.os.Parcel
import android.os.Parcelable

data class OfferModel(
    val uuid: String?,
    val status: String?,
    val annonce_id: String?,
    val user_id: String?,
    val text: String?,
    val updated_at: String?,
    val created_at: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(uuid)
        parcel.writeString(status)
        parcel.writeString(annonce_id)
        parcel.writeString(user_id)
        parcel.writeString(text)
        parcel.writeString(updated_at)
        parcel.writeString(created_at)
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
