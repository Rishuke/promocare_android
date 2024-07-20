package com.esgi.promocare_android.viewmodel.conversation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.esgi.promocare_android.models.conversations.LatestConv
import com.esgi.promocare_android.models.conversations.LatestConvDto
import com.esgi.promocare_android.network.conversation_services.ConversationRepository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LatestConvViewModel(private val conversationRepository: ConversationRepository) {
    var conversationList: MutableLiveData<ArrayList<LatestConv>> = MutableLiveData()

    fun getLatestUserConv(token:String){
        val apiResponse = conversationRepository.getLatestConvUser(token)

        apiResponse.enqueue(object : Callback<LatestConvDto> {
            override fun onFailure(p0: Call<LatestConvDto>, t: Throwable) {
                Log.d("ERROR CREATE", "onFailure: $t")
            }

            override fun onResponse(p0: Call<LatestConvDto>, response: Response<LatestConvDto>) {

            }
        })
    }
}