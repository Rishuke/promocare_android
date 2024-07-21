package com.esgi.promocare_android.views.conversations.user

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esgi.promocare_android.R
import com.esgi.promocare_android.data.Conversation
import com.esgi.promocare_android.models.annonce.AnnonceModel
import com.esgi.promocare_android.models.conversations.ConvFrom
import com.esgi.promocare_android.models.conversations.PostConversationDto
import com.esgi.promocare_android.network.Credential
import com.esgi.promocare_android.utils.handleDate
import com.esgi.promocare_android.utils.loadImage
import com.esgi.promocare_android.views.conversations.ConversationListAdapter
import com.esgi.promocare_android.views.user_annonce.AnnonceUserDetailActivity

class PostUserFirstConvActivity: AppCompatActivity(){

    //for API request
    private lateinit var annonceId : String
    private lateinit var annonce:AnnonceModel

    //layout and view
    private lateinit var conversationRecyclerView: RecyclerView
    private lateinit var conversationListAdapter: ConversationListAdapter
    private lateinit var noResultTextView: TextView
    private lateinit var messageEditText : EditText
    private lateinit var sendButton : ImageView

    private lateinit var annonceTitle:TextView
    private lateinit var annonceDate:TextView
    private lateinit var annonceImage:ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation_layout_user)
        getIntentExtra()
        setUpView()
        handleSend()
        Conversation.getPostFirstConvUserViewModel().verifyNoConv(Credential.token, annonceId,noResultTextView)
        setAnnonce()
        observeRecyclerView()
    }

    private fun setUpView(){
        noResultTextView = findViewById(R.id.no_result_conv_text_view)
        messageEditText = findViewById(R.id.message_edit_text)
        sendButton = findViewById(R.id.send_button)
        conversationRecyclerView = findViewById(R.id.conversation_recycler_view)
        annonceImage = findViewById(R.id.annonce_conversation_image_view_user)
        annonceTitle = findViewById(R.id.annonce_conversation_title_text_view_user)
        annonceDate = findViewById(R.id.annonce_conversation_date_text_view_user)
    }

    private fun setRecyclerView(conversations : MutableList<ConvFrom>){
        conversationListAdapter = ConversationListAdapter(conversations)

        conversationRecyclerView.layoutManager = GridLayoutManager(this, 1)

        conversationRecyclerView.adapter = conversationListAdapter
    }

    private fun setAnnonce(){
        annonceTitle.text = annonce.title
        val date = annonce.createdAt
        if(date != null){
            val formattedDate = handleDate(date)
            annonceDate.text = formattedDate
        }
        else{
            annonceDate.text = "Date inconnue"
        }
        loadImage(annonceImage,annonce.type)
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
                val messageToPost = PostConversationDto(messageEditText.text.toString())
                messageEditText.text.clear()
                Conversation.getPostFirstConvUserViewModel().postConvFirstUser(Credential.token,messageToPost,annonceId,noResultTextView)
                return@setOnClickListener
            }
            val messageToPost = PostConversationDto(messageEditText.text.toString())
            messageEditText.text.clear()
            Conversation.getPostFirstConvUserViewModel().postConv(Credential.token,messageToPost,annonceId,noResultTextView)
        }
    }

    private fun getIntentExtra(){
        if (this.intent.hasExtra(LatestConvUserAcitivity.ANNONCE)) {
            this.annonce = intent.getParcelableExtra(LatestConvUserAcitivity.ANNONCE)!!
            this.annonceId = annonce.uuid!!
        }
        if(this.intent.hasExtra(AnnonceUserDetailActivity.ANNONCE)){
            this.annonce = intent.getParcelableExtra(AnnonceUserDetailActivity.ANNONCE)!!
            this.annonceId = annonce.uuid!!
        }
    }
}