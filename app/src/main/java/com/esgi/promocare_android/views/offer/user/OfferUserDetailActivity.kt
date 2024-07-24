package com.esgi.promocare_android.views.offer.user

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
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
    private lateinit var offerText : TextView
    private lateinit var senderText : TextView

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
        offerText = findViewById(R.id.offer_detail_offer_user)
        senderText = findViewById(R.id.offer_detail_sender_user)

        refuse = findViewById(R.id.offer_detail_refuse_user)
        accept = findViewById(R.id.offer_detail_accept_user)
    }

    private fun getIntentExtra(){
        if (this.intent.hasExtra(ANNONCE)) {
            this.annonce = IntentCompat.getParcelableExtra(this.intent, ANNONCE,AnnonceModel::class.java)!!
        }
        if (this.intent.hasExtra(OFFER)) {
            this.offer = IntentCompat.getParcelableExtra(this.intent, OFFER,OfferModel::class.java)!!
        }
        if (this.intent.hasExtra(COMPANY)) {
            this.company = IntentCompat.getParcelableExtra(this.intent, COMPANY,CompanyModel::class.java)!!
        }
    }

    private fun setText(){
        loadImage(imageAnnonce,annonce.type)
        if(offer.status == "accepted"){
            refuse.isEnabled = false
            refuse.visibility = Button.GONE
            accept.isEnabled = false
            accept.text = getString(R.string.annonce_accept)
        }
        if(offer.status == "refused"){
            refuse.isEnabled = false
            accept.visibility = Button.GONE
            accept.isEnabled = false
            refuse.text = getString(R.string.annonce_refus)
        }
        titleAnnonce.text = annonce.title
        senderText.text = company.companyName
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
        val textStatus: String = when (offer.status) {
            "pending" -> {
                "en attente d'une réponse"
            }

            "accepted" -> {
                "acceptée"
            }

            "refused" -> {
                "refusée"
            }

            else -> {
                "inconnue"
            }
        }

        val text = "Envoyé par ${company.companyName} le $offerDate , l'offre est ${textStatus}. Voici un résumé de l'offre : \n\n"
        //statusAnnonce.text = text
        offerText.text = buildString {
            append(text)
            append(offer.text)
        }
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