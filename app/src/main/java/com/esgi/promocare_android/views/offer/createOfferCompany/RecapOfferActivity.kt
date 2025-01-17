package com.esgi.promocare_android.views.offer.createOfferCompany

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.esgi.promocare_android.R
import com.esgi.promocare_android.data.Offer
import com.esgi.promocare_android.models.offer.PostOfferModel
import com.esgi.promocare_android.network.Credential
import com.esgi.promocare_android.views.company_annonce.CompanyAnnonceActivity

class RecapOfferActivity:AppCompatActivity() {
    private lateinit var dateStart:TextView
    private lateinit var dateEnd : TextView
    private lateinit var frequence : TextView
    private lateinit var location : TextView
    private lateinit var price : TextView
    private lateinit var commentaire : EditText
    private lateinit var createOffer : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_offer_recap)
        setUpView()
        fillChamp()
        handleSend()
    }

    private fun setUpView(){
        dateStart = findViewById(R.id.company_offer_recap_date_start_text_view)
        dateEnd = findViewById(R.id.company_offer_recap_date_end_text_view)
        frequence = findViewById(R.id.company_offer_recap_frequency_text_view)
        location = findViewById(R.id.company_offer_recap_location_text_view)
        price = findViewById(R.id.company_offer_recap_price_text_view)
        commentaire = findViewById(R.id.company_offer_recap_comment_edit_text)
        createOffer = findViewById(R.id.company_offer_create_button)
    }

    private fun fillChamp(){
        val model = Offer.getCreateOfferCompanyViewModel()
        if(model.startDate == ""){
            dateStart.text = getString(R.string.non_renseign)
        }
        else{
            dateStart.text = model.startDate
        }
        if(model.endDate == ""){
            dateEnd.text = getString(R.string.non_renseign)
        }
        else{
            dateEnd.text = model.endDate
        }
        if(model.frequence == ""){
            frequence.text = getString(R.string.non_renseign)
        }
        else{
            frequence.text = model.frequence
        }
        if(model.location == ""){
            location.text = getString(R.string.non_renseign)
        }
        else{
            location.text = model.location
        }
        if(model.price == ""){
            if(model.nbSeance == ""){
                price.text = getString(R.string.non_renseign_non_renseign)
            }
            else{
                price.text = buildString {
                    append("Non renseigné - ")
                    append(model.nbSeance)
                }
            }
        }
        else{
            if(model.nbSeance == ""){
                price.text = buildString {
                    append(model.price)
                    append(" - Non renseigné")
                }
            }
            else{
                price.text = buildString {
                    append(model.price)
                    append(" - ")
                    append(model.nbSeance)
                }
            }
        }
    }

    private fun handleSend(){
        createOffer.setOnClickListener {
            val model = Offer.getCreateOfferCompanyViewModel()
            model.commentaire = commentaire.text.toString()

            val text:String = "Date de début : ${dateStart.text}\n" +
                    "Date de fin : ${dateEnd.text}\n\n" +
                    "Fréquence : ${frequence.text}\n\n" +
                    "Lieu : ${location.text}\n\n" +
                    "Prix et total seance : ${price.text}\n\n" +
                    "Commentaire : ${model.commentaire}"


            val postOfferModel = PostOfferModel(
                model.annonceId,
                model.userId,
                text
            )

            Offer.getCreateOfferCompanyViewModel().createOffer(Credential.token,postOfferModel)

            val intent = Intent(this, CompanyAnnonceActivity::class.java)
            startActivity(intent)
        }
    }
}