package com.esgi.promocare_android.data

import com.esgi.promocare_android.network.Retrofit
import com.esgi.promocare_android.network.annonce_services.AnnonceRepository
import com.esgi.promocare_android.network.annonce_services.AnnonceServices
import com.esgi.promocare_android.viewmodel.annonce.AnnonceCompanyViewModel
import com.esgi.promocare_android.viewmodel.annonce.CreateAnnonceViewModel

object Annonce {
    private val annonceServices: AnnonceServices by lazy {
        createServices()
    }

    private val annonceCompanyViewModel: AnnonceCompanyViewModel by lazy {
        initViewModel()
    }

    private val createAnnonceCompanyViewModel: CreateAnnonceViewModel by lazy {
        initCreateAnnonceViewModel()
    }

    private fun createServices(): AnnonceServices {
        return Retrofit.getRetrofitClient().create(AnnonceServices::class.java)
    }

    private fun initViewModel(): AnnonceCompanyViewModel {
        return AnnonceCompanyViewModel(AnnonceRepository(annonceServices))
    }

    fun getViewModel() = annonceCompanyViewModel

    private fun initCreateAnnonceViewModel(): CreateAnnonceViewModel {
        return CreateAnnonceViewModel(AnnonceRepository(annonceServices))
    }

    fun getCreateAnnonceViewModel() = createAnnonceCompanyViewModel
}