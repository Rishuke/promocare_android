package com.esgi.promocare_android.network

import com.esgi.promocare_android.model.LoginRequest
import com.esgi.promocare_android.model.LoginResponse
import retrofit2.Call

class ApiRepository(
    private val apiService: ApiService,
) {
    fun fetchApi(email: String, password: String): Call<LoginResponse?>? {
        val loginRequest = LoginRequest(email, password)
        return apiService.login(loginRequest)
    }
}
