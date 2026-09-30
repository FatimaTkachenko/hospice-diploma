package ru.edu.qamid.base

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith
import ru.edu.qamid.R
import ru.edu.qamid.ui.AppActivity

@RunWith(AndroidJUnit4::class)
abstract class BaseTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(AppActivity::class.java)

    /**
     * Ждёт появления экрана логина (до 15 секунд).
     */
    protected fun waitForLoginScreen() {
        waitForView(R.id.login_edit_text, 15_000L, "Login screen")
    }

    /**
     * Ждёт появления главного экрана (до 15 секунд).
     */
    protected fun waitForMainScreen() {
        waitForView(R.id.main_menu_image_button, 15_000L, "Main screen")
    }

    /**
     * Универсальное ожидание появления view по id.
     * Без sleep() — просто опрос с интервалом.
     */
    private fun waitForView(viewId: Int, timeoutMs: Long, screenName: String) {
        val startTime = System.currentTimeMillis()
        var lastError: Throwable? = null
        while (System.currentTimeMillis() - startTime < timeoutMs) {
            try {
                onView(withId(viewId)).check(matches(isDisplayed()))
                return
            } catch (e: Throwable) {
                lastError = e
            }
        }
        throw AssertionError("$screenName did not appear within $timeoutMs ms", lastError)
    }
}