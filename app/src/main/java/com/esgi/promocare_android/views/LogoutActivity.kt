package com.esgi.promocare_android.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.esgi.promocare_android.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.bottomnavigation.BottomNavigationView

class LogoutActivity : AppCompatActivity() {

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)

        val logoutConfirmButton: MaterialButton = findViewById(R.id.logout_confirm_button)

        logoutConfirmButton.setOnClickListener {
            handleLogout()
        }

        val bottomNavView: BottomNavigationView = findViewById(R.id.nav_view)
        NavigationUtilUser.setupBottomNavView(bottomNavView, this, R.id.ic_logout)
    }

    private fun handleLogout() {
        val isCompany = intent.getBooleanExtra("IS_COMPANY", false)

        val intent = if (isCompany) {
            Intent(this, CompanyConnexionActivity::class.java)
        } else {
            Intent(this, MainActivity::class.java)
        }
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
