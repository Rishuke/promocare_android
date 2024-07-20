package com.esgi.promocare_android.views.conversations.company

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esgi.promocare_android.R
import com.esgi.promocare_android.data.Conversation
import com.esgi.promocare_android.models.conversations.ConvFrom
import com.esgi.promocare_android.models.conversations.PostConversationDto
import com.esgi.promocare_android.network.Credential
import com.esgi.promocare_android.views.conversations.ConversationListAdapter

class ConversationCompanyActivity:AppCompatActivity() {

    //for API request
    private lateinit var convId : String
    private lateinit var annonceId : String

    //layout and view
    private lateinit var conversationRecyclerView: RecyclerView
    private lateinit var conversationListAdapter: ConversationListAdapter
    private lateinit var noResultTextView: TextView
    private lateinit var messageEditText : EditText
    private lateinit var sendButton : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation_company)
        getIntentExtra()
        setUpView()
        handleSend()
        Conversation.getPostFirstConvUserViewModel().getConvId(Credential.token, convId,noResultTextView)
        observeRecyclerView()
    }

    private fun setUpView(){
        noResultTextView = findViewById(R.id.no_result_conv_text_view_company)
        messageEditText = findViewById(R.id.message_edit_text_company)
        sendButton = findViewById(R.id.send_button_company)
        conversationRecyclerView = findViewById(R.id.conversation_recycler_view_company)
    }

    private fun setRecyclerView(conversations : MutableList<ConvFrom>){
        conversationListAdapter = ConversationListAdapter(conversations)

        conversationRecyclerView.layoutManager = GridLayoutManager(this, 1)

        conversationRecyclerView.adapter = conversationListAdapter
    }

    private fun observeRecyclerView() {
        Conversation.getPostFirstConvUserViewModel().conversationList.observe(this) { conversations ->
            this.setRecyclerView(conversations)
        }
    }

    private fun handleSend(){
        sendButton.setOnClickListener {
            if(messageEditText.text.isEmpty()){
                return@setOnClickListener
            }
            val messageToPost = PostConversationDto(messageEditText.text.toString())
            Conversation.getPostFirstConvUserViewModel().postConv(Credential.token,messageToPost,annonceId,noResultTextView)
        }
    }

    private fun getIntentExtra(){
        if (this.intent.hasExtra(LatestConvCompanyActivity.CONV_ID)) {
            this.convId = intent.getStringExtra(LatestConvCompanyActivity.CONV_ID)!!
        }
        if (this.intent.hasExtra(LatestConvCompanyActivity.ANNONCE_ID)) {
            this.annonceId = intent.getStringExtra(LatestConvCompanyActivity.ANNONCE_ID)!!
        }

        Conversation.getPostFirstConvUserViewModel().convId = this.convId
    }
}