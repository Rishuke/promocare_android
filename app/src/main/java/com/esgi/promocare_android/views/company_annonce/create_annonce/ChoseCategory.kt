package com.esgi.promocare_android.views.company_annonce.create_annonce

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.esgi.promocare_android.R
import com.esgi.promocare_android.data.Annonce

class ChoseCategory:AppCompatActivity() {
    private lateinit var cuisine: Button
    private lateinit var menage: Button
    private lateinit var soin: Button
    private lateinit var chauffeur: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category_annonce)
        this.cuisine = findViewById(R.id.add_category_annonce_menage)
        this.menage = findViewById(R.id.add_category_annonce_cuisine)
        this.soin = findViewById(R.id.add_category_annonce_conduite)
        this.chauffeur = findViewById(R.id.add_category_annonce_soin)
        setButton()
    }

    private fun setButton(){
        this.cuisine.setOnClickListener {
            Annonce.getCreateAnnonceViewModel().category = "Cuisine"
            startActivity(Intent(this, ChoseLocation::class.java))
        }
        this.menage.setOnClickListener {
            Annonce.getCreateAnnonceViewModel().category = "Menage"
            startActivity(Intent(this, ChoseLocation::class.java))
        }
        this.soin.setOnClickListener {
            Annonce.getCreateAnnonceViewModel().category = "Soin"
            startActivity(Intent(this, ChoseLocation::class.java))
        }
        this.chauffeur.setOnClickListener {
            Annonce.getCreateAnnonceViewModel().category = "Chauffeur"
            startActivity(Intent(this, ChoseLocation::class.java))
        }
    }
}