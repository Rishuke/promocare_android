package com.esgi.promocare_android.data


import com.esgi.promocare_android.network.Retrofit
import com.esgi.promocare_android.network.offer_services.OfferRepository
import com.esgi.promocare_android.network.offer_services.OfferServices
import com.esgi.promocare_android.viewmodel.offer.CreateOfferViewModel
import com.esgi.promocare_android.viewmodel.offer.GetOfferCompanyViewModel
import com.esgi.promocare_android.viewmodel.offer.GetOfferUserViewModel

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

    private val getOfferCompanyViewModel: GetOfferCompanyViewModel by lazy {
        initGetOfferViewModel()
    }

    private fun initGetOfferViewModel(): GetOfferCompanyViewModel {
        return GetOfferCompanyViewModel(OfferRepository(offerServices))
    }

    fun getOfferCompanyViewModel() = getOfferCompanyViewModel

    private val getOfferUserViewModel: GetOfferUserViewModel by lazy {
        initGetOfferUserViewModel()
    }

    private fun initGetOfferUserViewModel(): GetOfferUserViewModel {
        return GetOfferUserViewModel(OfferRepository(offerServices))
    }

    fun getOfferUserViewModel() = getOfferUserViewModel
}