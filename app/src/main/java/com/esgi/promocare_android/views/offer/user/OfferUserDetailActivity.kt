package com.esgi.promocare_android.views.offer.user

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.esgi.promocare_android.R
import com.esgi.promocare_android.data.Offer
import com.esgi.promocare_android.models.annonce.AnnonceModel
import com.esgi.promocare_android.models.company.CompanyModel
import com.esgi.promocare_android.models.offer.OfferModel
import com.esgi.promocare_android.models.offer.PatchOffer
import com.esgi.promocare_android.network.Credential
import com.esgi.promocare_android.utils.handleDate
import com.esgi.promocare_android.utils.loadImage
import com.esgi.promocare_android.views.offer.company.CompanyOfferActivity.Companion.ANNONCE
import com.esgi.promocare_android.views.offer.company.CompanyOfferActivity.Companion.OFFER
import com.esgi.promocare_android.views.offer.user.UserOfferActivity.Companion.COMPANY

class OfferUserDetailActivity:AppCompatActivity() {
    private lateinit var imageAnnonce : ImageView
    private lateinit var titleAnnonce : TextView
    private lateinit var descriptionAnnonce : TextView
    private lateinit var dateAnnonce : TextView
    private lateinit var statusAnnonce : TextView
    private lateinit var offerText : TextView

    //data
    private lateinit var offer : OfferModel
    private lateinit var annonce : AnnonceModel
    private lateinit var company : CompanyModel

    //requestAPI
    private lateinit var refuse : Button
    private lateinit var accept : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offer_detail_user)
        setUpView()
        getIntentExtra()
        setText()
        handleRefuse()
        handleAccept()
    }

    private fun setUpView(){
        imageAnnonce = findViewById(R.id.offer_detail_image_user)
        titleAnnonce = findViewById(R.id.offer_detail_annonce_tilte_user)
        descriptionAnnonce = findViewById(R.id.offer_detail_description_annonce_user)
        dateAnnonce = findViewById(R.id.offer_detail_annonce_date_user)
        statusAnnonce = findViewById(R.id.offer_detail_send_to_user)
        offerText = findViewById(R.id.offer_detail_offer_user)

        refuse = findViewById(R.id.offer_detail_refuse_user)
        accept = findViewById(R.id.offer_detail_accept_user)
    }

    private fun getIntentExtra(){
        if (this.intent.hasExtra(ANNONCE)) {
            this.annonce = intent.getParcelableExtra(ANNONCE)!!
        }
        if (this.intent.hasExtra(OFFER)) {
            this.offer = intent.getParcelableExtra(OFFER)!!
        }
        if (this.intent.hasExtra(COMPANY)) {
            this.company = intent.getParcelableExtra(COMPANY)!!
        }
    }

    private fun setText(){
        loadImage(imageAnnonce,annonce.type)
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

        var textStatus = ""
        if(offer.status == "pending"){
            textStatus = "en attente d'une réponse"
        }
        else if(offer.status == "accepted"){
            textStatus = "acceptée"
        }
        else if(offer.status == "refused"){
            textStatus = "refusée"
        }
        else{
            textStatus = "inconnue"
        }

        val text = "Envoyé par ${company.company_name} le ${offerDate} , l'offre est ${textStatus}. Voici un résumé de l'offre :"
        statusAnnonce.text = text
        offerText.text = offer.text
    }

    private fun handleRefuse() {
        val body = PatchOffer("refused")
        refuse.setOnClickListener {
            if(offer.uuid == null){
                startActivity(Intent(this, UserOfferActivity::class.java))
                return@setOnClickListener
            }
            Offer.getOfferUserViewModel().patchOffer(Credential.token, body, offer.uuid!!)
            startActivity(Intent(this, UserOfferActivity::class.java))
        }
    }

    private fun handleAccept() {
        val body = PatchOffer("accepted")
        accept.setOnClickListener {
            if(offer.uuid == null){
                startActivity(Intent(this, UserOfferActivity::class.java))
                return@setOnClickListener
            }
            Offer.getOfferUserViewModel().patchOffer(Credential.token, body, offer.uuid!!)
            startActivity(Intent(this, UserOfferActivity::class.java))
        }
    }
}