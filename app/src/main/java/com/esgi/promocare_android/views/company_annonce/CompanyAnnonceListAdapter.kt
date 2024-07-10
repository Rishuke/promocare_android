package com.esgi.promocare_android.views.company_annonce


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.esgi.promocare_android.R
import com.esgi.promocare_android.models.annonce.AnnonceModel
import com.esgi.promocare_android.utils.handleDate

class CompanyAnnonceListAdapter(var annonces:MutableList<AnnonceModel>): RecyclerView.Adapter<CompanyAnnonceListAdapter.AnnonceViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnonceViewHolder {
        val ingredientView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_layout_annonce, parent, false)
        return AnnonceViewHolder(ingredientView)
    }

    override fun getItemCount(): Int {
        return this.annonces.size
    }

    override fun onBindViewHolder(holder: AnnonceViewHolder, position: Int) {
        val currentIngredientData = this.annonces[position]

        holder.bind(currentIngredientData)
        /*holder.itemView.setOnClickListener {
            holder.updateBackgroundLayout(currentIngredientData)
        }*/
    }

    inner class AnnonceViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var annonceTitle : TextView
        private var annoncePrice : TextView
        private var reduction : TextView
        private var date : TextView
        private var views : TextView

        init {
            this.annonceTitle = itemView.findViewById(R.id.cell_layout_annonce_title)
            this.annoncePrice = itemView.findViewById(R.id.cell_layout_annonce_price)
            this.reduction = itemView.findViewById(R.id.cell_layout_annonce_reduction)
            this.date = itemView.findViewById(R.id.cell_layout_annonce_date)
            this.views = itemView.findViewById(R.id.cell_layout_company_annonce_views)
        }

        fun bind(annonce:AnnonceModel) {
            this.annonceTitle.text = annonce.title
            if(annonce.price != null && annonce.promo != null) {
                val pricePromo = annonce.price * (1 - (annonce.promo / 100.0)) // Ensure floating-point division
                val formattedPrice = String.format("%.2f€", pricePromo)
                this.annoncePrice.text = formattedPrice
            }
            this.reduction.text = " -${annonce.promo.toString()}%!!!"
            this.date.text = "Créer le ${handleDate(annonce.createdAt.toString())}"
            if(annonce.viewTime == 0){
                this.views.text = "0"
            }
            else{
                this.views.text = annonce.viewTime.toString()
            }
        }
    }
}