package ru.edu.qamid.base

import androidx.test.espresso.Espresso.onView
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
     * Ждёт появления поля Login на экране (до 10 секунд).
     * Используется вместо sleep().
     */
    protected fun waitForLoginScreen() {
        val timeoutMs = 10_000L
        val startTime = System.currentTimeMillis()
        while (System.currentTimeMillis() - startTime < timeoutMs) {
            try {
                onView(withId(R.id.login_edit_text)).check(
                    androidx.test.espresso.assertion.ViewAssertions.matches(
                        androidx.test.espresso.matcher.ViewMatchers.isDisplayed()
                    )
                )
                return
            } catch (e: Throwable) {
                // Пока не нашли — ждём
            }
        }
        throw AssertionError("Login screen did not appear within $timeoutMs ms")
    }
}