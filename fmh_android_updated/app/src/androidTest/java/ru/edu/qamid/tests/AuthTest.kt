package ru.edu.qamid.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import ru.edu.qamid.base.BaseTest
import ru.edu.qamid.pages.LoginPage
import ru.edu.qamid.pages.MainPage

@RunWith(AndroidJUnit4::class)
class AuthTest : BaseTest() {

    private val loginPage = LoginPage()
    private val mainPage = MainPage()

    @Test
    fun successfulLogin() {
        waitForLoginScreen()
        loginPage
            .checkLoginFieldIsDisplayed()
            .checkPasswordFieldIsDisplayed()
            .checkSignInButtonIsDisplayed()
            .login("login2", "password2")

        waitForMainScreen()
        mainPage
            .checkMainScreenIsDisplayed()
            .checkAllNewsButtonIsDisplayed()
    }
}