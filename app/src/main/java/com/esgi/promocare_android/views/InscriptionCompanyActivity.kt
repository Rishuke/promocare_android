package com.esgi.promocare_android.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.esgi.promocare_android.R
import com.esgi.promocare_android.data.InscriptionConnexion
import com.esgi.promocare_android.models.inscription.SubscribeCompanyRequest
import com.esgi.promocare_android.views.user_annonce.AnnonceUserActivity

class InscriptionCompanyActivity : AppCompatActivity() {
    private lateinit var companyNameEditText: EditText
    private lateinit var siretNumberEditText: EditText
    private lateinit var locationEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var errorText: TextView
    private lateinit var inscriptionButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscription_company)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.inscription_company_name)
        ) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setUpView()
        setButtons()
    }

    private fun setUpView() {
        companyNameEditText = findViewById(R.id.inscription_company_name)
        siretNumberEditText = findViewById(R.id.inscription_siret_number)
        locationEditText = findViewById(R.id.inscription_location)
        emailEditText = findViewById(R.id.inscription_company_email)
        passwordEditText = findViewById(R.id.inscription_company_password)
        confirmPasswordEditText = findViewById(R.id.inscription_company_confirm_password)
        errorText = findViewById(R.id.inscription_company_error)
        inscriptionButton = findViewById(R.id.inscription_company_button)
    }

    private fun setButtons() {
        inscriptionButton.setOnClickListener { registerClickListener() }
    }

    private fun registerClickListener() {
        val companyName = companyNameEditText.text.toString()
        val siretNumber = siretNumberEditText.text.toString()
        val location = locationEditText.text.toString()
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        val confirmPassword = confirmPasswordEditText.text.toString()

        if (companyName.isEmpty() || siretNumber.isEmpty() || location.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            errorText.setText(R.string.error_empty_fields)
            errorText.visibility = TextView.VISIBLE
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorText.setText(R.string.error_invalid_email)
            errorText.visibility = TextView.VISIBLE
        } else if (siretNumber.length != 14) {
            errorText.setText(R.string.error_invalid_siret_number)
            errorText.visibility = TextView.VISIBLE
        } else if (password != confirmPassword) {
            errorText.setText(R.string.error_password_mismatch)
            errorText.visibility = TextView.VISIBLE
        } else {
            errorText.visibility = TextView.INVISIBLE
            val subscribeCompanyRequest = SubscribeCompanyRequest(email, password, companyName, siretNumber, location)
            // Log the request body
            Log.d("InscriptionCompanyActivi ty", "Request Body: $subscribeCompanyRequest")
            sendInscription(subscribeCompanyRequest)
        }
    }

    private fun sendInscription(subscribeCompanyRequest: SubscribeCompanyRequest) {
        InscriptionConnexion.getCompanyRegisterViewModel().sendRegisterCompanyRequest(subscribeCompanyRequest, errorText, this)
    }

    private fun goToHome() {
        startActivity(Intent(this, AnnonceUserActivity::class.java))
        finish()
    }
}
