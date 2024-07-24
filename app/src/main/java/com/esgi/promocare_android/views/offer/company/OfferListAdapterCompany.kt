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

class OfferListAdapterCompany(private var offers:MutableList<GetOfferCompany>, private val offerClickHander: DisplayCompanyDetail): RecyclerView.Adapter<OfferListAdapterCompany.OfferViewHolder>() {
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
        private var imageAnnonce : ImageView = itemView.findViewById(R.id.offer_image_view_company)
        private var annonceTitle : TextView = itemView.findViewById(R.id.offer_anonce_title_text_view_company)
        private var companyName : TextView = itemView.findViewById(R.id.offer_company_name_text_view_company)
        private var date : TextView = itemView.findViewById(R.id.offer_date_text_view_company)

        fun bind(offer : GetOfferCompany) {
            loadImage(imageAnnonce,offer.annonce.type)
            annonceTitle.text = offer.annonce.title
            companyName.text = buildString {
                append("Envoyer à ")
                append(offer.user.lastName)
                append(" ")
                append(offer.user.firstName)
            }
            if(offer.offer.createdAt!=null){
                date.text = handleDate(offer.offer.createdAt)
            }
            else{
                date.text = itemView.context.getString(R.string.date_inconnu_within_inner_class)
            }
        }
    }
}

interface DisplayCompanyDetail {
    fun viewDetailOfferCompany(offer: GetOfferCompany)
}