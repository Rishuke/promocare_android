package com.esgi.promocare_android.views.company_annonce.create_annonce

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.esgi.promocare_android.R
import com.esgi.promocare_android.data.Annonce

class ChoseTitle:AppCompatActivity() {

    private lateinit var title: EditText
    private lateinit var next: Button
    private lateinit var error: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_annonce_title)
        this.title = findViewById(R.id.creation_annonce_edit_view_title)
        this.next = findViewById(R.id.creation_annonce_button_next)
        this.error = findViewById(R.id.creation_annonce_error)
        setButton()
    }

    private fun setButton(){
        this.next.setOnClickListener {
            val title = this.title.text.toString()
            if (title.length > 100) {
                this.error.text = "Le titre ne doit pas dépasser 100 caractères"
                error.visibility = TextView.VISIBLE
                return@setOnClickListener
            }
            if (title == "" || title == null) {
                this.error.text = "Le titre ne doit pas être vide"
                error.visibility = TextView.VISIBLE
                return@setOnClickListener
            }
            Annonce.getCreateAnnonceViewModel().title = title
            startActivity(Intent(this, ChoseDescription::class.java))
        }
    }
}