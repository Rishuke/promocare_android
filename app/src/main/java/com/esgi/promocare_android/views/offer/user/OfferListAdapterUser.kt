package com.esgi.promocare_android.views.offer.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esgi.promocare_android.R
import com.esgi.promocare_android.data.Offer
import com.esgi.promocare_android.models.offer.GetOfferCompany
import com.esgi.promocare_android.models.offer.GetOfferUser
import com.esgi.promocare_android.network.Credential
import com.esgi.promocare_android.utils.handleDate
import com.esgi.promocare_android.utils.loadImage
import com.esgi.promocare_android.views.offer.company.DisplayCompanyDetail
import com.esgi.promocare_android.views.offer.company.OfferListAdapterCompany

class OfferListAdapterUser(var offers:MutableList<GetOfferUser>,var detailOffer:DetailOfferUserClickHandler): RecyclerView.Adapter<OfferListAdapterUser.OfferUserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferUserViewHolder {
        val offerView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_layout_offer, parent, false)
        return OfferUserViewHolder(offerView)
    }

    override fun getItemCount(): Int {
        return this.offers.size
    }

    override fun onBindViewHolder(holder: OfferUserViewHolder, position: Int) {
        val currentConversationData = this.offers[position]

        holder.bind(currentConversationData)
        holder.itemView.setOnClickListener {
            detailOffer.viewDetailOfferUser(currentConversationData)
        }
    }

    inner class OfferUserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var imageAnnonce : ImageView
        private var annonceTitle : TextView
        private var companyName : TextView
        private var date : TextView

        init {
            imageAnnonce = itemView.findViewById(R.id.offer_image_view_company)
            annonceTitle = itemView.findViewById(R.id.offer_anonce_title_text_view_company)
            companyName = itemView.findViewById(R.id.offer_company_name_text_view_company)
            date = itemView.findViewById(R.id.offer_date_text_view_company)
        }

        fun bind(offer : GetOfferUser) {
            loadImage(imageAnnonce,offer.annonce.type)
            annonceTitle.text = offer.annonce.title
            companyName.text = offer.company.company_name

            if(offer.offer.created_at!=null){
                date.text = handleDate(offer.offer.created_at)
            }
            else{
                date.text = "Date inconnue"
            }
        }
    }
}

interface DetailOfferUserClickHandler {
    fun viewDetailOfferUser(offer: GetOfferUser)
}