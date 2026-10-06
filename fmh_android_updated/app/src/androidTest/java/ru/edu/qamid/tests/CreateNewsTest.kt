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
import ru.edu.qamid.pages.CreateNewsPage
import ru.edu.qamid.pages.LoginPage
import ru.edu.qamid.pages.MainPage


@Epic("Мобильный хоспис")
@Feature("Работа с новостями")
@Story("Создание новости")
@Owner("Fatima Tkachenko")
class CreateNewsTest : BaseTest() {

    private val loginPage = LoginPage()
    private val mainPage = MainPage()
    private val controlPanelPage = ControlPanelPage()
    private val createNewsPage = CreateNewsPage()

    private fun openControlPanel() {
        waitForLoginScreen()
        loginPage.login("login2", "password2")
        waitForMainScreen()
        mainPage.clickAllNews()
        onView(withId(R.id.news_edit_button)).perform(click())
        val cpElement = device.wait(Until.findObject(By.text("Control panel")), 5_000L)
        assertTrue("Control panel did not open", cpElement != null)
    }

    /**
     * BUG-002: создание новости не работает из-за несоответствия категорий
     * в UI и метода convertNewsCategory в приложении.
     * Сервер возвращает 500 (StackOverflowError) при newsCategoryId=0.
     */
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("BUG-002: Создание новости не работает из-за newsCategoryId=0. Ожидаем возврат на Control panel — фактически форма остаётся открытой из-за ошибки 500 сервера.")
    fun createNewsFailsDueToCategoryBug() {
        Log.d("CreateNewsTest", "=== Start: createNewsFailsDueToCategoryBug ===")

        openControlPanel()

        val uniqueTitle = "AutoTest_${System.currentTimeMillis()}"

        onView(withId(R.id.add_news_image_view)).perform(click())
        val createTitle = device.wait(Until.findObject(By.text("Creating")), 5_000L)
        assertTrue("Create news form did not open", createTitle != null)

        createNewsPage.checkTitleIsDisplayed()
        createNewsPage.selectCategory()
        createNewsPage.enterTitle(uniqueTitle)
        createNewsPage.enterDescription("Auto description $uniqueTitle")
        createNewsPage.enterPublishDate("30.09.2026")
        createNewsPage.enterPublishTime("12:00")

        createNewsPage.clickSave()
        Log.d("CreateNewsTest", "SAVE clicked")

        val cpElement = device.wait(Until.findObject(By.text("Control panel")), 10_000L)
        assertTrue(
            "BUG-002: Форма не закрылась после SAVE. Сервер вернул 500 (StackOverflowError) " +
                    "из-за newsCategoryId=0. Проверить convertNewsCategory в Utils.kt.",
            cpElement != null
        )

        controlPanelPage.checkNewsWithTitleExists(uniqueTitle)
    }
}