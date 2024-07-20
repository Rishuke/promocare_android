package com.esgi.promocare_android.views.conversations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.esgi.promocare_android.R
import com.esgi.promocare_android.models.conversations.LatestConv
import com.esgi.promocare_android.utils.handleDate

class LatestConvAdapter(var latestConv:MutableList<LatestConv>): RecyclerView.Adapter<LatestConvAdapter.LatestConvViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestConvViewHolder {
        val latestConvView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_layout_latest_conv, parent, false)
        return LatestConvViewHolder(latestConvView)
    }

    override fun getItemCount(): Int {
        return this.latestConv.size
    }

    override fun onBindViewHolder(holder: LatestConvViewHolder, position: Int) {
        val currentConversationData = this.latestConv[position]

        holder.bind(currentConversationData)
        /**holder.itemView.setOnClickListener {
        annonceUserClickHander.viewDetailAnnonceUser(currentIngredientData,position)
        }**/
    }

    inner class LatestConvViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var annonceTitle : TextView
        private var messageContent : TextView
        private var dateLastMessage : TextView

        init {
            annonceTitle = itemView.findViewById(R.id.cell_layout_latest_conv_title_annonce)
            messageContent = itemView.findViewById(R.id.cell_layout_latest_conv_last_message)
            dateLastMessage = itemView.findViewById(R.id.cell_layout_latest_conv_date)
        }

        fun bind(latest: LatestConv) {
            annonceTitle.text = latest.annonce?.title ?: "Pas de titre d'annonce"
            messageContent.text = latest.conversation?.message ?: "Pas de message"
            var date  = handleDate(latest.conversation?.created_at ?: "Pas de date")
            date += " à " + latest.conversation?.created_at?.substring(11, 16)
            dateLastMessage.text = date
        }
    }
}