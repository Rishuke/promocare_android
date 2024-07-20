package com.esgi.promocare_android.views

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.esgi.promocare_android.R

class InscriptionActivity : AppCompatActivity() {
    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var errorText: TextView
    private lateinit var inscriptionButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscription)
        setUpView()
        setButtons()
    }

    private fun setUpView(){
        firstNameEditText = findViewById(R.id.inscription_user_first_name)
        lastNameEditText = findViewById(R.id.inscription_user_last_name)
        emailEditText = findViewById(R.id.inscription_user_email)
        passwordEditText = findViewById(R.id.inscription_user_password)
        confirmPasswordEditText = findViewById(R.id.inscription_user_confirm_password)
        errorText = findViewById(R.id.inscription_user_error)
        inscriptionButton = findViewById(R.id.inscription_user_button)
    }

    private fun setButtons(){
        val registerClickListener = {
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                errorText.text = getString(R.string.error_empty_fields)
                errorText.visibility = TextView.VISIBLE
            } else if (password != confirmPassword) {
                errorText.text = getString(R.string.error_password_mismatch)
                errorText.visibility = TextView.VISIBLE
            } else {
                errorText.visibility = TextView.INVISIBLE
                // Logique d'inscription, par exemple, envoyer les données au serveur
            }
        }

        inscriptionButton.setOnClickListener { registerClickListener() }
    }
}
