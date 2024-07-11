package com.esgi.promocare_android.model

import android.os.Parcel
import android.os.Parcelable

data class LoginRequest (
    private var email: String?,
    private var password: String?,
): Parcelable {
    constructor(parcel: Parcel): this(
        parcel.readString(),
        parcel.readString(),
    ){
    }

    override fun describeContents(): Int {
        return 0;
    }

    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeString(email)
        parcel.writeString(password)

    }

    companion object CREATOR : Parcelable.Creator<LoginRequest> {
        override fun createFromParcel(parcel: Parcel): LoginRequest {
            return LoginRequest(parcel)
        }

        override fun newArray(size: Int): Array<LoginRequest?> {
            return arrayOf()
        }
    }

}