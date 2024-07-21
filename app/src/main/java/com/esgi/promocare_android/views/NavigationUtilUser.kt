package com.esgi.promocare_android.views

import android.content.Context
import android.content.Intent
import com.esgi.promocare_android.R
import com.esgi.promocare_android.views.conversations.user.LatestConvUserAcitivity
import com.esgi.promocare_android.views.user_annonce.AnnonceUserActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

object NavigationUtilUser {
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
                        context.startActivity(Intent(context, LatestConvUserAcitivity::class.java))
                    }
                }

                R.id.ic_annonce -> {
                    if (currentActivityId != R.id.ic_annonce) {
                        context.startActivity(Intent(context, AnnonceUserActivity::class.java))
                    }
                }

                R.id.ic_offer -> {
                    if (currentActivityId != R.id.ic_offer) {
                        // Add your activity for "Offer" here
                    }
                }
            }
            true
        })
    }
}
