package com.esgi.promocare_android.views.company_annonce

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.esgi.promocare_android.R
import com.esgi.promocare_android.fragment.SearchBarFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CompanyAnnonceActivity: AppCompatActivity(){

    private lateinit var searchBarFragment: SearchBarFragment
    private lateinit var addAnnonce: FloatingActionButton
    private lateinit var annonceList: RecyclerView
    private lateinit var titleAnnonce: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_annonce)
        setUpView()
    }

    private fun setUpView(){
        this.searchBarFragment = SearchBarFragment.newInstance()
        this.addAnnonce = findViewById(R.id.company_annonce_add_button)
        this.annonceList = findViewById(R.id.company_annonce_recycler_view)
        this.titleAnnonce = findViewById(R.id.company_annonce_title)
    }
}