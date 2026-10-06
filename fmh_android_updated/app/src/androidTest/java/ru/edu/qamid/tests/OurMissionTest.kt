package ru.edu.qamid.tests

import android.util.Log
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
import ru.edu.qamid.pages.OurMissionPage


@Epic("Мобильный хоспис")
@Feature("Страницы приложения")
@Story("Раздел Our Mission (цитаты)")
@Owner("Fatima Tkachenko")
class OurMissionTest : BaseTest() {

    private val loginPage = LoginPage()
    private val mainPage = MainPage()
    private val ourMissionPage = OurMissionPage()

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка перехода в раздел Our Mission и отображения списка цитат")
    fun openOurMissionScreen() {
        Log.d("OurMissionTest", "=== Start ===")

        waitForLoginScreen()
        loginPage.login("login2", "password2")
        Log.d("OurMissionTest", "Login submitted")

        waitForMainScreen()
        Log.d("OurMissionTest", "Main screen visible")

        mainPage.clickOurMission()
        Log.d("OurMissionTest", "Our Mission button clicked")

        ourMissionPage
            .checkTitleIsDisplayed()
            .checkQuoteListIsDisplayed()

        Log.d("OurMissionTest", "Our Mission screen verified")
    }
}