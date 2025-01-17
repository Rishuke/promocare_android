package com.esgi.promocare_android.views.company_annonce.create_annonce

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.esgi.promocare_android.R
import com.esgi.promocare_android.data.Annonce

class ChoseDescription: AppCompatActivity(){
    private lateinit var descritpion: EditText
    private lateinit var next: Button
    private lateinit var error: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_description_annonce)
        this.descritpion = findViewById(R.id.creation_annonce_edit_view_description)
        this.next = findViewById(R.id.creation_annonce_descritpion_button_next)
        this.error = findViewById(R.id.creation_annonce_description_error)
        setButton()
    }

    private fun setButton(){
        this.next.setOnClickListener {
            val description = this.descritpion.text.toString()
            if (description.length > 1000) {
                this.error.text =
                    getString(R.string.la_description_ne_doit_pas_d_passer_1000_caract_res)
                error.visibility = TextView.VISIBLE
                return@setOnClickListener
            }
            if (description == "" || description.length < 25) {
                this.error.text =
                    getString(R.string.la_description_ne_doit_pas_tre_vide_ou_inf_rieur_25_caract_res)
                error.visibility = TextView.VISIBLE
                return@setOnClickListener
            }
            Annonce.getCreateAnnonceViewModel().description = description
            startActivity(Intent(this, ChoseCategory::class.java))
        }
    }
}