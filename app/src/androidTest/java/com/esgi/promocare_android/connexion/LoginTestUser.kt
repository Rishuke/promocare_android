package com.esgi.promocare_android.connexion

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.esgi.promocare_android.R
import com.esgi.promocare_android.network.Credential
import com.esgi.promocare_android.views.MainActivity
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class LoginTestUser {
    private var scenario: ActivityScenario<MainActivity>? = null
    @Before
    fun setUp(){
        this.scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun isErrorDisplayedUserSideUsername() {
        Thread.sleep(1000)
        onView(withId(R.id.connection_user_username))
            .perform(ViewActions.typeText("New Username"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.connection_user_button)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.connection_user_error)).check(matches(isDisplayed()))
    }

    @Test
    fun isErrorDisplayedUserSidePassword() {
        Thread.sleep(1000)
        onView(withId(R.id.connection_user_password))
            .perform(ViewActions.typeText("my password"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.connection_user_button)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.connection_user_error)).check(matches(isDisplayed()))
    }

    @Test
    fun isOkConnexion(){
        Thread.sleep(1000)
        onView(withId(R.id.connection_user_button)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.user_annonce_search_bar)).check(matches(isDisplayed()))
        assertNotEquals(Credential.token, "Bearer ")
    }

    @Test
    fun goToCompany(){
        Thread.sleep(1000)
        onView(withId(R.id.connection_user_go_to_company_connection)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.connection_company_username)).check(matches(isDisplayed()))
    }
}