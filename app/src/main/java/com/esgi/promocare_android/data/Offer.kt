package com.esgi.promocare_android.data


import com.esgi.promocare_android.network.inscription_connexion.InscriptionConnexionServices
import com.esgi.promocare_android.viewmodel.offer.CreateOfferViewModel

object Offer {
    private val createOfferViewModel: CreateOfferViewModel by lazy {
        CreateOfferViewModel()
    }

    fun getCreateOfferCompanyViewModel() = createOfferViewModel
}