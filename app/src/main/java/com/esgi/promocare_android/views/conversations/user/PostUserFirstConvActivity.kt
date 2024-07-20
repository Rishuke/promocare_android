package com.esgi.promocare_android.views.conversations.user

import android.os.Bundle
import android.util.Log
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
import com.esgi.promocare_android.views.user_annonce.AnnonceUserDetailActivity

class PostUserFirstConvActivity: AppCompatActivity(){

    //for API request
    private lateinit var annonceId : String
    private lateinit var convId : String

    //layout and view
    private lateinit var conversationRecyclerView: RecyclerView
    private lateinit var conversationListAdapter: ConversationListAdapter
    private lateinit var noResultTextView: TextView
    private lateinit var messageEditText : EditText
    private lateinit var sendButton : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.conversation_layout)
        getIntentExtra()
        setUpView()
        handleSend()
        //Conversation.getPostFirstConvUserViewModel().verifyNoConv(Credential.token, annonceId,noResultTextView)
        Conversation.getPostFirstConvUserViewModel().getConvId(Credential.token,"03c73fb5-3c64-4bda-b738-12e707b458dd",noResultTextView)
        observeRecyclerView()
    }

    private fun setUpView(){
        noResultTextView = findViewById(R.id.no_result_conv_text_view)
        messageEditText = findViewById(R.id.message_edit_text)
        sendButton = findViewById(R.id.send_button)
        conversationRecyclerView = findViewById(R.id.conversation_recycler_view)
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
            if(Conversation.getPostFirstConvUserViewModel().conversationList.value.isNullOrEmpty()){
                Log.d("ERROR IS EMPTY","conversationList is null")
                val messageToPost = PostConversationDto(messageEditText.text.toString())
                Conversation.getPostFirstConvUserViewModel().postConvFirstUser(Credential.token,messageToPost,annonceId,noResultTextView)
                return@setOnClickListener
            }

        }
    }

    private fun getIntentExtra(){
        if (this.intent.hasExtra(AnnonceUserDetailActivity.ANNONCE_ID)) {
            this.annonceId = intent.getStringExtra(AnnonceUserDetailActivity.ANNONCE_ID)!!
        }
    }
}