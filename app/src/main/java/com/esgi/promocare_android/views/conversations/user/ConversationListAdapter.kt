package com.esgi.promocare_android.views.conversations.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.esgi.promocare_android.R
import com.esgi.promocare_android.models.conversations.ConvFrom

class ConversationListAdapter(var conversations:MutableList<ConvFrom>): RecyclerView.Adapter<ConversationListAdapter.ConversationViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationViewHolder {
        val ingredientView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_layout_conversation, parent, false)
        return ConversationViewHolder(ingredientView)
    }

    override fun getItemCount(): Int {
        return this.conversations.size
    }

    override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) {
        val currentConversationData = this.conversations[position]

        holder.bind(currentConversationData)
        /**holder.itemView.setOnClickListener {
            annonceUserClickHander.viewDetailAnnonceUser(currentIngredientData,position)
        }**/
    }

    inner class ConversationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var targetSide : TextView
        private var senderSide : TextView

        init {
            targetSide = itemView.findViewById(R.id.conversation_target_side)
            senderSide = itemView.findViewById(R.id.conversation_sender_side)
        }

        fun bind(conversation: ConvFrom) {
            senderSide.visibility = View.VISIBLE
            targetSide.visibility = View.VISIBLE
            if(conversation.from == "Not you"){
                targetSide.text = conversation.message
                senderSide.visibility = View.INVISIBLE
            }
            else{
                senderSide.text = conversation.message
                targetSide.visibility = View.INVISIBLE
            }
        }
    }
}