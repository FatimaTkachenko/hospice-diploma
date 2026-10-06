package ru.edu.qamid.tests

import io.qameta.allure.kotlin.Description
import io.qameta.allure.kotlin.Epic
import io.qameta.allure.kotlin.Feature
import io.qameta.allure.kotlin.Owner
import io.qameta.allure.kotlin.Severity
import io.qameta.allure.kotlin.SeverityLevel
import io.qameta.allure.kotlin.Story
import org.junit.Test
import ru.edu.qamid.base.BaseTest
import ru.edu.qamid.pages.LoginPage
import ru.edu.qamid.pages.MainPage


@Epic("Мобильный хоспис")
@Feature("Авторизация")
@Story("Успешный вход")
@Owner("Fatima Tkachenko")
class AuthTest : BaseTest() {

    private val loginPage = LoginPage()
    private val mainPage = MainPage()

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Проверка успешной авторизации в приложении с валидными учётными данными")
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