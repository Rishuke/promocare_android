package com.esgi.promocare_android.views.conversations.company

import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esgi.promocare_android.R
import com.esgi.promocare_android.data.Conversation
import com.esgi.promocare_android.models.annonce.AnnonceDto
import com.esgi.promocare_android.models.annonce.AnnonceModel
import com.esgi.promocare_android.models.conversations.LatestConv
import com.esgi.promocare_android.network.Credential
import com.esgi.promocare_android.views.conversations.LatestConvAdapter
import com.esgi.promocare_android.views.conversations.ShowAllConv

class LatestConvCompanyActivity: AppCompatActivity(),ShowAllConv {
    companion object {
        const val CONV_ID = "CONV_ID"
        const val ANNONCE_ID = "ANNONCE_ID"
    }
    private lateinit var latestConvRecyclerView: RecyclerView
    private lateinit var latestConvAdapter: LatestConvAdapter

    //gestion erreur
    private lateinit var errorTextView: TextView
    private lateinit var loader: ProgressBar
    private lateinit var noResultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latest_conv)
        setUpView()
        Conversation.getLastConvViewModel().getLatestCompanyConv(Credential.token, loader, errorTextView,noResultTextView)
        observeRecyclerView()
    }

    private fun setUpView(){
        this.noResultTextView = findViewById(R.id.latest_conv_error)
        this.loader = findViewById(R.id.latest_conv_progress_bar)
        this.errorTextView = findViewById(R.id.latest_conv_no_result)
        this.latestConvRecyclerView = findViewById(R.id.latest_conv_recycler_view)
    }

    private fun setRecyclerView(lastestConv: MutableList<LatestConv>) {
        this.latestConvAdapter = LatestConvAdapter(lastestConv,this)

        this.latestConvRecyclerView.layoutManager = GridLayoutManager(this, 1)

        this.latestConvRecyclerView.setAdapter(latestConvAdapter)
    }

    private fun observeRecyclerView() {
        Conversation.getLastConvViewModel().latestConvList.observe(this) { latestConv ->
            this.setRecyclerView(latestConv)
        }
    }

    override fun showAllConv(convId: String, annonceId: String) {
        Intent(this, ConversationCompanyActivity::class.java).also {
            it.putExtra(CONV_ID,convId)
            it.putExtra(ANNONCE_ID,annonceId)
            startActivity(it)
        }
    }
}