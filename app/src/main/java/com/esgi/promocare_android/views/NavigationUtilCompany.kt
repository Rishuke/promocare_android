package com.esgi.promocare_android.views

import android.content.Context
import android.content.Intent
import com.esgi.promocare_android.R
import com.esgi.promocare_android.views.conversations.company.LatestConvCompanyActivity
import com.esgi.promocare_android.views.company_annonce.CompanyAnnonceActivity
import com.esgi.promocare_android.views.offer.company.CompanyOfferActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

object NavigationUtilCompany {
    fun setupBottomNavView(
        bottomNavView: BottomNavigationView,
        context: Context,
        currentActivityId: Int
    ) {
        bottomNavView.selectedItemId = currentActivityId
        bottomNavView.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
            val itemId = item.itemId
            when (itemId) {
                R.id.ic_conversation -> {
                    if (currentActivityId != R.id.ic_conversation) {
                        context.startActivity(Intent(context, LatestConvCompanyActivity::class.java))
                    }
                }

                R.id.ic_annonce -> {
                    if (currentActivityId != R.id.ic_annonce) {
                        context.startActivity(Intent(context, CompanyAnnonceActivity::class.java))
                    }
                }

                R.id.ic_offer -> {
                    if (currentActivityId != R.id.ic_offer) {
                        context.startActivity(Intent(context, CompanyOfferActivity::class.java))
                    }
                }

                R.id.ic_logout -> {
                    val intent = Intent(context, LogoutActivity::class.java)
                    intent.putExtra("IS_COMPANY", true) // Company
                    context.startActivity(intent)
                }


            }
            true
        })
    }
}
