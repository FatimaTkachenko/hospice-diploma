package ru.edu.qamid.base

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import io.qameta.allure.kotlin.Allure
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runner.RunWith
import ru.edu.qamid.R
import ru.edu.qamid.ui.AppActivity
import java.io.File

@RunWith(AndroidJUnit4::class)
abstract class BaseTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(AppActivity::class.java)

    protected val device: UiDevice
        get() = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @get:Rule
    val screenshotOnFailure = object : TestWatcher() {
        override fun failed(e: Throwable?, description: Description?) {
            super.failed(e, description)
            try {
                val screenshotDir = File(
                    InstrumentationRegistry.getInstrumentation()
                        .targetContext.filesDir, "screenshots"
                )
                if (!screenshotDir.exists()) screenshotDir.mkdirs()
                val file = File(
                    screenshotDir,
                    "${description?.methodName}_${System.currentTimeMillis()}.png"
                )
                device.takeScreenshot(file)

                Allure.attachment(
                    "screenshot_on_failure",
                    file.inputStream(),
                    "image/png",
                    "png"
                )
            } catch (ignored: Throwable) {
            }
        }
    }

    @Before
    fun setUp() {
        // Если приложение авторизовано (после предыдущего теста) — делаем logout
        if (isViewDisplayed(R.id.main_menu_image_button)) {
            performLogout()
        }
        waitForLoginScreen()
    }

    @After
    fun tearDown() {
        // После каждого теста — выходим из приложения, чтобы следующий стартовал с логина
        try {
            if (isViewDisplayed(R.id.main_menu_image_button)) {
                performLogout()
                waitForLoginScreen()
            }
        } catch (ignored: Throwable) {
        }
    }

    private fun performLogout() {
        try {
            onView(withId(R.id.authorization_image_button)).perform(click())
            val logOut = device.wait(Until.findObject(By.text("Log out")), 5_000L)
            if (logOut != null) {
                device.click(logOut.visibleBounds.centerX(), logOut.visibleBounds.centerY())
            }
        } catch (ignored: Throwable) {
        }
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
        waitForView(withId(R.id.login_edit_text), 40_000L, "Login screen")
    }

    protected fun waitForMainScreen() {
        waitForView(withId(R.id.main_menu_image_button), 40_000L, "Main screen")
    }

    protected fun waitForText(text: String, timeoutMs: Long = 15_000L) {
        waitForView(withText(text), timeoutMs, "Text '$text'")
    }

    private fun waitForView(
        matcher: Matcher<android.view.View>,
        timeoutMs: Long,
        screenName: String
    ) {
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