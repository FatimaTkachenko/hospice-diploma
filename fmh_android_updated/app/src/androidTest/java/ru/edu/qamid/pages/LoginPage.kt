package ru.edu.qamid.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import ru.edu.qamid.R

/**
 * Page Object экрана авторизации.
 * Содержит методы для взаимодействия с полями и кнопкой входа.
 */
class LoginPage {

    fun enterLogin(login: String): LoginPage {
        onView(withId(R.id.login_edit_text))
            .perform(replaceText(login), closeSoftKeyboard())
        return this
    }

    fun enterPassword(password: String): LoginPage {
        onView(withId(R.id.password_edit_text))
            .perform(replaceText(password), closeSoftKeyboard())
        return this
    }

    fun clickSignIn(): LoginPage {
        onView(withId(R.id.enter_button)).perform(click())
        return this
    }

    fun login(login: String, password: String): LoginPage {
        enterLogin(login)
        enterPassword(password)
        clickSignIn()
        return this
    }

    fun checkLoginFieldIsDisplayed(): LoginPage {
        onView(withId(R.id.login_edit_text)).check(matches(isDisplayed()))
        return this
    }

    fun checkPasswordFieldIsDisplayed(): LoginPage {
        onView(withId(R.id.password_edit_text)).check(matches(isDisplayed()))
        return this
    }

    fun checkSignInButtonIsDisplayed(): LoginPage {
        onView(withId(R.id.enter_button)).check(matches(isDisplayed()))
        return this
    }
} 
