package com.esgi.promocare_android.models.conversations

import com.esgi.promocare_android.models.annonce.AnnonceDto

data class ConvFromDto(
    val annonce_id: String?,
    val created_at: String?,
    val first_conv_id: String?,
    val from: String?,
    val isFirst: Boolean?,
    val message: String?,
    val sender_id: String?,
    val target_id: String?,
    val updated_at: String?,
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

