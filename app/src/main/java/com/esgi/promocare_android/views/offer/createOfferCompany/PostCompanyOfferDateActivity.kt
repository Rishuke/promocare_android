package com.esgi.promocare_android.views.offer.createOfferCompany

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.esgi.promocare_android.R
import com.esgi.promocare_android.data.Offer

class PostCompanyOfferDateActivity: AppCompatActivity(){

    private lateinit var startDate: EditText
    private lateinit var endDate: EditText
    private lateinit var error: TextView
    private lateinit var nextScreen: Button
    private lateinit var skip: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_offer_date)
        setUpView()
        handleSkip()
        handleSend()
    }

    private fun setUpView(){
        startDate = findViewById(R.id.company_offer_date_start_edit_text)
        endDate = findViewById(R.id.company_offer_date_end_edit_text)
        nextScreen = findViewById(R.id.company_offer_send_date_button)
        skip = findViewById(R.id.company_offer_skip_date_button)
        error = findViewById(R.id.company_offer_error_date_text_view)
    }

    private fun handleSend(){
        nextScreen.setOnClickListener {
            if (startDate.text.toString().isEmpty() && endDate.text.toString().isEmpty()){
                error.visibility = TextView.VISIBLE
                error.text = getString(R.string.veuillez_remplir_les_champs)
            }else{
                val intent = Intent(this, PostCompanyOfferFrequencyActivity::class.java)
                Offer.getCreateOfferCompanyViewModel().startDate = startDate.text.toString()
                Offer.getCreateOfferCompanyViewModel().endDate = endDate.text.toString()
                startActivity(intent)
            }
        }
    }

    private fun handleSkip(){
        skip.setOnClickListener {
            startActivity(Intent(this, PostCompanyOfferFrequencyActivity::class.java))
        }
    }

}