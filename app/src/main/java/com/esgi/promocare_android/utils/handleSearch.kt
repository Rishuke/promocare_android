package com.esgi.promocare_android.utils

import com.esgi.promocare_android.models.annonce.AnnonceModel


fun searchInAnnonce(annonces: MutableList<AnnonceModel>?, query: String): MutableList<AnnonceModel> {
    return annonces?.filter {
        it.title?.contains(query, ignoreCase = true) ?: false
    }?.toMutableList() ?: mutableListOf()
}