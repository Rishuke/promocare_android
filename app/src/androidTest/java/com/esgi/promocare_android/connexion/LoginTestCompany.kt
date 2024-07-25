package com.esgi.promocare_android.connexion

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.esgi.promocare_android.R
import com.esgi.promocare_android.network.Credential
import com.esgi.promocare_android.views.CompanyConnexionActivity
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class LoginTestCompany {
    private var scenario: ActivityScenario<CompanyConnexionActivity>? = null
    @Before
    fun setUp(){
        this.scenario = ActivityScenario.launch(CompanyConnexionActivity::class.java)
    }

    @Test
    fun isErrorDisplayedCompanySideUsername() {
        Thread.sleep(1000)
        onView(withId(R.id.connection_company_username))
            .perform(ViewActions.typeText("New Username"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.connection_company_button)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.connection_company_error)).check(matches(isDisplayed()))
    }

    @Test
    fun isErrorDisplayedCompanySidePassword() {
        Thread.sleep(1000)
        onView(withId(R.id.connection_company_password))
            .perform(ViewActions.typeText("my password"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.connection_company_button)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.connection_company_error)).check(matches(isDisplayed()))
    }

    @Test
    fun isOkConnexion(){
        Thread.sleep(1000)
        onView(withId(R.id.connection_company_button)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.company_annonce_search_bar)).check(matches(isDisplayed()))
        assertNotEquals(Credential.token, "Bearer ")
    }

    @Test
    fun goToUser(){
        Thread.sleep(1000)
        onView(withId(R.id.connection_company_go_to_user_connection)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.connection_user_go_to_company_connection)).check(matches(isDisplayed()))
    }
}