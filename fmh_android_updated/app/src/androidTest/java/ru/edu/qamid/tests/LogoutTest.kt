package ru.edu.qamid.tests

import android.util.Log
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import io.qameta.allure.kotlin.Description
import io.qameta.allure.kotlin.Epic
import io.qameta.allure.kotlin.Feature
import io.qameta.allure.kotlin.Owner
import io.qameta.allure.kotlin.Severity
import io.qameta.allure.kotlin.SeverityLevel
import io.qameta.allure.kotlin.Story
import org.junit.Test
import ru.edu.qamid.R
import ru.edu.qamid.base.BaseTest
import ru.edu.qamid.pages.LoginPage
import ru.edu.qamid.pages.MainPage


@Epic("Мобильный хоспис")
@Feature("Авторизация")
@Story("Выход из приложения")
@Owner("Fatima Tkachenko")
class LogoutTest : BaseTest() {

    private val loginPage = LoginPage()
    private val mainPage = MainPage()

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка выхода из приложения через меню профиля: Log out возвращает на экран авторизации")
    fun logoutFromApp() {
        Log.d("LogoutTest", "=== Start ===")

        waitForLoginScreen()
        Log.d("LogoutTest", "Login screen visible")

        loginPage.login("login2", "password2")
        Log.d("LogoutTest", "Login form submitted")

        waitForMainScreen()
        Log.d("LogoutTest", "Main screen visible")

        mainPage.clickAuthorization()
        Log.d("LogoutTest", "Profile icon clicked")

        val logOut = device.wait(Until.findObject(By.text("Log out")), 5_000L)
            ?: throw AssertionError("'Log out' not found")

        device.click(logOut.visibleBounds.centerX(), logOut.visibleBounds.centerY())
        Log.d("LogoutTest", "Clicked Log out")

        waitForLoginScreen()
        Log.d("LogoutTest", "Back to login screen")

        onView(withId(R.id.login_edit_text)).check(matches(isDisplayed()))
        onView(withId(R.id.enter_button)).check(matches(isDisplayed()))
    }
}