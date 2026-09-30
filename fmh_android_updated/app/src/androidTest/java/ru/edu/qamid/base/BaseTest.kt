package ru.edu.qamid.base

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import ru.edu.qamid.R
import ru.edu.qamid.ui.AppActivity

@RunWith(AndroidJUnit4::class)
abstract class BaseTest {

    private lateinit var scenario: ActivityScenario<AppActivity>

    protected val device: UiDevice
        get() = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @Before
    fun setUp() {
        // Запуск приложения
        val intent = Intent(
            ApplicationProvider.getApplicationContext(),
            AppActivity::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        scenario = ActivityScenario.launch(intent)

        // Приводим приложение в исходное состояние — экран логина
        ensureLoggedOut()
    }

    @After
    fun tearDown() {
        try {
            scenario.close()
        } catch (ignored: Throwable) {
        }
    }

    /**
     * Проверяет, что приложение на экране логина.
     * Если авторизовано — делает Logout.
     */
    private fun ensureLoggedOut() {
        // Ждём любое из двух состояний
        val timeoutMs = 20_000L
        val startTime = System.currentTimeMillis()
        while (System.currentTimeMillis() - startTime < timeoutMs) {
            // На логине?
            if (isViewDisplayed(R.id.login_edit_text)) return
            // На главном?
            if (isViewDisplayed(R.id.main_menu_image_button)) {
                performLogout()
                return
            }
        }
        throw AssertionError("Could not reach login screen within $timeoutMs ms")
    }

    private fun performLogout() {
        // Открываем меню профиля
        onView(withId(R.id.authorization_image_button)).perform(click())

        // Ждём "Log out" и кликаем через UI Automator
        val logOut = device.wait(Until.findObject(By.text("Log out")), 5_000L)
            ?: throw AssertionError("'Log out' not found in profile menu")
        device.click(logOut.visibleBounds.centerX(), logOut.visibleBounds.centerY())
    }

    private fun isViewDisplayed(viewId: Int): Boolean {
        return try {
            onView(withId(viewId)).check(matches(isDisplayed()))
            true
        } catch (e: Throwable) {
            false
        }
    }

    protected fun waitForLoginScreen() {
        waitForView(withId(R.id.login_edit_text), 20_000L, "Login screen")
    }

    protected fun waitForMainScreen() {
        waitForView(withId(R.id.main_menu_image_button), 20_000L, "Main screen")
    }

    protected fun waitForText(text: String, timeoutMs: Long = 10_000L) {
        waitForView(withText(text), timeoutMs, "Text '$text'")
    }

    private fun waitForView(matcher: Matcher<android.view.View>, timeoutMs: Long, screenName: String) {
        val startTime = System.currentTimeMillis()
        var lastError: Throwable? = null
        while (System.currentTimeMillis() - startTime < timeoutMs) {
            try {
                onView(matcher).check(matches(isDisplayed()))
                return
            } catch (e: Throwable) {
                lastError = e
            }
        }
        throw AssertionError("$screenName did not appear within $timeoutMs ms", lastError)
    }
}