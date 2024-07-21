package com.esgi.promocare_android.models.offer

import com.esgi.promocare_android.models.annonce.AnnonceDto
import com.esgi.promocare_android.models.annonce.AnnonceModel
import com.esgi.promocare_android.models.company.CompanyModel
import com.esgi.promocare_android.models.company.CompanyModelDto
import com.esgi.promocare_android.models.user.UserModel
import com.esgi.promocare_android.models.user.UserModelDto


data class AllOfferCompany(
    val offers: List<GetOfferCompanyDto>?
)

data class GetOfferCompanyDto(
    val offer : OfferModelDto,
    val user : UserModelDto,
    val annonce : AnnonceDto
)

data class GetOfferCompany(
    val offer : OfferModel,
    val user : UserModel,
    val annonce : AnnonceModel
)

data class AllOfferUser(
    val offers: List<GetOfferUserDto>?
)

data class GetOfferUserDto(
    val offer : OfferModelDto,
    val company : CompanyModelDto,
    val annonce : AnnonceDto
)

data class GetOfferUser(
    val offer : OfferModel,
    val company : CompanyModel,
    val annonce : AnnonceModel
)