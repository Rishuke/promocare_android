package com.esgi.promocare_android.views.company_annonce

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.esgi.promocare_android.R
import com.esgi.promocare_android.data.Annonce
import com.esgi.promocare_android.models.annonce.CreateAnnonceDto
import com.esgi.promocare_android.network.Credential
import com.esgi.promocare_android.viewmodel.annonce.AnnonceCompanyViewModel

class UpdateAnnonceActivity : AppCompatActivity() {

    private lateinit var viewModel: AnnonceCompanyViewModel
    private lateinit var annonceId: String

    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var priceEditText: EditText
    private lateinit var typeSpinner: Spinner
    private lateinit var locationEditText: EditText
    private lateinit var promoEditText: EditText
    private lateinit var updateButton: Button
    private lateinit var loader: ProgressBar
    private lateinit var errorText: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_annonce)

        viewModel = Annonce.getViewModel()

        annonceId = intent.getStringExtra("annonceId") ?: ""

        titleEditText = findViewById(R.id.update_annonce_title)
        descriptionEditText = findViewById(R.id.update_annonce_description)
        priceEditText = findViewById(R.id.update_annonce_price)
        typeSpinner = findViewById(R.id.update_annonce_type)
        locationEditText = findViewById(R.id.update_annonce_location)
        promoEditText = findViewById(R.id.update_annonce_promo)
        updateButton = findViewById(R.id.update_annonce_button)
        loader = findViewById(R.id.update_annonce_loader)
        errorText = findViewById(R.id.update_annonce_error)

        // Récupérer les données passées via l'intent
        val title = intent.getStringExtra("title") ?: ""
        val description = intent.getStringExtra("description") ?: ""
        val price = intent.getFloatExtra("price", 0.0f)
        val type = intent.getStringExtra("type") ?: ""
        val location = intent.getStringExtra("location") ?: ""
        val promo = intent.getIntExtra("promo", 0)

        // Populer les champs avec les données de l'annonce
        titleEditText.setText(title)
        descriptionEditText.setText(description)
        priceEditText.setText(price.toString())
        locationEditText.setText(location)
        promoEditText.setText(promo.toString())

        // Créer une liste d'éléments pour le Spinner
        val items = listOf("Menage à domicile", "Cuisine à domicile", "Conducteur personnel", "Soin personnalisé")

        // Créer un ArrayAdapter en utilisant un layout par défaut du Spinner et la liste d'éléments
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)

        // Spécifier le layout à utiliser quand la liste des choix apparaît
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Appliquer l'adaptateur au Spinner
        typeSpinner.adapter = adapter

        // Sélectionner l'élément du Spinner correspondant au type de l'annonce
        val typePosition = items.indexOf(type)
        if (typePosition >= 0) {
            typeSpinner.setSelection(typePosition)
        }

        updateButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val priceText = priceEditText.text.toString()
            val location = locationEditText.text.toString()
            val promoText = promoEditText.text.toString()
            val type = typeSpinner.selectedItem.toString()

            // Validation des champs
            if (title.isBlank()) {
                showError("Le titre ne peut pas être vide")
                return@setOnClickListener
            }
            if (description.isBlank()) {
                showError("La description ne peut pas être vide")
                return@setOnClickListener
            }
            val price = priceText.toFloatOrNull()
            if (price == null || price <= 0) {
                showError("Le prix doit être supérieur à 0")
                return@setOnClickListener
            }
            val promo = promoText.toIntOrNull()
            if (promo == null || promo <= 0 || promo > 100) {
                showError("La promotion doit être comprise entre 0 et 100")
                return@setOnClickListener
            }

            val createAnnonceDto = CreateAnnonceDto(
                title = title,
                description = description,
                price = price,
                type = type,
                location = location,
                promo = promo
            )
            viewModel.updateAnnonceCompany("Bearer " + Credential.token, annonceId, createAnnonceDto, loader, errorText) {
                finish()
            }
        }
    }



    private fun showError(message: String) {
        errorText.text = message
        errorText.visibility = View.VISIBLE
    }
}
