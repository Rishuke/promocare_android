package com.esgi.promocare_android.data

import com.esgi.promocare_android.network.inscriptionConnexion.InscriptionConnexionRepository
import com.esgi.promocare_android.network.inscriptionConnexion.InscriptionConnexionServices
import com.esgi.promocare_android.network.Retrofit
import com.esgi.promocare_android.viewmodel.inscriptionConnexion.UserConnectionViewModel


object InscriptionConnexion {
    private val inscriptionConnexionService: InscriptionConnexionServices by lazy {
        createInscriptionConnexionService()
    }

    private val userConnectionViewModel: UserConnectionViewModel by lazy {
        initUserConnectionViewModel()
    }

    private fun createInscriptionConnexionService(): InscriptionConnexionServices {
        return Retrofit.getRetrofitClient().create(InscriptionConnexionServices::class.java)
    }

    fun getInscriptionConnexionServices() = inscriptionConnexionService

    private fun initUserConnectionViewModel(): UserConnectionViewModel {
        return UserConnectionViewModel(InscriptionConnexionRepository(inscriptionConnexionService))
    }

    fun getUserConnectionViewModel() = userConnectionViewModel
}