package ru.edu.qamid.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import io.qameta.allure.kotlin.Allure
import ru.edu.qamid.R

/**
 * Page Object экрана авторизации.
 */
class LoginPage {

    fun enterLogin(login: String): LoginPage {
        Allure.step("Ввести логин: $login") {
            onView(withId(R.id.login_edit_text))
                .perform(replaceText(login), closeSoftKeyboard())
        }
        return this
    }

    fun enterPassword(password: String): LoginPage {
        Allure.step("Ввести пароль") {
            onView(withId(R.id.password_edit_text))
                .perform(replaceText(password), closeSoftKeyboard())
        }
        return this
    }

    fun clickSignIn(): LoginPage {
        Allure.step("Нажать кнопку SIGN IN") {
            onView(withId(R.id.enter_button)).perform(click())
        }
        return this
    }

    fun login(login: String, password: String): LoginPage {
        Allure.step("Авторизоваться с логином $login") {
            enterLogin(login)
            enterPassword(password)
            clickSignIn()
        }
        return this
    }

    fun checkLoginFieldIsDisplayed(): LoginPage {
        Allure.step("Проверить, что поле логина отображается") {
            onView(withId(R.id.login_edit_text)).check(matches(isDisplayed()))
        }
        return this
    }

    fun checkPasswordFieldIsDisplayed(): LoginPage {
        Allure.step("Проверить, что поле пароля отображается") {
            onView(withId(R.id.password_edit_text)).check(matches(isDisplayed()))
        }
        return this
    }

    fun checkSignInButtonIsDisplayed(): LoginPage {
        Allure.step("Проверить, что кнопка SIGN IN отображается") {
            onView(withId(R.id.enter_button)).check(matches(isDisplayed()))
        }
        return this
    }
}