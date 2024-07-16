package com.esgi.promocare_android.views.company_annonce.create_annonce

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.esgi.promocare_android.R
import com.esgi.promocare_android.data.Annonce

class ChoseLocation: AppCompatActivity() {
    private lateinit var location: EditText
    private lateinit var next: Button
    private lateinit var skip: Button
    private lateinit var error: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_location)
        this.location = findViewById(R.id.creation_annonce_edit_view_location)
        this.next = findViewById(R.id.creation_annonce_location_button_next)
        this.skip = findViewById(R.id.creation_annonce_location_button_skip)
        this.error = findViewById(R.id.creation_annonce_location_error)
        setButton()
        setSkip()
    }

    private fun setButton(){
        this.next.setOnClickListener {
            val location = this.location.text.toString()
            if (location.length > 100) {
                this.error.text = "L'adresse ne peut pas dépasser 100 caractères"
                error.visibility = TextView.VISIBLE
                return@setOnClickListener
            }
            if (location == "" || location == null || location.length < 10) {
                this.error.text = "L'adresse ne peut pas être vide ou inférieur à 10 caractères"
                error.visibility = TextView.VISIBLE
                return@setOnClickListener
            }
            Annonce.getCreateAnnonceViewModel().location = location
            startActivity(Intent(this, ChosePrice::class.java))
        }
    }

    private fun setSkip(){
        this.skip.setOnClickListener {
            startActivity(Intent(this, ChosePrice::class.java))
        }
    }
}