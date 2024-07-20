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
    var convId: String = ""

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
                    convId = response.body()?.convId.toString()
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
                convId = response.body()?.item?.uuid.toString()
                getConvId(token,convId,noResult)
            }
        })

    }

    fun postConv(token: String,postMessage: PostConversationDto,annonceId: String,noResult: TextView){
        val apiResponse = conversationRepository.postConv(token,postMessage,this.convId,annonceId)

        apiResponse.enqueue(object : Callback<ReturnPostFirstConvDto> {
            override fun onFailure(p0: Call<ReturnPostFirstConvDto>, t: Throwable) {

            }

            override fun onResponse(p0: Call<ReturnPostFirstConvDto>, response: Response<ReturnPostFirstConvDto>) {
                getConvId(token,convId,noResult)
            }
        })
    }

    fun getConvId(token: String, convId: String, noResult: TextView){
        val apiResponse = conversationRepository.getConvId(token,convId)

        apiResponse.enqueue(object : Callback<ReturnConvFromDto> {
            override fun onFailure(p0: Call<ReturnConvFromDto>, t: Throwable) {

            }

            override fun onResponse(p0: Call<ReturnConvFromDto>, response: Response<ReturnConvFromDto>) {
                val responseBody: List<ConvFromDto> = response.body()?.conversations ?: listOf()
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
                conversationList.value = ArrayList(mappedResponse)
                if((conversationList.value as ArrayList<ConvFrom>).isEmpty()){
                    noResult.visibility = TextView.VISIBLE
                }
                else{
                    noResult.visibility = TextView.GONE
                }
            }
        })

    }
}