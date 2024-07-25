/**package com.esgi.promocare_android.annonce

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.esgi.promocare_android.R
import com.esgi.promocare_android.models.annonce.AnnonceDto
import com.esgi.promocare_android.models.annonce.ReturnAnnonceDto
import com.esgi.promocare_android.util.gson
import com.esgi.promocare_android.util.mockWebServerVal
import com.esgi.promocare_android.util.setSearchViewQuery
import com.esgi.promocare_android.views.user_annonce.AnnonceUserActivity
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Test

class AnnonceTestUserSide {

    private var scenario: ActivityScenario<AnnonceUserActivity>? = null

    @Before
    fun setUp(){
        mockWebServerVal.start()
        val responseAnnonce = listOf(
            AnnonceDto(
                uuid = "123e4567-e89b-12d3-a456-426614174000",
                companyId = "company1",
                location = "Location A",
                price = 99.99f,
                promo = 10,
                status = "Active",
                title = "Annonce Title 1",
                description = "Description for annonce 1",
                type = "Type A",
                viewTime = 120,
                updatedAt = "2024-07-24T10:00:00Z",
                createdAt = "2024-07-01T09:00:00Z"
            ),
            AnnonceDto(
                uuid = "123e4567-e89b-12d3-a456-426614174001",
                companyId = "company2",
                location = "Location B",
                price = 199.99f,
                promo = 20,
                status = "Active",
                title = "Annonce Title 2",
                description = "Description for annonce 2",
                type = "Type B",
                viewTime = 240,
                updatedAt = "2024-07-24T10:00:00Z",
                createdAt = "2024-07-01T09:00:00Z"
            )
        )

        val responseBody = ReturnAnnonceDto(message = "Success", item = responseAnnonce)
        val responseBodyJson = gson.toJson(responseBody)
        mockWebServerVal.enqueue(MockResponse()
            .setBody(responseBodyJson)
            .setResponseCode(200)
        )
        this.scenario = ActivityScenario.launch(AnnonceUserActivity::class.java)
    }

    @Test
    fun noResultInAnnonce(){
        onView(withId(R.id.user_annonce_search_bar)).perform(setSearchViewQuery("zzzzzzzzzzz", true))
        Thread.sleep(1000)
        onView(withId(R.id.user_annonce_no_result)).check(matches(isDisplayed()))
    }
}**/