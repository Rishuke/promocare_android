package com.esgi.promocare_android.network.conversation_services

import com.esgi.promocare_android.models.conversations.NoConvDto
import com.esgi.promocare_android.models.conversations.PostConversationDto
import com.esgi.promocare_android.models.conversations.ReturnConvFromDto
import com.esgi.promocare_android.models.conversations.ReturnPostFirstConvDto
import com.esgi.promocare_android.models.conversations.ListLatestConvDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ConversationServices {
    @GET("verifUserNoConv/{annonceId}")
    fun verifyNoConv(
        @Header("Authorization") token: String,
        @Path("annonceId") annonceId: String): Call<NoConvDto>

    @POST("postFirstConvUser/{annonceId}")
    fun postFirstConvUser(
        @Header("Authorization") token: String,
        @Body postConversationDto: PostConversationDto,
        @Path("annonceId") annonceId: String): Call<ReturnPostFirstConvDto>

    @GET("getConv/{convId}")
    fun getConvId(
        @Header("Authorization") token: String,
        @Path("convId") convId: String): Call<ReturnConvFromDto>

    @POST("postConv/{convId}/{annonceId}")
    fun postConv(
        @Header("Authorization") token: String,
        @Body postConversationDto: PostConversationDto,
        @Path("convId") convId: String,
        @Path("annonceId") annonceId: String): Call<ReturnPostFirstConvDto>

    @GET("getFirstConvUser")
    fun getLatestConvUser(
        @Header("Authorization") token: String
    ): Call<ListLatestConvDto>

    @GET("getFirstConvCompany")
    fun getLatestConvCompany(
        @Header("Authorization") token: String
    ): Call<ListLatestConvDto>
}