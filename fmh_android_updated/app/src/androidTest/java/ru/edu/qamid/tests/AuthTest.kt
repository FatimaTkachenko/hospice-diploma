package ru.edu.qamid.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import ru.edu.qamid.base.BaseTest
import ru.edu.qamid.pages.LoginPage

@RunWith(AndroidJUnit4::class)
class AuthTest : BaseTest() {

    private val loginPage = LoginPage()

    @Test
    fun successfulLogin() {
        waitForLoginScreen()
        loginPage
            .checkLoginFieldIsDisplayed()
            .checkPasswordFieldIsDisplayed()
            .checkSignInButtonIsDisplayed()
            .login("login2", "password2")
    }
}