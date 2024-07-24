package com.esgi.promocare_android.models.conversations

import com.google.gson.annotations.SerializedName

data class PostConversationDto(
    val message : String?,
)

data class ReturnPostFirstConvDto(
    val message: String?,
    val item : PostFirstConvAnswerDto?
)

data class PostFirstConvAnswerDto(
    @SerializedName(value = "annonce_id")val annonceId: String?,
    @SerializedName(value = "created_at")val createdAt: String?,
    @SerializedName(value = "first_conv_id")val firstConvId: String?,
    val isFirst: Boolean,
    val message: String?,
    @SerializedName(value = "sender_id")val senderId: String?,
    @SerializedName(value = "target_id")val targetId: String?,
    @SerializedName(value = "updated_at")val updatedAt: String?,
    val uuid: String?
)