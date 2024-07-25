package com.esgi.promocare_android.screen

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.esgi.promocare_android.views.MainActivity
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.esgi.promocare_android.R
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import tools.fastlane.screengrab.Screengrab
import tools.fastlane.screengrab.locale.LocaleTestRule


@RunWith(AndroidJUnit4::class)
class ScreenGrabTest {
    @Rule
    @JvmField
    val localeTestRule = LocaleTestRule()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.esgi.promocare_android", appContext.packageName)
    }

    @Test
    fun test_todos_list_is_displayed() {
        ActivityScenario.launch(MainActivity::class.java)
        Thread.sleep(1000)
        onView(withId(R.id.connection_user_password)).check(matches(isDisplayed()))
        Screengrab.screenshot("screenshot_main_activity_todo")
        Thread.sleep(1000)
    }
}