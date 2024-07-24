package com.esgi.promocare_android.views.conversations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.esgi.promocare_android.R
import com.esgi.promocare_android.models.annonce.AnnonceDto
import com.esgi.promocare_android.models.conversations.LatestConv
import com.esgi.promocare_android.utils.handleDate
import com.esgi.promocare_android.utils.loadImage

class LatestConvAdapter(private var latestConv:MutableList<LatestConv>, private var convClickHanlder:ShowAllConv): RecyclerView.Adapter<LatestConvAdapter.LatestConvViewHolder>() {
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
        val annonce = currentConversationData.annonce?.uuid ?: return
        holder.itemView.setOnClickListener {
            if(currentConversationData.conversation?.firstConvId == null){
                convClickHanlder.showAllConv(currentConversationData.conversation?.uuid!!, annonce, currentConversationData.annonce)
                return@setOnClickListener
            }
            else{
                convClickHanlder.showAllConv(currentConversationData.conversation.firstConvId, annonce, currentConversationData.annonce)
            }
        }
    }

    inner class LatestConvViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var annonceTitle : TextView = itemView.findViewById(R.id.cell_layout_latest_conv_title_annonce)
        private var messageContent : TextView = itemView.findViewById(R.id.cell_layout_latest_conv_last_message)
        private var dateLastMessage : TextView = itemView.findViewById(R.id.cell_layout_latest_conv_date)
        private var annonceImage : ImageView = itemView.findViewById(R.id.cell_layout_latest_conv_image)

        fun bind(latest: LatestConv) {
            loadImage(annonceImage,latest.annonce?.type)
            annonceTitle.text = latest.annonce?.title ?: "Pas de titre d'annonce"
            if(latest.conversation?.from == "Not you"){
                messageContent.text = buildString {
                    append("Pour vous : ")
                    append(latest.conversation.message)
                }
            }
            else{
                messageContent.text = buildString {
                    append("Vous : ")
                    append(latest.conversation?.message)
                }
            }
            var date  = handleDate(latest.conversation?.createdAt ?: "Pas de date")
            date += " à " + latest.conversation?.createdAt?.substring(11, 16)
            dateLastMessage.text = date
        }
    }
}

interface ShowAllConv{
    fun showAllConv(convId:String,annonceId:String,annonce : AnnonceDto)
}