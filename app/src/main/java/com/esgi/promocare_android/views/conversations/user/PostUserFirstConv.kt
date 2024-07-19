package com.esgi.promocare_android.views.conversations.user

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.esgi.promocare_android.R
import com.esgi.promocare_android.data.Conversation
import com.esgi.promocare_android.models.conversations.PostConversationDto
import com.esgi.promocare_android.network.Credential
import com.esgi.promocare_android.views.user_annonce.AnnonceUserDetailActivity

class PostUserFirstConv: AppCompatActivity(){

    //for API request
    private lateinit var annonceId : String
    private lateinit var convId : String

    //layout and view
    private lateinit var conversationRecyclerView: RecyclerView
    private lateinit var noResultTextView: TextView
    private lateinit var messageEditText : EditText
    private lateinit var sendButton : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.conversation_layout)
        getIntentExtra()
        setUpView()
        Conversation.getPostFirstConvUserViewModel().verifyNoConv(Credential.token, annonceId,noResultTextView)
        handleSend()
    }

    private fun setUpView(){
        noResultTextView = findViewById(R.id.no_result_conv_text_view)
        messageEditText = findViewById(R.id.message_edit_text)
        sendButton = findViewById(R.id.send_button)
        conversationRecyclerView = findViewById(R.id.conversation_recycler_view)
    }

    private fun handleSend(){
        sendButton.setOnClickListener {
            Log.d("SLEEP", "handleSend: ")
            if(messageEditText.text.isEmpty()){
                return@setOnClickListener
            }
            if(Conversation.getPostFirstConvUserViewModel().conversationList.value.isNullOrEmpty()){
                val messageToPost = PostConversationDto(messageEditText.text.toString())
                Conversation.getPostFirstConvUserViewModel().postConvFirstUser(Credential.token,messageToPost,annonceId)
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