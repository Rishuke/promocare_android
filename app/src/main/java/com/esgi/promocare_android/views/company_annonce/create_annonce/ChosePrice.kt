package com.esgi.promocare_android.views.company_annonce.create_annonce

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.esgi.promocare_android.R
import com.esgi.promocare_android.data.Annonce

class ChosePrice:AppCompatActivity() {
    private lateinit var price: EditText
    private lateinit var promo : EditText
    private lateinit var realPrice: TextView
    private lateinit var validatePrice: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_price_annonce)

        this.price = findViewById(R.id.creation_annonce_edit_view_price)
        this.promo = findViewById(R.id.creation_annonce_edit_view_promo)
        this.realPrice = findViewById(R.id.creation_annonce_price_calculator)
        this.validatePrice = findViewById(R.id.creation_annonce_price_button)

        this.handleEditChange()
        this.validatePrice()
    }

    private fun calculateRealPrice(){
        if(this.price.text.isEmpty() || this.promo.text.isEmpty()){
            this.realPrice.text = getString(R.string.mauvaise_valeur)
            return
        }
        val price = this.price.text.toString().toDouble()
        val promo = this.promo.text.toString().toDouble()
        if(price.isNaN() || promo.isNaN()){
            this.realPrice.text = getString(R.string.erreur_dans_le_calcul_du_prix)
        }
        val priceWithPromo = price - (price * (promo / 100))
        this.realPrice.text = buildString {
            append("Le prix est ")
            append(priceWithPromo)
        }
    }

    private fun calculPrice():Float{
        val price = this.price.text.toString().toDouble()
        val promo = this.promo.text.toString().toDouble()
        val priceWithPromo = price - (price * (promo / 100))
        return priceWithPromo.toFloat()
    }

    private fun handleEditChange(){
        this.price.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculateRealPrice()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        this.promo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculateRealPrice()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun validatePrice(){
        this.validatePrice.setOnClickListener {
            if(this.price.text.isEmpty() || this.promo.text.isEmpty()){
                this.realPrice.text = getString(R.string.mauvaise_valeur)
                return@setOnClickListener
            }
            if(this.price.text.toString().toFloat() <= 0){
                this.realPrice.text = getString(R.string.le_prix_ne_peut_pas_tre_inf_rieur_0)
                return@setOnClickListener
            }
            if(this.promo.text.toString().toInt() <= 0 || this.promo.text.toString().toInt() >= 100){
                this.realPrice.text =
                    getString(R.string.une_r_duction_sup_rieur_100_ou_inf_rieur_0_n_est_pas_autoris)
                return@setOnClickListener
            }
            if(calculPrice() < 0){
                this.realPrice.text = getString(R.string.une_r_duction_sup_rieur_100_ou_inf_rieur_0_n_est_pas_autoris)
                return@setOnClickListener
            }
            Annonce.getCreateAnnonceViewModel().price = this.price.text.toString().toFloat()
            Annonce.getCreateAnnonceViewModel().promo = this.promo.text.toString().toInt()
            Annonce.getCreateAnnonceViewModel().postAnnonceCompany(this)

        }
    }
}