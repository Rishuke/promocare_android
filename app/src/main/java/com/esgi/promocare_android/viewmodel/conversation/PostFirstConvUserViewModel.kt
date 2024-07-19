package com.esgi.promocare_android.viewmodel.conversation

import android.util.Log
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import com.esgi.promocare_android.models.annonce.AnnonceDto
import com.esgi.promocare_android.models.annonce.AnnonceModel
import com.esgi.promocare_android.models.annonce.ReturnCreateAnnonceDto
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

    var conversationList: MutableLiveData<MutableList<ConvFrom>> = MutableLiveData()

    fun verifyNoConv(token: String, annonceId: String,noResult:TextView) {
        val apiResponse = conversationRepository.verifyNoConv(token, annonceId)

        apiResponse.enqueue(object : Callback<NoConvDto> {
            override fun onFailure(p0: Call<NoConvDto>, t: Throwable) {
                Log.d("ERROR CREATE", "onFailure: $t")
            }

            override fun onResponse(p0: Call<NoConvDto>, response: Response<NoConvDto>) {
                Log.d("SUCCESS CREATE", "onResponse: ${response.body()?.convId}")
                if(response.code()==200){
                    noResult.visibility = TextView.VISIBLE
                }
                else{
                    getConv(token,response.body()?.convId.toString())
                }
            }
        })
    }

    fun postConvFirstUser(token: String,postFirstMessage: PostConversationDto,annonceId: String){
        val apiResponse = conversationRepository.postFirstConvUser(token,postFirstMessage,annonceId)

        apiResponse.enqueue(object : Callback<ReturnPostFirstConvDto> {
            override fun onFailure(p0: Call<ReturnPostFirstConvDto>, t: Throwable) {

            }

            override fun onResponse(p0: Call<ReturnPostFirstConvDto>, response: Response<ReturnPostFirstConvDto>) {
                getConv(token,response.body()?.item?.uuid.toString())
            }
        })

    }

    fun getConv(token: String,convId: String){
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
            }
        })

    }
}