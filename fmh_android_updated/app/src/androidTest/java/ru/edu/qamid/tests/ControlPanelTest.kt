package ru.edu.qamid.tests

import android.util.Log
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import ru.edu.qamid.R
import ru.edu.qamid.base.BaseTest
import ru.edu.qamid.pages.ControlPanelPage
import ru.edu.qamid.pages.LoginPage
import ru.edu.qamid.pages.MainPage

@RunWith(AndroidJUnit4::class)
class ControlPanelTest : BaseTest() {

    private val loginPage = LoginPage()
    private val mainPage = MainPage()
    private val controlPanelPage = ControlPanelPage()

    /**
     * Подготовка: логин + переход в Control panel.
     * Путь: Main → ALL NEWS → ✎ (news_edit_button) → Control panel.
     */
    private fun openControlPanel() {
        waitForLoginScreen()
        loginPage.login("login2", "password2")
        waitForMainScreen()
        mainPage.clickAllNews()

        // После ALL NEWS — нажимаем кнопку создания/редактирования (✎),
        // чтобы открыть Control panel
        onView(withId(R.id.news_edit_button)).perform(click())

        // Ждём открытия Control panel
        val cpElement = device.wait(Until.findObject(By.text("Control panel")), 5_000L)
        assertTrue("Control panel did not open", cpElement != null)
    }

    @Test
    fun openControlPanelAndCheckList() {
        Log.d("ControlPanelTest", "=== Start: openControlPanelAndCheckList ===")

        openControlPanel()

        controlPanelPage
            .checkControlPanelIsDisplayed()
            .checkNewsListIsDisplayed()

        Log.d("ControlPanelTest", "Control panel verified")
    }

    /**
     * Тест, выявляющий баг с некорректным отображением Creation date.
     * Ожидаем год "2026", но приложение показывает год "58714".
     * Тест падает — это ожидаемое падение (баг подтверждён).
     */
    @Test
    fun creationDateShouldNotContainInvalidYear() {
        Log.d("ControlPanelTest", "=== Start: creationDateShouldNotContainInvalidYear ===")

        openControlPanel()

        // Находим значение поля Creation date через UiAutomator
        val creationDateElement = device.wait(
            Until.findObject(By.res("ru.edu.qamid", "news_item_create_date_text_view")),
            5_000L
        )
        assertTrue("Creation date field not found", creationDateElement != null)

        val value = creationDateElement!!.text
        Log.d("ControlPanelTest", "Creation date value = $value")

        // Проверяем: год НЕ должен быть 587xx.
        // Ожидаем 2026. Но приложение показывает 58714 — тест падает, фиксируя баг.
        val hasInvalidYear = value.contains("587")
        assertTrue(
            "BUG: Creation date shows invalid year in '$value'. Expected year 2026.",
            !hasInvalidYear
        )
    }
}