package com.esgi.promocare_android.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    private var retrofitClient: Retrofit = createRetrofitClient()

    private fun createRetrofitClient(): Retrofit {
        val gsonConverter =
            GsonConverterFactory.create(
                GsonBuilder().create()
            )
        val retrofitClient = Retrofit.Builder()
            .baseUrl("https://helpother.fr/")
            .addConverterFactory(gsonConverter)
            .build()

        return retrofitClient
    }

    fun getRetrofitClient() = retrofitClient
}