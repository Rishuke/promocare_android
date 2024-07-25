package com.esgi.promocare_android.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.esgi.promocare_android.R
import com.esgi.promocare_android.data.InscriptionConnexion
import com.esgi.promocare_android.models.login.LoginRequest
import com.google.firebase.crashlytics.FirebaseCrashlytics

class MainActivity : AppCompatActivity() {

    private lateinit var usernameText: EditText
    private lateinit var passwordText: EditText
    private lateinit var errorText: TextView
    private lateinit var goToInscription: TextView
    private lateinit var goToCompany: TextView
    private lateinit var sendConnexion: Button
    private lateinit var forceCrashButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseCrashlytics.getInstance().log("Main activity started")
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.connection_user_username)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpView()
        setButtons()
    }

    private fun setUpView() {
        usernameText = findViewById(R.id.connection_user_username)
        passwordText = findViewById(R.id.connection_user_password)
        errorText = findViewById(R.id.connection_user_error)
        sendConnexion = findViewById(R.id.connection_user_button)
        goToInscription = findViewById(R.id.connection_user_register_user)
        goToCompany = findViewById(R.id.connection_user_go_to_company_connection)
        forceCrashButton = findViewById(R.id.connection_user_button)
    }

    private fun setButtons() {
        sendConnection()
        goToInscription()
        goToCompany()
        forceCrashButton.setOnClickListener {
            forceCrash()
        }
    }

    private fun sendConnection() {
        sendConnexion.setOnClickListener {
            if (usernameText.text.isEmpty() || passwordText.text.isEmpty()) {
                val crashlytics = FirebaseCrashlytics.getInstance()
                crashlytics.setCustomKey("login_error", "username_or_password_empty")
                errorText.setText(R.string.user_connection_empty_error)
                errorText.visibility = TextView.VISIBLE
                return@setOnClickListener
            }
            InscriptionConnexion.getLoginViewModel().sendLoginRequest(
                LoginRequest(usernameText.text.toString(), passwordText.text.toString()),
                errorText,
                this
            )
        }
    }

    private fun goToInscription() {
        goToInscription.setOnClickListener {
            startActivity(Intent(this, InscriptionActivity::class.java))
        }
    }

    private fun goToCompany() {
        goToCompany.setOnClickListener {
            startActivity(Intent(this, CompanyConnexionActivity::class.java))
        }
    }

    // Simulate a crash for testing Crashlytics
    private fun forceCrash() {
        val crashlytics = FirebaseCrashlytics.getInstance()
        crashlytics.log("Crash button clicked")
        crashlytics.setCustomKey("user_id", usernameText.text.toString())
        crashlytics.setCustomKey("crash_type", "manual_force_crash")
        throw RuntimeException("Test Crash") // Force a crash
    }
}
