package com.esgi.promocare_android.views

import android.content.Intent
import android.os.Bundle
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

class CompanyConnexionActivity: AppCompatActivity() {

    private lateinit var usernameText: EditText
    private lateinit var passwordText: EditText
    private lateinit var errorText: TextView
    private lateinit var goToInscription: TextView
    private lateinit var goToUser: TextView
    private lateinit var sendConnexion : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.company_connexion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.connection_company_username)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpView()
        setButtons()
    }

    private fun setUpView(){
        usernameText = findViewById(R.id.connection_company_username)
        passwordText = findViewById(R.id.connection_company_password)
        errorText = findViewById(R.id.connection_company_error)
        sendConnexion = findViewById(R.id.connection_company_button)
        goToInscription = findViewById(R.id.connection_company_register_company)
        goToUser = findViewById(R.id.connection_company_go_to_user_connection)
    }


    private fun setButtons(){
        sendConnection()
        goToInscription()
        goToUser()
    }

    private fun sendConnection(){
        sendConnexion.setOnClickListener {
            if(usernameText.text.isEmpty() || passwordText.text.isEmpty()){
                errorText.setText(R.string.user_connection_empty_error)
                errorText.visibility = TextView.VISIBLE
                return@setOnClickListener
            }
            InscriptionConnexion.getLoginViewModel().sendCompanyLoginRequest(
                LoginRequest(usernameText.text.toString(), passwordText.text.toString()),
                errorText,
                this
            )
        }
    }

    private fun goToInscription(){
        goToInscription.setOnClickListener {
            val intent = Intent(this, InscriptionCompanyActivity::class.java)
            startActivity(intent)
        }
    }

    private fun goToUser(){
        goToUser.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}