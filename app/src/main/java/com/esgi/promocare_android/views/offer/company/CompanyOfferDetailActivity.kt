package com.esgi.promocare_android.views.offer.company

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import com.esgi.promocare_android.R
import com.esgi.promocare_android.models.annonce.AnnonceModel
import com.esgi.promocare_android.models.offer.OfferModel
import com.esgi.promocare_android.models.user.UserModel
import com.esgi.promocare_android.utils.handleDate
import com.esgi.promocare_android.utils.loadImage
import com.esgi.promocare_android.views.offer.company.CompanyOfferActivity.Companion.ANNONCE
import com.esgi.promocare_android.views.offer.company.CompanyOfferActivity.Companion.OFFER
import com.esgi.promocare_android.views.offer.company.CompanyOfferActivity.Companion.USER

class CompanyOfferDetailActivity:AppCompatActivity() {
    private lateinit var imageAnnonce : ImageView
    private lateinit var titleAnnonce : TextView
    private lateinit var descriptionAnnonce : TextView
    private lateinit var dateAnnonce : TextView
    private lateinit var offerText : TextView
    private lateinit var sender : TextView
    private lateinit var refuseButton : Button
    private lateinit var acceptButton : Button

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
        offerText = findViewById(R.id.offer_detail_offer_company)
        sender = findViewById(R.id.detail_offer_company_sender_text_view)
        refuseButton = findViewById(R.id.offer_detail_button_state_refuse_company)
        acceptButton = findViewById(R.id.offer_detail_button_state_accept_company)
    }

    private fun getIntentExtra(){
        if (this.intent.hasExtra(ANNONCE)) {
            this.annonce = IntentCompat.getParcelableExtra(this.intent, ANNONCE,AnnonceModel::class.java)!!
        }
        if (this.intent.hasExtra(OFFER)) {
            this.offer = IntentCompat.getParcelableExtra(this.intent, OFFER,OfferModel::class.java)!!
        }
        if (this.intent.hasExtra(USER)) {
            this.user = IntentCompat.getParcelableExtra(this.intent, USER,UserModel::class.java)!!
        }
    }

    private fun setText(){
        if(offer.status == "pending"){
            refuseButton.visibility = Button.GONE
            acceptButton.visibility = Button.GONE
        }
        if(offer.status == "accepted"){
            refuseButton.isEnabled = false
            acceptButton.isEnabled = false
            refuseButton.visibility = Button.GONE

        }
        if(offer.status == "refused"){
            refuseButton.isEnabled = false
            acceptButton.isEnabled = false
            acceptButton.visibility = Button.GONE
        }

        loadImage(imageAnnonce,annonce.type)
        sender.text = buildString {
            append("Envoyé par ")
            append(user.firstName)
            append(" ")
            append(user.lastName)
        }
        titleAnnonce.text = annonce.title
        descriptionAnnonce.text = annonce.description
        if(annonce.createdAt != null){
            dateAnnonce.text = handleDate(annonce.createdAt!!)
        }
        else{
            dateAnnonce.text = getString(R.string.date_inconnue)
        }
        var offerDate = "Date inconnue"
        if(offer.createdAt != null){
            offerDate = handleDate(offer.createdAt!!)
        }

        val text = "Envoyé à ${user.firstName} ${user.lastName} le $offerDate , le status est ${offer.status}. Voici un résumé de votre offre : \n\n"
        offerText.text = buildString {
            append(text)
            append(offer.text)
        }
    }
}