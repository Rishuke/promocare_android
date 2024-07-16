package com.esgi.promocare_android.views.company_annonce

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esgi.promocare_android.R
import com.esgi.promocare_android.data.Annonce
import com.esgi.promocare_android.fragment.SearchBarFragment
import com.esgi.promocare_android.fragment.searchBarHandler
import com.esgi.promocare_android.models.annonce.AnnonceModel
import com.esgi.promocare_android.network.Credential
import com.esgi.promocare_android.utils.searchInAnnonce
import com.esgi.promocare_android.views.company_annonce.create_annonce.ChosePrice
import com.esgi.promocare_android.views.company_annonce.create_annonce.ChoseTitle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CompanyAnnonceActivity: AppCompatActivity(), searchBarHandler {

    private lateinit var searchBarFragment: SearchBarFragment
    private lateinit var addAnnonce: FloatingActionButton
    private lateinit var annonceRecyclerView: RecyclerView
    private lateinit var titleAnnonce: TextView
    private lateinit var companyAnnonceAdapter: CompanyAnnonceListAdapter

    //gestion erreur
    private lateinit var errorTextView: TextView
    private lateinit var loader: ProgressBar
    private lateinit var noResultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_annonce)
        setUpView()
        Annonce.getViewModel().getAnnonceCompany(Credential.token, loader, errorTextView)
        observeRecyclerView()
        addAnnonce()
    }

    private fun setUpView(){
        this.searchBarFragment = SearchBarFragment.newInstance()
        this.addAnnonce = findViewById(R.id.company_annonce_add_button)
        this.annonceRecyclerView = findViewById(R.id.company_annonce_recycler_view)
        this.titleAnnonce = findViewById(R.id.company_annonce_title)

        this.noResultTextView = findViewById(R.id.company_annonce_no_result)
        this.loader = findViewById(R.id.company_annonce_progress_bar)
        this.errorTextView = findViewById(R.id.company_annonce_error)
    }

    private fun addAnnonce(){
        this.addAnnonce.setOnClickListener {
            startActivity(Intent(this, ChoseTitle::class.java))
        }
    }

    private fun setRecyclerView(annonces: MutableList<AnnonceModel>) {
        this.companyAnnonceAdapter = CompanyAnnonceListAdapter(annonces)

        this.annonceRecyclerView.layoutManager = GridLayoutManager(this, 1)

        this.annonceRecyclerView.setAdapter(companyAnnonceAdapter)
    }

    private fun observeRecyclerView() {
        Annonce.getViewModel().annonceList.observe(this) { annonce ->
            if(Annonce.getViewModel().annonceList.value?.size == null){
                titleAnnonce.text = buildString {
                    append("Mes annonces : (")
                    append("0)")

                }
            }
            else{
                titleAnnonce.text = buildString {
                    append("Mes annonces : (")
                    append(Annonce.getViewModel().annonceList.value?.size.toString())
                    append(")")
                }
            }
            this.setRecyclerView(annonce)
        }
    }

    override fun textChange(newText: String) {
        val listFilter = searchInAnnonce(Annonce.getViewModel().annonceList.value, newText)
        this.companyAnnonceAdapter.annonces = listFilter
        if(listFilter.isEmpty()){
            noResultTextView.visibility = TextView.VISIBLE
        }
        else{
            noResultTextView.visibility = TextView.GONE
        }
        companyAnnonceAdapter.notifyDataSetChanged()
    }
}