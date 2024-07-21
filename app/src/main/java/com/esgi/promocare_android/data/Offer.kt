package com.esgi.promocare_android.data


import com.esgi.promocare_android.network.Retrofit
import com.esgi.promocare_android.network.annonce_services.AnnonceServices
import com.esgi.promocare_android.network.inscription_connexion.InscriptionConnexionServices
import com.esgi.promocare_android.network.offer_services.OfferRepository
import com.esgi.promocare_android.network.offer_services.OfferServices
import com.esgi.promocare_android.viewmodel.offer.CreateOfferViewModel

object Offer {

    private val offerServices: OfferServices by lazy {
        createServices()
    }

    private fun createServices(): OfferServices{
        return Retrofit.getRetrofitClient().create(OfferServices::class.java)
    }

    private val createOfferViewModel: CreateOfferViewModel by lazy {
        initCreateOfferViewModel()
    }

    private fun initCreateOfferViewModel(): CreateOfferViewModel {
        return CreateOfferViewModel(OfferRepository(offerServices))
    }

    fun getCreateOfferCompanyViewModel() = createOfferViewModel
}