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
import com.esgi.promocare_android.network.Credential

class MainActivity : AppCompatActivity() {

    private lateinit var usernameText: EditText
    private lateinit var passwordText: EditText
    private lateinit var errorText: TextView
    private lateinit var goToInscription: TextView
    private lateinit var goToCompany: TextView
    private lateinit var sendConnexion : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    private fun setUpView(){
        usernameText = findViewById(R.id.connection_user_username)
        passwordText = findViewById(R.id.connection_user_password)
        errorText = findViewById(R.id.connection_user_error)
        sendConnexion = findViewById(R.id.connection_user_button)
        goToInscription = findViewById(R.id.connection_user_register_user)
        goToCompany = findViewById(R.id.connection_user_go_to_company_connection)
    }

    private fun setButtons(){
        sendConnection()
        goToInscription()
        goToCompany()
    }

    private fun sendConnection(){
        sendConnexion.setOnClickListener {
            if(usernameText.text.isEmpty() || passwordText.text.isEmpty()){
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

    private fun goToInscription(){
        goToInscription.setOnClickListener {
            //startActivity(Intent(this, InscriptionActivity::class.java))
        }
    }

    private fun goToCompany(){
        goToCompany.setOnClickListener {
            startActivity(Intent(this, CompanyConnexionActivity::class.java))
        }
    }

}