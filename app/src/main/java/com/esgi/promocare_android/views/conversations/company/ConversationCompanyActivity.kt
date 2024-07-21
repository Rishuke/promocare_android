package com.esgi.promocare_android.views.conversations.company

import android.content.Intent
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
import com.esgi.promocare_android.data.Offer
import com.esgi.promocare_android.models.conversations.ConvFrom
import com.esgi.promocare_android.models.conversations.PostConversationDto
import com.esgi.promocare_android.network.Credential
import com.esgi.promocare_android.utils.handleDate
import com.esgi.promocare_android.utils.loadImage
import com.esgi.promocare_android.views.conversations.ConversationListAdapter
import com.esgi.promocare_android.views.offer.createOfferCompany.PostCompanyOfferDateActivity
import com.esgi.promocare_android.views.offer.createOfferCompany.PostCompanyOfferFrequencyActivity
import com.esgi.promocare_android.views.user_annonce.AnnonceUserDetailActivity

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


    private lateinit var annonceTitle:TextView
    private lateinit var annonceDate:TextView
    private lateinit var annonceImage:ImageView

    private lateinit var makeOffer : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation_company)
        getIntentExtra()
        setUpView()
        handleSend()
        handleMakeOffer()
        Conversation.getPostFirstConvUserViewModel().getConvId(Credential.token, convId,noResultTextView)
        observeRecyclerView()
    }

    private fun setUpView(){
        noResultTextView = findViewById(R.id.no_result_conv_text_view_company)
        messageEditText = findViewById(R.id.message_edit_text_company)
        sendButton = findViewById(R.id.send_button_company)
        conversationRecyclerView = findViewById(R.id.conversation_recycler_view_company)

        annonceImage = findViewById(R.id.annonce_conversation_image_view_company)
        annonceTitle = findViewById(R.id.annonce_conversation_title_text_view_company)
        annonceDate = findViewById(R.id.annonce_conversation_date_text_view_company)

        makeOffer = findViewById(R.id.conversation_company_offer_text_view)
    }

    private fun setRecyclerView(conversations : MutableList<ConvFrom>){
        conversationListAdapter = ConversationListAdapter(conversations)

        conversationRecyclerView.layoutManager = GridLayoutManager(this, 1)

        conversationRecyclerView.adapter = conversationListAdapter
    }

    private fun observeRecyclerView() {
        Conversation.getPostFirstConvUserViewModel().conversationList.observe(this) { conversations ->
            this.setRecyclerView(conversations)

            if(Conversation.getPostFirstConvUserViewModel().annonce != null){
                val annonce = Conversation.getPostFirstConvUserViewModel().annonce!!
                loadImage(annonceImage,annonce.type)
                annonceTitle.text = annonce.title
                val date = annonce.createdAt
                if(date != null){
                    val formattedDate = handleDate(date)
                    annonceDate.text = formattedDate
                }
                else{
                    annonceDate.text = "Date inconnue"
                }
            }
        }
    }

    private fun handleSend(){
        sendButton.setOnClickListener {
            if(messageEditText.text.isEmpty()){
                return@setOnClickListener
            }
            val messageToPost = PostConversationDto(messageEditText.text.toString())
            messageEditText.text.clear()
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

    private fun handleMakeOffer(){
        makeOffer.setOnClickListener {
            val userId = Conversation.getPostFirstConvUserViewModel().senderId
            Offer.getCreateOfferCompanyViewModel().userId = userId
            Offer.getCreateOfferCompanyViewModel().annonceId = annonceId
            Intent(this, PostCompanyOfferDateActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}