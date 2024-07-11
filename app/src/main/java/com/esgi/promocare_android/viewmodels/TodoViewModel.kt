package com.esgi.promocare_android.viewmodels

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.esgi.promocare_android.model.LoginRequest
import com.esgi.promocare_android.model.LoginResponse
import com.esgi.promocare_android.network.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoViewModel(private val apiRepository: ApiRepository) : ViewModel() {
    var todos: MutableLiveData<ArrayList<LoginRequest>> = MutableLiveData()
    var todoListLoadedOnce = false

    fun fetchDataFromApi(email: String, password: String,activity: AppCompatActivity) {
        val todosApiResponse = apiRepository.fetchApi(email, password)
        todosApiResponse?.enqueue(object : Callback<LoginResponse?> {
            override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                Log.d("Error", t.message ?: "Error")
            }

            override fun onResponse(
                call: Call<LoginResponse?>,
                response: Response<LoginResponse?>
            ) {
                if (response.isSuccessful) {
                    // Connexion réussie
                    val loginResponse = response.body()
                    activity.runOnUiThread {
                        Toast.makeText(activity, "Connexion réussie", Toast.LENGTH_SHORT).show()
                        val intent = Intent(activity, com.esgi.promocare_android.views.HomeActivity::class.java)
                        activity.startActivity(intent)
                    }
                } else {
                    // Échec de la connexion
                    activity.runOnUiThread {
                        Toast.makeText(
                            activity,
                            "Échec de la connexion. Veuillez vérifier vos informations d'identification.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        })
    }
}
