package com.esgi.promocare_android.views.conversations.user

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
import com.esgi.promocare_android.views.NavigationUtilUser
import com.esgi.promocare_android.views.conversations.LatestConvAdapter
import com.esgi.promocare_android.views.conversations.ShowAllConv
import com.google.android.material.bottomnavigation.BottomNavigationView

class LatestConvUserAcitivity : AppCompatActivity(), ShowAllConv {

    companion object {
        const val ANNONCE = "ANNONCE_EXTRA"
    }

    private lateinit var latestConvRecyclerView: RecyclerView
    private lateinit var latestConvAdapter: LatestConvAdapter

    // Gestion des erreurs
    private lateinit var errorTextView: TextView
    private lateinit var loader: ProgressBar
    private lateinit var noResultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latest_conv)
        setUpView()
        Conversation.getLastConvViewModel().getLatestUserConv(Credential.token, loader, errorTextView, noResultTextView)
        observeRecyclerView()
        setUpBottomNavigationView()
    }

    private fun setUpView() {
        noResultTextView = findViewById(R.id.latest_conv_no_result)
        loader = findViewById(R.id.latest_conv_progress_bar)
        errorTextView = findViewById(R.id.latest_conv_error)
        latestConvRecyclerView = findViewById(R.id.latest_conv_recycler_view)
    }

    private fun setRecyclerView(latestConv: MutableList<LatestConv>) {
        latestConvAdapter = LatestConvAdapter(latestConv, this)
        latestConvRecyclerView.layoutManager = GridLayoutManager(this, 1)
        latestConvRecyclerView.adapter = latestConvAdapter
    }

    private fun observeRecyclerView() {
        Conversation.getLastConvViewModel().latestConvList.observe(this) { latestConv ->
            setRecyclerView(latestConv)
        }
    }

    private fun setUpBottomNavigationView() {
        val bottomNavView: BottomNavigationView = findViewById(R.id.nav_view)
        NavigationUtilUser.setupBottomNavView(bottomNavView, this, R.id.ic_conversation)
    }

    override fun showAllConv(convId: String, annonceId: String,annonce: AnnonceDto) {
        val annonceModel = AnnonceModel(
            annonce.uuid,
            annonce.companyId,
            annonce.location,
            annonce.price,
            annonce.promo,
            annonce.status,
            annonce.title,
            annonce.description,
            annonce.type,
            annonce.viewTime,
            annonce.updatedAt,
            annonce.createdAt
        )

        Intent(this, PostUserFirstConvActivity::class.java).also {
            it.putExtra(ANNONCE, annonceModel)
            startActivity(it)
        }
    }
}
