package com.esgi.promocare_android.views.offer.createOfferCompany

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.esgi.promocare_android.R
import com.esgi.promocare_android.data.Offer

class PostCompanyOfferPriceActivity:AppCompatActivity() {
    private lateinit var price: EditText
    private lateinit var nombreSeance: EditText
    private lateinit var error: TextView
    private lateinit var nextScreen: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_offer_price)
        setUpView()
        handleSend()
    }

    private fun setUpView(){
        price = findViewById(R.id.company_offer_price_edit_text)
        nombreSeance = findViewById(R.id.company_offer_seance_edit_text)
        nextScreen = findViewById(R.id.company_offer_send_price_button)
        error = findViewById(R.id.company_offer_error_price_text_view)
    }

    private fun handleSend(){
        nextScreen.setOnClickListener {
            if (price.text.toString().isEmpty()){
                error.visibility = TextView.VISIBLE
                error.text = "Le prix est obligatoire"
            }else{
                Offer.getCreateOfferCompanyViewModel().price = price.text.toString() + "€ par séance"
                Offer.getCreateOfferCompanyViewModel().nbSeance = nombreSeance.text.toString()+" séances au total"
                val intent = Intent(this, RecapOfferActivity::class.java)
                startActivity(intent)
            }
        }
    }
}