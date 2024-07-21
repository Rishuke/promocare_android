package com.esgi.promocare_android.views.offer.company

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.esgi.promocare_android.R
import com.esgi.promocare_android.models.annonce.AnnonceModel
import com.esgi.promocare_android.models.offer.OfferModel
import com.esgi.promocare_android.models.user.UserModel
import com.esgi.promocare_android.utils.handleDate
import com.esgi.promocare_android.views.offer.company.CompanyOfferActivity.Companion.ANNONCE
import com.esgi.promocare_android.views.offer.company.CompanyOfferActivity.Companion.OFFER
import com.esgi.promocare_android.views.offer.company.CompanyOfferActivity.Companion.USER
import com.esgi.promocare_android.views.user_annonce.AnnonceUserActivity.Companion.ANNONCE_MODEL_EXTRA

class CompanyOfferDetailActivity:AppCompatActivity() {
    private lateinit var imageAnnonce : ImageView
    private lateinit var titleAnnonce : TextView
    private lateinit var descriptionAnnonce : TextView
    private lateinit var dateAnnonce : TextView
    private lateinit var statusAnnonce : TextView
    private lateinit var offerText : TextView

    //data
    private lateinit var offer : OfferModel
    private lateinit var annonce : AnnonceModel
    private lateinit var user : UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_offer_company)
        setUpView()
        getIntentExtra()
        setText()
    }

    private fun setUpView(){
        imageAnnonce = findViewById(R.id.offer_detail_image_company)
        titleAnnonce = findViewById(R.id.offer_detail_annonce_tilte)
        descriptionAnnonce = findViewById(R.id.offer_detail_description_annonce_company)
        dateAnnonce = findViewById(R.id.offer_detail_annonce_date_company)
        statusAnnonce = findViewById(R.id.offer_detail_send_to_company)
        offerText = findViewById(R.id.offer_detail_offer_company)
    }

    private fun getIntentExtra(){
        if (this.intent.hasExtra(ANNONCE)) {
            this.annonce = intent.getParcelableExtra<AnnonceModel>(ANNONCE)!!
        }
        if (this.intent.hasExtra(OFFER)) {
            this.offer = intent.getParcelableExtra<OfferModel>(OFFER)!!
        }
        if (this.intent.hasExtra(USER)) {
            this.user = intent.getParcelableExtra<UserModel>(USER)!!
        }
    }

    private fun setText(){
        titleAnnonce.text = annonce.title
        descriptionAnnonce.text = annonce.description
        if(annonce.createdAt != null){
            dateAnnonce.text = handleDate(annonce.createdAt!!)
        }
        else{
            dateAnnonce.text = "Date inconnue"
        }
        var offerDate = "Date inconnue"
        if(offer.created_at != null){
            offerDate = handleDate(offer.created_at!!)
        }

        val text = "Envoyé à ${user.first_name} ${user.last_name} le ${offerDate} , le status est ${offer.status}. Voici un résumé de votre offre :"
        statusAnnonce.text = text
        offerText.text = offer.text
    }
}