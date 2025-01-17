package com.esgi.promocare_android.views.offer.user

import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esgi.promocare_android.R
import com.esgi.promocare_android.data.Offer
import com.esgi.promocare_android.models.offer.GetOfferUser
import com.esgi.promocare_android.network.Credential
import com.esgi.promocare_android.views.NavigationUtilUser
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserOfferActivity:AppCompatActivity(), DetailOfferUserClickHandler{
    companion object{
        const val OFFER = "OFFER"
        const val ANNONCE = "ANNONCE"
        const val COMPANY = "COMPANY"
    }

    private lateinit var offerRecyclerView : RecyclerView
    private lateinit var offerAdapter: OfferListAdapterUser

    private lateinit var goToPending : TextView
    private lateinit var goToAccepted : TextView
    private lateinit var goToRefused : TextView

    private lateinit var noResult : TextView
    private lateinit var error: TextView
    private lateinit var loading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_offer)

        setUpView()
        observeRecyclerView()
        Offer.getOfferUserViewModel().getOfferUserPending(Credential.token,loading,error,noResult)
        handleGoToPending()
        handleGoToAccepted()
        handleGoToRefused()
        goToPending.setTextColor(ContextCompat.getColor(this, R.color.white))
        goToPending.setBackgroundColor(ContextCompat.getColor(this, R.color.black))


        val bottomNavView: BottomNavigationView = findViewById(R.id.nav_view)
        NavigationUtilUser.setupBottomNavView(bottomNavView, this, R.id.ic_offer)
    }

    private fun setUpView(){
        offerRecyclerView = findViewById(R.id.recycler_view_offer_company)
        goToPending = findViewById(R.id.go_to_offer_company_pending)
        goToAccepted = findViewById(R.id.go_to_offer_company_accepted)
        goToRefused = findViewById(R.id.go_to_offer_company_refused)

        noResult = findViewById(R.id.no_offer_company)
        error = findViewById(R.id.offer_error_company)
        loading = findViewById(R.id.progress_bar_offer_company)
    }


    private fun setRecyclerView(offers : MutableList<GetOfferUser>){
        offerAdapter = OfferListAdapterUser(offers,this)

        offerRecyclerView.layoutManager = GridLayoutManager(this, 1)

        offerRecyclerView.adapter = offerAdapter
    }

    private fun observeRecyclerView() {
        Offer.getOfferUserViewModel().offerList.observe(this){ offers ->
            this.setRecyclerView(offers)
        }
    }

    private fun handleGoToPending(){
        goToPending.setOnClickListener {
            Offer.getOfferUserViewModel().getOfferUserPending(Credential.token,loading,error,noResult)
            handleBackgroundColor(goToPending,goToAccepted,goToRefused)
        }
    }

    private fun handleGoToAccepted(){
        goToAccepted.setOnClickListener {
            Offer.getOfferUserViewModel().getOfferUserAccepted(Credential.token,loading,error,noResult)
            handleBackgroundColor(goToAccepted,goToPending,goToRefused)
        }
    }

    private fun handleGoToRefused(){
        goToRefused.setOnClickListener {
            Offer.getOfferUserViewModel().getOfferUserRefused(Credential.token,loading,error,noResult)
            handleBackgroundColor(goToRefused,goToPending,goToAccepted)
        }
    }


    private fun handleBackgroundColor(selectedTextView: TextView, otherTextView1: TextView, otherTextView2: TextView){
        selectedTextView.setTextColor(ContextCompat.getColor(this, R.color.white))
        selectedTextView.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
        otherTextView1.setTextColor(ContextCompat.getColor(this, R.color.black))
        otherTextView1.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        otherTextView2.setTextColor(ContextCompat.getColor(this, R.color.black))
        otherTextView2.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
    }

    override fun viewDetailOfferUser(offer: GetOfferUser) {
        Intent(this, OfferUserDetailActivity::class.java).also {
            it.putExtra(ANNONCE, offer.annonce)
            it.putExtra(OFFER, offer.offer)
            it.putExtra(COMPANY, offer.company)
            startActivity(it)
        }
    }
}