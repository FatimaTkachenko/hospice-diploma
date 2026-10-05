package ru.edu.qamid.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import ru.edu.qamid.R

/**
 * Page Object формы создания новости (Creating News).
 */
class CreateNewsPage {

    private val device: UiDevice =
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    fun checkTitleIsDisplayed(): CreateNewsPage {
        onView(withText("Creating")).check(matches(isDisplayed()))
        return this
    }

    fun enterTitle(title: String): CreateNewsPage {
        onView(withId(R.id.news_title_edit_text))
            .perform(replaceText(title), closeSoftKeyboard())
        return this
    }

    fun enterDescription(description: String): CreateNewsPage {
        onView(withId(R.id.news_description_edit_text))
            .perform(replaceText(description), closeSoftKeyboard())
        return this
    }

    fun enterPublishDate(date: String): CreateNewsPage {
        onView(withId(R.id.news_publish_date_edit_text))
            .perform(replaceText(date), closeSoftKeyboard())
        return this
    }

    fun enterPublishTime(time: String): CreateNewsPage {
        onView(withId(R.id.news_publish_time_edit_text))
            .perform(replaceText(time), closeSoftKeyboard())
        return this
    }

    /**
     * Открывает dropdown категорий и кликает по первой категории ("Объявление")
     * через координаты, т.к. Material dropdown не попадает в UI-иерархию.
     */
    fun selectCategory(): CreateNewsPage {
        onView(withId(R.id.news_category_auto_complete)).perform(click())
        device.waitForIdle()
        // Координаты первой категории "Объявление" в открытом dropdown
        device.click(540, 771)
        device.waitForIdle()
        return this
    }

    fun clickSave(): CreateNewsPage {
        onView(withId(R.id.news_save_button)).perform(click())
        return this
    }

    fun clickCancel(): CreateNewsPage {
        onView(withId(R.id.news_cancel_button)).perform(click())
        return this
    }

    fun fillAndSave(
        title: String,
        description: String,
        date: String,
        time: String
    ): CreateNewsPage {
        selectCategory()
        enterTitle(title)
        enterDescription(description)
        enterPublishDate(date)
        enterPublishTime(time)
        clickSave()
        return this
    }
}