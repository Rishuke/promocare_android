package com.esgi.promocare_android.views.offer.company

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.esgi.promocare_android.R
import com.esgi.promocare_android.models.offer.GetOfferCompany
import com.esgi.promocare_android.utils.handleDate
import com.esgi.promocare_android.utils.loadImage
import com.esgi.promocare_android.views.user_annonce.AnnonceUserOnClickListener

class OfferListAdapterCompany(var offers:MutableList<GetOfferCompany>,val offerClickHander: DisplayCompanyDetail): RecyclerView.Adapter<OfferListAdapterCompany.OfferViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val offerView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_layout_offer, parent, false)
        return OfferViewHolder(offerView)
    }

    override fun getItemCount(): Int {
        return this.offers.size
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val currentConversationData = this.offers[position]

        holder.bind(currentConversationData)
        holder.itemView.setOnClickListener {
            offerClickHander.viewDetailOfferCompany(currentConversationData)
        }
    }

    inner class OfferViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
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

        fun bind(offer : GetOfferCompany) {
            loadImage(imageAnnonce,offer.annonce.type)
            annonceTitle.text = offer.annonce.title
            companyName.text = "Envoyer à ${offer.user.last_name} ${offer.user.first_name}"
            if(offer.offer.created_at!=null){
                date.text = handleDate(offer.offer.created_at)
            }
            else{
                date.text = "Date inconnue"
            }
        }
    }
}

interface DisplayCompanyDetail {
    fun viewDetailOfferCompany(offer: GetOfferCompany)
}