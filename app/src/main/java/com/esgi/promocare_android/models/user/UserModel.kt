package com.esgi.promocare_android.models.user

import android.os.Parcel
import android.os.Parcelable

data class UserModel(
    val created_at: String?,
    val email: String?,
    val first_name: String?,
    val last_name: String?,
    val phone: String?,
    val role: String?,
    val updated_at: String?,
    val uuid: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
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
        parcel.writeString(created_at)
        parcel.writeString(email)
        parcel.writeString(first_name)
        parcel.writeString(last_name)
        parcel.writeString(phone)
        parcel.writeString(role)
        parcel.writeString(updated_at)
        parcel.writeString(uuid)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }
}