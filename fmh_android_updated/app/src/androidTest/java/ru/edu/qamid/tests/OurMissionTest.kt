package ru.edu.qamid.tests

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import ru.edu.qamid.base.BaseTest
import ru.edu.qamid.pages.LoginPage
import ru.edu.qamid.pages.MainPage
import ru.edu.qamid.pages.OurMissionPage

@RunWith(AndroidJUnit4::class)
class OurMissionTest : BaseTest() {

    private val loginPage = LoginPage()
    private val mainPage = MainPage()
    private val ourMissionPage = OurMissionPage()

    @Test
    fun openOurMissionScreen() {
        Log.d("OurMissionTest", "=== Start ===")

        // 1. Логин
        waitForLoginScreen()
        loginPage.login("login2", "password2")
        Log.d("OurMissionTest", "Login submitted")

        // 2. Ждём главный экран
        waitForMainScreen()
        Log.d("OurMissionTest", "Main screen visible")

        // 3. Переходим в раздел "Our Mission"
        mainPage.clickOurMission()
        Log.d("OurMissionTest", "Our Mission button clicked")

        // 4. Проверяем, что открылся экран цитат
        ourMissionPage
            .checkTitleIsDisplayed()
            .checkQuoteListIsDisplayed()

        Log.d("OurMissionTest", "Our Mission screen verified")
    }
}