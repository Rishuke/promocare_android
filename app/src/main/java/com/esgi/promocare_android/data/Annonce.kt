package com.esgi.promocare_android.data

import com.esgi.promocare_android.network.Retrofit
import com.esgi.promocare_android.network.annonce_services.AnnonceRepository
import com.esgi.promocare_android.network.annonce_services.AnnonceServices
import com.esgi.promocare_android.viewmodel.annonce.AnnonceCompanyViewModel

object Annonce {
    private val annonceServices: AnnonceServices by lazy {
        createServices()
    }

    private val annonceCompanyViewModel: AnnonceCompanyViewModel by lazy {
        initViewModel()
    }

    private fun createServices(): AnnonceServices {
        return Retrofit.getRetrofitClient().create(AnnonceServices::class.java)
    }

    private fun initViewModel(): AnnonceCompanyViewModel {
        return AnnonceCompanyViewModel(AnnonceRepository(annonceServices))
    }

    fun getViewModel() = annonceCompanyViewModel
}