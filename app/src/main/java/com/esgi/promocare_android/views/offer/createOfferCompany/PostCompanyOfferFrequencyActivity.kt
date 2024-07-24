package com.esgi.promocare_android.views.offer.createOfferCompany

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.esgi.promocare_android.R
import com.esgi.promocare_android.data.Offer

class PostCompanyOfferFrequencyActivity:AppCompatActivity() {

    private lateinit var error : TextView
    private lateinit var frequencyNumber: EditText
    private lateinit var choice: Spinner
    private lateinit var frequencyChoice: String
    private lateinit var nextScreen: Button
    private lateinit var skip: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_offer_frequency)
        setUpView()
        handleSkip()
        handleSend()
    }

    private fun setUpView(){
        frequencyNumber = findViewById(R.id.company_offer_frequency_edit_text)
        choice = findViewById(R.id.company_offer_frequency_choice_spinner)
        nextScreen = findViewById(R.id.company_offer_send_frequency_button)
        skip = findViewById(R.id.company_offer_skip_frequency_button)
        error = findViewById(R.id.company_offer_error_frequency_text_view)

        val items = listOf("Jour", "Semaine", "Mois")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        choice.adapter = adapter

        choice.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                frequencyChoice = selectedItem
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Faire quelque chose lorsque rien n'est sélectionné (si nécessaire)
            }
        }
    }

    private fun handleSend(){
        nextScreen.setOnClickListener {
            if(frequencyNumber.text.toString().isEmpty()){
                error.visibility = TextView.VISIBLE
                error.text = getString(R.string.frequence_est_vide)
            }
            val frequence = frequencyNumber.text.toString() + " fois par $frequencyChoice"
            Offer.getCreateOfferCompanyViewModel().frequence = frequence
            startActivity(Intent(this, PostCompanyOfferLocationActivity::class.java))
        }
    }

    private fun handleSkip(){
        skip.setOnClickListener {
            startActivity(Intent(this, PostCompanyOfferLocationActivity::class.java))
        }
    }
}