package com.esgi.promocare_android.views.user_annonce

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.esgi.promocare_android.R
import com.esgi.promocare_android.models.annonce.AnnonceModel
import com.esgi.promocare_android.utils.handleDate
import com.esgi.promocare_android.utils.loadImage
import com.esgi.promocare_android.views.conversations.user.PostUserFirstConvActivity
import com.esgi.promocare_android.views.user_annonce.AnnonceUserActivity.Companion.ANNONCE_MODEL_EXTRA

class AnnonceUserDetailActivity:AppCompatActivity() {
    companion object {
        const val ANNONCE = "ANNONCE_EXTRA"
    }
    private lateinit var annonceModel: AnnonceModel
    private lateinit var annonceTitle : TextView
    private lateinit var annoncePrice : TextView
    private lateinit var reduction : TextView
    private lateinit var date : TextView
    private lateinit var descritpion : TextView
    private lateinit var contactCompany : Button
    private lateinit var imageAnnonce : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail_annonce)
        setView()
        getIntentExtraData()
        goToContact()
    }

    private fun setView(){
        this.annonceTitle = findViewById(R.id.cell_layout_annonce_title_user_detail)
        this.annoncePrice = findViewById(R.id.cell_layout_annonce_price_user_detail)
        this.reduction = findViewById(R.id.cell_layout_annonce_reduction_user_detail)
        this.date = findViewById(R.id.cell_layout_annonce_date_user_detail)
        this.descritpion = findViewById(R.id.detail_annonce_user_text_view_description)
        this.contactCompany = findViewById(R.id.detail_annonce_user_button_contact)
        this.imageAnnonce = findViewById(R.id.cell_layout_annonce_image_user_detail)
    }

    private fun goToContact(){
        this.contactCompany.setOnClickListener {
            Intent(this, PostUserFirstConvActivity::class.java).also {
                it.putExtra(ANNONCE, annonceModel)
                startActivity(it)
            }
        }
    }

    private fun getIntentExtraData(){
        if (this.intent.hasExtra(ANNONCE_MODEL_EXTRA)) {
            val annonceData = intent.getParcelableExtra<AnnonceModel>(ANNONCE_MODEL_EXTRA)!!
            this.annonceModel = annonceData

            this.annonceTitle.text = annonceData.title

            val texte = " ${annonceData.description} \n\n Catégorie : ${annonceData.type} \n\n Localisation : ${annonceData.location}"

            this.descritpion.text = texte
            if(annonceData.price != null && annonceData.promo != null) {
                val pricePromo = annonceData.price * (1 - (annonceData.promo / 100.0)) // Ensure floating-point division
                val formattedPrice = String.format("%.2f€", pricePromo)
                this.annoncePrice.text = formattedPrice
            }
            this.reduction.text = " -${annonceData.promo.toString()}%!!!"
            this.date.text = "Créer le ${handleDate(annonceData.createdAt.toString())}"

            loadImage(imageAnnonce, annonceModel.type)
        }
    }
}