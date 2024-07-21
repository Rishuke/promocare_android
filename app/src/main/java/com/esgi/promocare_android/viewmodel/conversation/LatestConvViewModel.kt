package com.esgi.promocare_android.viewmodel.conversation

import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.esgi.promocare_android.models.conversations.ConvFrom
import com.esgi.promocare_android.models.conversations.LatestConv
import com.esgi.promocare_android.models.conversations.LatestConvDto
import com.esgi.promocare_android.models.conversations.ListLatestConvDto
import com.esgi.promocare_android.network.conversation_services.ConversationRepository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LatestConvViewModel(private val conversationRepository: ConversationRepository) {
    var latestConvList: MutableLiveData<ArrayList<LatestConv>> = MutableLiveData()

    fun getLatestUserConv(token:String, loader: ProgressBar, errorTextView: TextView,noResult:TextView){
        val apiResponse = conversationRepository.getLatestConvUser(token)
        loader.visibility = ProgressBar.VISIBLE
        apiResponse.enqueue(object : Callback<ListLatestConvDto> {
            override fun onFailure(p0: Call<ListLatestConvDto>, t: Throwable) {
                errorTextView.visibility = TextView.VISIBLE
            }

            override fun onResponse(p0: Call<ListLatestConvDto>, response: Response<ListLatestConvDto>) {
                val latestConv: List<LatestConvDto> = response.body()?.latestConv ?: listOf()
                val mappedResponse = latestConv.map {
                    if(it.annonce!=null) {
                        LatestConv(
                            it.annonce,
                            it.conversation
                        )
                    }
                    else{
                        null
                    }

                }
                loader.visibility = ProgressBar.GONE
                errorTextView.visibility = TextView.GONE
                noResult.visibility = TextView.GONE
                if(mappedResponse.isEmpty()){
                    noResult.visibility = TextView.VISIBLE
                }

                val filteredResponse = mappedResponse.filterNotNull()

                latestConvList.value = ArrayList(filteredResponse)
            }
        })
    }

    fun getLatestCompanyConv(token:String, loader: ProgressBar, errorTextView: TextView,noResult:TextView){
        val apiResponse = conversationRepository.getLatestConvCompany(token)
        loader.visibility = ProgressBar.VISIBLE
        apiResponse.enqueue(object : Callback<ListLatestConvDto> {
            override fun onFailure(p0: Call<ListLatestConvDto>, t: Throwable) {
                errorTextView.visibility = TextView.VISIBLE
            }

            override fun onResponse(p0: Call<ListLatestConvDto>, response: Response<ListLatestConvDto>) {
                val latestConv: List<LatestConvDto> = response.body()?.latestConv ?: listOf()
                val mappedResponse = latestConv.map {
                    if(it.annonce!=null) {
                        LatestConv(
                            it.annonce,
                            it.conversation
                        )
                    }
                    else{
                        null
                    }

                }
                loader.visibility = ProgressBar.GONE
                errorTextView.visibility = TextView.GONE
                noResult.visibility = TextView.GONE
                if(mappedResponse.isEmpty()){
                    noResult.visibility = TextView.VISIBLE
                }
                val filteredResponse = mappedResponse.filterNotNull()
                latestConvList.value = ArrayList(filteredResponse)
            }
        })
    }
}