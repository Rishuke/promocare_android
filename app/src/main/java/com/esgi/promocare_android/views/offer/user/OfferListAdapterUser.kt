package com.esgi.promocare_android.views.offer.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.esgi.promocare_android.R
import com.esgi.promocare_android.models.offer.GetOfferUser
import com.esgi.promocare_android.utils.handleDate
import com.esgi.promocare_android.utils.loadImage

class OfferListAdapterUser(private var offers:MutableList<GetOfferUser>, private var detailOffer:DetailOfferUserClickHandler): RecyclerView.Adapter<OfferListAdapterUser.OfferUserViewHolder>() {
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
        private var imageAnnonce : ImageView = itemView.findViewById(R.id.offer_image_view_company)
        private var annonceTitle : TextView = itemView.findViewById(R.id.offer_anonce_title_text_view_company)
        private var companyName : TextView = itemView.findViewById(R.id.offer_company_name_text_view_company)
        private var date : TextView = itemView.findViewById(R.id.offer_date_text_view_company)

        fun bind(offer : GetOfferUser) {
            loadImage(imageAnnonce,offer.annonce.type)
            annonceTitle.text = offer.annonce.title
            companyName.text = offer.company.companyName

            if(offer.offer.createdAt!=null){
                date.text = handleDate(offer.offer.createdAt)
            }
            else{
                date.text = itemView.context.getString(R.string.date_inconnue)
            }
        }
    }
}

interface DetailOfferUserClickHandler {
    fun viewDetailOfferUser(offer: GetOfferUser)
}