package com.esgi.promocare_android.models.annonce

data class CreateAnnonceDto(
    var title:String = "",
    var description:String = "",
    var price:Float = -1f,
    var type:String = "",
    var location:String = "No location provided",
    var promo:Int = -1
)
