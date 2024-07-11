package com.esgi.promocare_android.data


import com.esgi.promocare_android.network.ApiRepository
import com.esgi.promocare_android.network.ApiService
import com.esgi.promocare_android.viewmodels.TodoViewModel
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataLayerSingleton {
        private lateinit var retrofitClient: Retrofit
        private lateinit var apiService: ApiService
        private lateinit var todoViewModel: TodoViewModel


        fun createRetrofitClient() {
                val gsonConverter =
                        GsonConverterFactory.create(
                                GsonBuilder().create()
                        )
                retrofitClient = Retrofit.Builder()
                        .baseUrl("https://my-json-server.typicode.com/")
                        .addConverterFactory(gsonConverter)
                        .build()
        }

        fun createTodoService() {
                apiService  = retrofitClient.create(ApiService::class.java)
        }

        fun initViewModel(){
                todoViewModel = TodoViewModel(
                        ApiRepository(
                                apiService
                        )
                )

        }

}