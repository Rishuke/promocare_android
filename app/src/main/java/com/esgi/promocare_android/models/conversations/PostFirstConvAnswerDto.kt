package com.esgi.promocare_android.models.conversations

data class PostConversationDto(
    val message : String?,
)

data class ReturnPostFirstConvDto(
    val message: String?,
    val item : PostFirstConvAnswerDto?
)

data class PostFirstConvAnswerDto(
    val annonce_id: String?,
    val created_at: String?,
    val first_conv_id: String?,
    val isFirst: Boolean,
    val message: String?,
    val sender_id: String?,
    val target_id: String?,
    val updated_at: String?,
    val uuid: String?
)