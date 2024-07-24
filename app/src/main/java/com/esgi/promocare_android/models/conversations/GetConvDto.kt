package com.esgi.promocare_android.models.conversations

import com.esgi.promocare_android.models.annonce.AnnonceDto
import com.google.gson.annotations.SerializedName

data class ConvFromDto(
    @SerializedName(value = "annonce_id")val annonceId: String?,
    @SerializedName(value = "created_at")val createdAt: String?,
    @SerializedName(value = "first_conv_id")val firstConvId: String?,
    val from: String?,
    val isFirst: Boolean?,
    val message: String?,
    @SerializedName(value = "sender_id")val senderId: String?,
    @SerializedName(value = "target_id")val targetId: String?,
    @SerializedName(value = "updated_at")val updatedAt: String?,
    val uuid: String?
)

data class ReturnConvFromDto(
    val annonce: AnnonceDto?,
    val conversations: List<ConvFromDto>,
)

data class LatestConvDto(
    val annonce:AnnonceDto?,
    val conversation:ConvFromDto?
)

data class ListLatestConvDto(
    val latestConv:List<LatestConvDto>
)

data class LatestConv(
    val annonce:AnnonceDto?,
    val conversation:ConvFromDto?
)

