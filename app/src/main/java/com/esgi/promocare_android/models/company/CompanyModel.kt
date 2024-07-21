package com.esgi.promocare_android.models.company

import android.os.Parcel
import android.os.Parcelable

data class CompanyModel(
    val company_name: String?,
    val created_at: String?,
    val email: String?,
    val location: String?,
    val phone: String?,
    val role: String?,
    val siret_number: String?,
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
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(company_name)
        parcel.writeString(created_at)
        parcel.writeString(email)
        parcel.writeString(location)
        parcel.writeString(phone)
        parcel.writeString(role)
        parcel.writeString(siret_number)
        parcel.writeString(updated_at)
        parcel.writeString(uuid)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CompanyModel> {
        override fun createFromParcel(parcel: Parcel): CompanyModel {
            return CompanyModel(parcel)
        }

        override fun newArray(size: Int): Array<CompanyModel?> {
            return arrayOfNulls(size)
        }
    }
}