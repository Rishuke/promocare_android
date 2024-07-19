package com.esgi.promocare_android.viewmodel.conversation

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.esgi.promocare_android.models.conversations.ConvFrom
import com.esgi.promocare_android.models.conversations.ConvFromDto
import com.esgi.promocare_android.models.conversations.NoConvDto
import com.esgi.promocare_android.models.conversations.PostConversationDto
import com.esgi.promocare_android.models.conversations.ReturnConvFromDto
import com.esgi.promocare_android.models.conversations.ReturnPostFirstConvDto
import com.esgi.promocare_android.network.conversation_services.ConversationRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostFirstConvUserViewModel(private val conversationRepository: ConversationRepository) {

    var conversationList: MutableLiveData<ArrayList<ConvFrom>> = MutableLiveData()

    fun verifyNoConv(token: String, annonceId: String,noResult:TextView) {
        val apiResponse = conversationRepository.verifyNoConv(token, annonceId)

        apiResponse.enqueue(object : Callback<NoConvDto> {
            override fun onFailure(p0: Call<NoConvDto>, t: Throwable) {
                Log.d("ERROR CREATE", "onFailure: $t")
            }

            override fun onResponse(p0: Call<NoConvDto>, response: Response<NoConvDto>) {
                if(response.code()==200){
                    noResult.visibility = TextView.VISIBLE
                }
                else{
                    getConvId(token,response.body()?.convId.toString(),noResult)
                }
            }
        })
    }

    fun postConvFirstUser(token: String,postFirstMessage: PostConversationDto,annonceId: String, noResult: TextView){
        val apiResponse = conversationRepository.postFirstConvUser(token,postFirstMessage,annonceId)

        apiResponse.enqueue(object : Callback<ReturnPostFirstConvDto> {
            override fun onFailure(p0: Call<ReturnPostFirstConvDto>, t: Throwable) {

            }

            override fun onResponse(p0: Call<ReturnPostFirstConvDto>, response: Response<ReturnPostFirstConvDto>) {
                /**val convId = response.body()?.item?.uuid.toString()
                Log.d("ID DE LA CONV", "onResponse: $convId")
                getConvId(token,convId,noResult)**/
            }
        })

    }

    fun getConvId(token: String, convId: String, noResult: TextView){
        val apiResponse = conversationRepository.getConvId(token,convId)

        apiResponse.enqueue(object : Callback<ReturnConvFromDto> {
            override fun onFailure(p0: Call<ReturnConvFromDto>, t: Throwable) {
                Log.d("ERROR CREATE", "onFailure: $t")
            }

            override fun onResponse(p0: Call<ReturnConvFromDto>, response: Response<ReturnConvFromDto>) {
                val responseBody: List<ConvFromDto> = response.body()?.conversations ?: listOf()
                Log.d("ResponseBody", "onResponse: $responseBody")
                val mappedResponse = responseBody.map {
                    ConvFrom(
                        it.annonce_id,
                        it.created_at,
                        it.first_conv_id,
                        it.from,
                        it.isFirst,
                        it.message,
                        it.sender_id,
                        it.target_id,
                        it.updated_at,
                        it.uuid
                    )
                }
                Log.d("MAPPED", "onResponse: $mappedResponse")
                conversationList.value = ArrayList(mappedResponse)
                Log.d("CONVERSATION LIST","$conversationList"
                )
                if((conversationList.value as ArrayList<ConvFrom>).isNotEmpty()){
                    noResult.visibility = TextView.GONE
                }
                else{
                    noResult.visibility = TextView.VISIBLE
                }
            }
        })

    }
}