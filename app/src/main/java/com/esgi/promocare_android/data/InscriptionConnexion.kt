package com.esgi.promocare_android.data

import com.esgi.promocare_android.network.inscription_connexion.InscriptionConnexionRepository
import com.esgi.promocare_android.network.inscription_connexion.InscriptionConnexionServices
import com.esgi.promocare_android.network.Retrofit
import com.esgi.promocare_android.viewmodel.inscription_connexion.InscriptionConnectionViewModel


object InscriptionConnexion {
    private val inscriptionConnexionService: InscriptionConnexionServices by lazy {
        createInscriptionConnexionService()
    }

    private val inscriptionConnectionViewModel: InscriptionConnectionViewModel by lazy {
        initUserConnectionViewModel()
    }

    private fun createInscriptionConnexionService(): InscriptionConnexionServices {
        return Retrofit.getRetrofitClient().create(InscriptionConnexionServices::class.java)
    }

    private fun initUserConnectionViewModel(): InscriptionConnectionViewModel {
        return InscriptionConnectionViewModel(InscriptionConnexionRepository(inscriptionConnexionService))
    }

    fun getLoginViewModel() = inscriptionConnectionViewModel

}