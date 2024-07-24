package com.esgi.promocare_android.views.user_annonce

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.esgi.promocare_android.R
import com.esgi.promocare_android.models.annonce.AnnonceModel
import com.esgi.promocare_android.utils.handleDate
import com.esgi.promocare_android.utils.loadImage
import java.util.Locale

class AnnonceUserListAdapter(var annonces:MutableList<AnnonceModel>, private val annonceUserClickHander:AnnonceUserOnClickListener): RecyclerView.Adapter<AnnonceUserListAdapter.AnnonceUserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnonceUserViewHolder {
        val ingredientView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_layout_annonce_user, parent, false)
        return AnnonceUserViewHolder(ingredientView)
    }

    override fun getItemCount(): Int {
        return this.annonces.size
    }

    override fun onBindViewHolder(holder: AnnonceUserViewHolder, position: Int) {
        val currentIngredientData = this.annonces[position]

        holder.bind(currentIngredientData)
        holder.itemView.setOnClickListener {
            annonceUserClickHander.viewDetailAnnonceUser(currentIngredientData,position)
        }
    }

    inner class AnnonceUserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var annonceTitle : TextView = itemView.findViewById(R.id.cell_layout_annonce_title_user)
        private var annoncePrice : TextView = itemView.findViewById(R.id.cell_layout_annonce_price_user)
        private var reduction : TextView = itemView.findViewById(R.id.cell_layout_annonce_reduction_user)
        private var date : TextView = itemView.findViewById(R.id.cell_layout_annonce_date_user)
        private var annonceImage : ImageView = itemView.findViewById(R.id.cell_layout_annonce_image_user)

        fun bind(annonce: AnnonceModel) {
            this.annonceTitle.text = annonce.title

            if(annonce.price != null && annonce.promo != null) {
                val pricePromo = annonce.price * (1 - (annonce.promo / 100.0)) // Ensure floating-point division
                val formattedPrice = String.format(Locale.FRANCE,"%.2f€", pricePromo)
                this.annoncePrice.text = formattedPrice
            }
            this.reduction.text = buildString {
                append(" -")
                append(annonce.promo.toString())
                append("%!!!")
            }
            this.date.text = buildString {
                append("Créer le ")
                append(handleDate(annonce.createdAt.toString()))
            }

            if(annonce.type != null){
                loadImage(annonceImage,annonce.type)
            }
        }
    }
}

interface AnnonceUserOnClickListener {
    fun viewDetailAnnonceUser(annonce: AnnonceModel, position: Int)
}