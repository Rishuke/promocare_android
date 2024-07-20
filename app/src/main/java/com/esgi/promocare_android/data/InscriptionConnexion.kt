package com.esgi.promocare_android.data

import com.esgi.promocare_android.network.inscription_connexion.InscriptionConnexionRepository
import com.esgi.promocare_android.network.inscription_connexion.InscriptionConnexionServices
import com.esgi.promocare_android.network.Retrofit
import com.esgi.promocare_android.viewmodel.inscription_connexion.InscriptionConnectionViewModel
import com.esgi.promocare_android.viewmodel.inscription_register.InscriptionCompanyViewModel
import com.esgi.promocare_android.viewmodel.inscription_register.InscriptionViewModel


object InscriptionConnexion {
    private val inscriptionConnexionService: InscriptionConnexionServices by lazy {
        createInscriptionConnexionService()
    }

    private val inscriptionConnectionViewModel: InscriptionConnectionViewModel by lazy {
        initUserConnectionViewModel()
    }

    private val inscriptionViewModel: InscriptionViewModel by lazy {
        initUserRegisterViewModel()
    }

    private val inscriptionCompanyViewModel: InscriptionCompanyViewModel by lazy {
        initCompanyRegisterViewModel()
    }



    private fun createInscriptionConnexionService(): InscriptionConnexionServices {
        return Retrofit.getRetrofitClient().create(InscriptionConnexionServices::class.java)
    }

    private fun initUserConnectionViewModel(): InscriptionConnectionViewModel {
        return InscriptionConnectionViewModel(InscriptionConnexionRepository(inscriptionConnexionService))
    }

    private fun initUserRegisterViewModel(): InscriptionViewModel {
        return InscriptionViewModel(InscriptionConnexionRepository(inscriptionConnexionService))
    }

    private fun initCompanyRegisterViewModel(): InscriptionCompanyViewModel {
        return InscriptionCompanyViewModel(InscriptionConnexionRepository(inscriptionConnexionService))
    }

    fun getLoginViewModel() = inscriptionConnectionViewModel

    fun getRegisterViewModel() = inscriptionViewModel

    fun getCompanyRegisterViewModel() = inscriptionCompanyViewModel


}