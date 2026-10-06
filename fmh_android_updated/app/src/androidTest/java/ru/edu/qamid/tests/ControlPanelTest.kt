package ru.edu.qamid.tests

import android.util.Log
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
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
import org.junit.Assert.assertTrue
import org.junit.Test
import ru.edu.qamid.R
import ru.edu.qamid.base.BaseTest
import ru.edu.qamid.pages.ControlPanelPage
import ru.edu.qamid.pages.LoginPage
import ru.edu.qamid.pages.MainPage


@Epic("Мобильный хоспис")
@Feature("Работа с новостями")
@Owner("Fatima Tkachenko")
class ControlPanelTest : BaseTest() {

    private val loginPage = LoginPage()
    private val mainPage = MainPage()
    private val controlPanelPage = ControlPanelPage()

    private fun openControlPanel() {
        waitForLoginScreen()
        loginPage.login("login2", "password2")
        waitForMainScreen()
        mainPage.clickAllNews()
        onView(withId(R.id.news_edit_button)).perform(click())
        val cpElement = device.wait(Until.findObject(By.text("Control panel")), 5_000L)
        assertTrue("Control panel did not open", cpElement != null)
    }

    @Test
    @Story("Открытие Control panel")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка открытия панели управления новостями и отображения списка")
    fun openControlPanelAndCheckList() {
        Log.d("ControlPanelTest", "=== Start: openControlPanelAndCheckList ===")

        openControlPanel()

        controlPanelPage
            .checkControlPanelIsDisplayed()
            .checkNewsListIsDisplayed()

        Log.d("ControlPanelTest", "Control panel verified")
    }

    /**
     * BUG-001: некорректное отображение Creation date (год 58714 вместо 2026).
     */
    @Test
    @Story("Отображение даты создания новости")
    @Severity(SeverityLevel.CRITICAL)
    @Description("BUG-001: Проверка, что Creation date отображается корректно (год 2026, а не 58714)")
    fun creationDateShouldNotContainInvalidYear() {
        Log.d("ControlPanelTest", "=== Start: creationDateShouldNotContainInvalidYear ===")

        openControlPanel()

        val creationDateElement = device.wait(
            Until.findObject(By.res("ru.edu.qamid", "news_item_create_date_text_view")),
            5_000L
        )
        assertTrue("Creation date field not found", creationDateElement != null)

        val value = creationDateElement!!.text
        Log.d("ControlPanelTest", "Creation date value = $value")

        val hasInvalidYear = value.contains("587")
        assertTrue(
            "BUG-001: Creation date shows invalid year in '$value'. Expected year 2026.",
            !hasInvalidYear
        )
    }
}