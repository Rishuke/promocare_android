package com.esgi.promocare_android.views.offer.createOfferCompany

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.esgi.promocare_android.R
import com.esgi.promocare_android.data.Offer
import com.esgi.promocare_android.views.conversations.company.ConversationCompanyActivity
import com.esgi.promocare_android.views.user_annonce.AnnonceUserActivity.Companion.ANNONCE_MODEL_EXTRA

class PostCompanyOfferLocationActivity: AppCompatActivity(){

    private lateinit var location: EditText
    private lateinit var error: TextView
    private lateinit var nextScreen: Button
    private lateinit var skip: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_offer_location)
        setUpView()
        handleSkip()
        handleSend()
    }

    private fun setUpView(){
        location = findViewById(R.id.company_offer_location_edit_text)
        nextScreen = findViewById(R.id.company_offer_send_location_button)
        skip = findViewById(R.id.company_offer_skip_location_button)
        error = findViewById(R.id.company_offer_error_location_text_view)
    }

    private fun handleSend(){
        nextScreen.setOnClickListener {
            if (location.text.toString().isEmpty()){
                error.visibility = TextView.VISIBLE
                error.text = "Veuillez remplir les champs"
            }else{
                Offer.getCreateOfferCompanyViewModel().location = location.text.toString()
                val intent = Intent(this, PostCompanyOfferPriceActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun handleSkip(){
        skip.setOnClickListener {
            startActivity(Intent(this, PostCompanyOfferPriceActivity::class.java))
        }
    }
}