package com.esgi.promocare_android.network.conversation_services

import com.esgi.promocare_android.models.conversations.NoConvDto
import com.esgi.promocare_android.models.conversations.PostConversationDto
import com.esgi.promocare_android.models.conversations.ReturnConvFromDto
import com.esgi.promocare_android.models.conversations.ReturnPostFirstConvDto
import retrofit2.Call

class ConversationRepository(private val conversationServices: ConversationServices) {
    fun verifyNoConv(token: String,annonceId:String): Call<NoConvDto> {
        return conversationServices.verifyNoConv(token,annonceId)
    }

    fun postFirstConvUser(token: String, postConversationDto: PostConversationDto, annonceId: String): Call<ReturnPostFirstConvDto> {
        return conversationServices.postFirstConvUser(token,postConversationDto,annonceId)
    }

    fun getConvId(token: String, convId: String): Call<ReturnConvFromDto> {
        return conversationServices.getConvId(token,convId)
    }
}