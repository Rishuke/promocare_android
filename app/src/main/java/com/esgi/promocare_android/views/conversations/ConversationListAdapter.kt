package com.esgi.promocare_android.views.conversations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.esgi.promocare_android.R
import com.esgi.promocare_android.models.conversations.ConvFrom
import com.esgi.promocare_android.utils.handleDateShort

class ConversationListAdapter(var conversations:MutableList<ConvFrom>): RecyclerView.Adapter<ConversationListAdapter.ConversationViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationViewHolder {
        val conversationView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_layout_conversation, parent, false)
        return ConversationViewHolder(conversationView)
    }

    override fun getItemCount(): Int {
        return this.conversations.size
    }

    override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) {
        val currentConversationData = this.conversations[position]

        holder.bind(currentConversationData)
    }

    inner class ConversationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var targetSide : TextView = itemView.findViewById(R.id.conversation_target_side)
        private var targetSideDate : TextView = itemView.findViewById(R.id.conversation_target_side_date)
        private var senderSide : TextView = itemView.findViewById(R.id.conversation_sender_side)
        private var senderSideDate : TextView = itemView.findViewById(R.id.conversation_sender_side_date)

        fun bind(conversation: ConvFrom) {
            targetSide.visibility = TextView.INVISIBLE
            targetSideDate.visibility = TextView.INVISIBLE
            senderSide.visibility = TextView.INVISIBLE
            senderSideDate.visibility = TextView.INVISIBLE
            if(conversation.from == "You"){
                senderSide.text = conversation.message
                senderSide.visibility = TextView.VISIBLE

                if(conversation.createdAt != null){
                    var date = handleDateShort(conversation.createdAt) +" à "
                    date += conversation.createdAt.substring(11,16)
                    senderSideDate.text = date
                    senderSideDate.visibility = TextView.VISIBLE
                }
            }
            else if(conversation.from == "Not you"){
                targetSide.text = conversation.message
                targetSide.visibility = TextView.VISIBLE
                if(conversation.createdAt != null){
                    var date = handleDateShort(conversation.createdAt) +" à "
                    date += conversation.createdAt.substring(11,16)
                    targetSideDate.text = date
                    targetSideDate.visibility = TextView.VISIBLE
                }
            }
        }
    }
}