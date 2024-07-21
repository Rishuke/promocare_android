package com.esgi.promocare_android.views.company_annonce
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
            val createAnnonceDto = CreateAnnonceDto(
                title = titleEditText.text.toString(),
                description = descriptionEditText.text.toString(),
                price = priceEditText.text.toString().toFloatOrNull() ?: 0.0f,
                type = typeSpinner.selectedItem.toString(),
                location = locationEditText.text.toString(),
                promo = promoEditText.text.toString().toIntOrNull() ?: 0
            )
            viewModel.updateAnnonceCompany("Bearer " + Credential.token, annonceId, createAnnonceDto, loader, errorText) {
                finish()
            }
        }
    }
}
