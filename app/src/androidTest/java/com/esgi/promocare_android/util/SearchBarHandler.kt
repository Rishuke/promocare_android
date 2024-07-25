package com.esgi.promocare_android.util

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher

fun setSearchViewQuery(query: String, submit: Boolean): ViewAction {
    return ViewActions.actionWithAssertions(object : ViewAction {
        override fun getConstraints(): Matcher<View>? {
            return ViewMatchers.isAssignableFrom(SearchView::class.java)
        }

        override fun getDescription(): String {
            return "Change view query"
        }

        override fun perform(uiController: UiController?, view: View?) {
            val searchView = view as SearchView
            searchView.setQuery(query, submit)
        }
    })
}