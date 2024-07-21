package com.esgi.promocare_android.views.company_annonce

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.esgi.promocare_android.R
import com.esgi.promocare_android.models.annonce.AnnonceModel
import com.esgi.promocare_android.network.Credential
import com.esgi.promocare_android.utils.handleDate
import com.esgi.promocare_android.utils.loadImage
import com.esgi.promocare_android.viewmodel.annonce.AnnonceCompanyViewModel

class CompanyAnnonceListAdapter(
    private var annonces: MutableList<AnnonceModel>,
    private val viewModel: AnnonceCompanyViewModel,
    private val context: Context,
    private val loader: ProgressBar,
    private val error: TextView
) : RecyclerView.Adapter<CompanyAnnonceListAdapter.AnnonceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnonceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_layout_annonce, parent, false)
        return AnnonceViewHolder(view)
    }

    override fun getItemCount(): Int {
        return annonces.size
    }

    override fun onBindViewHolder(holder: AnnonceViewHolder, position: Int) {
        val annonce = annonces[position]
        holder.bind(annonce)
    }

    inner class AnnonceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val annonceTitle: TextView = itemView.findViewById(R.id.cell_layout_annonce_title)
        private val annoncePrice: TextView = itemView.findViewById(R.id.cell_layout_annonce_price)
        private val reduction: TextView = itemView.findViewById(R.id.cell_layout_annonce_reduction)
        private val date: TextView = itemView.findViewById(R.id.cell_layout_annonce_date)
        private val views: TextView = itemView.findViewById(R.id.cell_layout_company_annonce_views)
        private val imageAnnonce:ImageView = itemView.findViewById(R.id.cell_layout_annonce_image)
        private val updateButton: Button = itemView.findViewById(R.id.modify_button_text_annonce)
        private val deleteButton: Button = itemView.findViewById(R.id.cell_layout_annonce_delete_button)

        fun bind(annonce: AnnonceModel) {
            if(annonce.type != null){
                loadImage(imageAnnonce, annonce.type)
            }
            annonceTitle.text = annonce.title
            if (annonce.price != null && annonce.promo != null) {
                val pricePromo = annonce.price * (1 - (annonce.promo / 100.0))
                val formattedPrice = String.format("%.2f€", pricePromo)
                annoncePrice.text = formattedPrice
            }
            reduction.text = " -${annonce.promo.toString()}%!!!"
            date.text = "Créer le ${handleDate(annonce.createdAt.toString())}"
            views.text = annonce.viewTime.toString()

            updateButton.setOnClickListener {
                val intent = Intent(context, UpdateAnnonceActivity::class.java)
                intent.putExtra("annonceId", annonce.uuid)
                intent.putExtra("title", annonce.title)
                intent.putExtra("description", annonce.description)
                intent.putExtra("price", annonce.price)
                intent.putExtra("type", annonce.type)
                intent.putExtra("location", annonce.location)
                intent.putExtra("promo", annonce.promo)
                context.startActivity(intent)
            }

            deleteButton.setOnClickListener {
                /**viewModel.deleteAnnonceCompany("Bearer " + Credential.token, annonce.uuid!!, loader, error)
                annonces.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
                notifyItemRangeChanged(adapterPosition, annonces.size)**/
            }
        }
    }

    fun setAnnonces(newAnnonces: MutableList<AnnonceModel>) {
        annonces = newAnnonces
        notifyDataSetChanged()
    }
}
