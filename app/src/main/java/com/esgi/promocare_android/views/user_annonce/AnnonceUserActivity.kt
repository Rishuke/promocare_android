package com.esgi.promocare_android.views.user_annonce

import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esgi.promocare_android.R
import com.esgi.promocare_android.data.Annonce
import com.esgi.promocare_android.fragment.SearchBarFragment
import com.esgi.promocare_android.fragment.SearchBarHandler
import com.esgi.promocare_android.models.annonce.AnnonceModel
import com.esgi.promocare_android.network.Credential
import com.esgi.promocare_android.utils.searchInAnnonce
import com.esgi.promocare_android.views.NavigationUtilUser
import com.google.android.material.bottomnavigation.BottomNavigationView

class AnnonceUserActivity : AppCompatActivity(), SearchBarHandler, AnnonceUserOnClickListener {

    companion object {
        const val ANNONCE_MODEL_EXTRA = "ANNONCE_MODEL_EXTRA"
        const val POSITION = "POSITION"
    }

    private lateinit var searchBarFragment: SearchBarFragment
    private lateinit var annonceRecyclerView: RecyclerView
    private lateinit var userAnnonceAdapter: AnnonceUserListAdapter

    // Gestion des erreurs
    private lateinit var errorTextView: TextView
    private lateinit var loader: ProgressBar
    private lateinit var noResultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_annonce)
        setUpView()
        Annonce.getUserViewModel().getAllAnnonce(Credential.token, loader, errorTextView)
        observeRecyclerView()
        setUpBottomNavigationView()
    }

    private fun setUpView() {
        searchBarFragment = SearchBarFragment.newInstance()
        annonceRecyclerView = findViewById(R.id.user_annonce_recycler_view)
        noResultTextView = findViewById(R.id.user_annonce_no_result)
        loader = findViewById(R.id.user_annonce_progress_bar)
        errorTextView = findViewById(R.id.user_annonce_error)
    }

    private fun setRecyclerView(annonces: MutableList<AnnonceModel>) {
        userAnnonceAdapter = AnnonceUserListAdapter(annonces, this)
        annonceRecyclerView.layoutManager = GridLayoutManager(this, 1)
        annonceRecyclerView.adapter = userAnnonceAdapter
    }

    private fun observeRecyclerView() {
        Annonce.getUserViewModel().annonceList.observe(this) { annonce ->
            setRecyclerView(annonce)
        }
    }

    override fun textChange(newText: String) {
        val listFilter = searchInAnnonce(Annonce.getUserViewModel().annonceList.value, newText)
        userAnnonceAdapter.annonces = listFilter
        noResultTextView.visibility = if (listFilter.isEmpty()) TextView.VISIBLE else TextView.GONE
        userAnnonceAdapter.notifyDataSetChanged()
    }

    private fun setUpBottomNavigationView() {
        val bottomNavView: BottomNavigationView = findViewById(R.id.nav_view)
        NavigationUtilUser.setupBottomNavView(bottomNavView, this, R.id.ic_annonce)
    }

    override fun viewDetailAnnonceUser(annonce: AnnonceModel, position: Int) {
        Intent(this, AnnonceUserDetailActivity::class.java).also {
            it.putExtra(ANNONCE_MODEL_EXTRA, annonce)
            it.putExtra(POSITION, position)
            startActivity(it)
        }
    }
}
