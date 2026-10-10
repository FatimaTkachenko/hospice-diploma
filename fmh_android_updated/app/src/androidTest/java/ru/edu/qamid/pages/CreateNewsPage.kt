package ru.edu.qamid.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import io.qameta.allure.kotlin.Allure
import ru.edu.qamid.R

/**
 * Page Object формы создания новости (Creating News).
 */
class CreateNewsPage {

    private val device: UiDevice =
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    private val creatingTitle: ViewInteraction = onView(withText("Creating"))
    private val titleField: ViewInteraction = onView(withId(R.id.news_title_edit_text))
    private val descriptionField: ViewInteraction = onView(withId(R.id.news_description_edit_text))
    private val publishDateField: ViewInteraction = onView(withId(R.id.news_publish_date_edit_text))
    private val publishTimeField: ViewInteraction = onView(withId(R.id.news_publish_time_edit_text))
    private val categoryField: ViewInteraction = onView(withId(R.id.news_category_auto_complete))
    private val saveButton: ViewInteraction = onView(withId(R.id.news_save_button))
    private val cancelButton: ViewInteraction = onView(withId(R.id.news_cancel_button))

    fun checkTitleIsDisplayed(): CreateNewsPage {
        Allure.step("Проверить, что форма создания новости отображается") {
            creatingTitle.check(matches(isDisplayed()))
        }
        return this
    }

    fun enterTitle(title: String): CreateNewsPage {
        Allure.step("Ввести заголовок: $title") {
            titleField.perform(replaceText(title), closeSoftKeyboard())
        }
        return this
    }

    fun enterDescription(description: String): CreateNewsPage {
        Allure.step("Ввести описание: $description") {
            descriptionField.perform(replaceText(description), closeSoftKeyboard())
        }
        return this
    }

    fun enterPublishDate(date: String): CreateNewsPage {
        Allure.step("Ввести дату публикации: $date") {
            publishDateField.perform(replaceText(date), closeSoftKeyboard())
        }
        return this
    }

    fun enterPublishTime(time: String): CreateNewsPage {
        Allure.step("Ввести время публикации: $time") {
            publishTimeField.perform(replaceText(time), closeSoftKeyboard())
        }
        return this
    }

    fun selectCategory(): CreateNewsPage {
        Allure.step("Выбрать первую категорию из выпадающего списка") {
            categoryField.perform(click())
            device.waitForIdle()
            device.click(540, 771)
            device.waitForIdle()
        }
        return this
    }

    fun clickSave(): CreateNewsPage {
        Allure.step("Нажать кнопку SAVE") {
            saveButton.perform(click())
        }
        return this
    }

    fun clickCancel(): CreateNewsPage {
        Allure.step("Нажать кнопку CANCEL") {
            cancelButton.perform(click())
        }
        return this
    }

    fun fillAndSave(
        title: String,
        description: String,
        date: String,
        time: String
    ): CreateNewsPage {
        Allure.step("Заполнить форму и сохранить") {
            selectCategory()
            enterTitle(title)
            enterDescription(description)
            enterPublishDate(date)
            enterPublishTime(time)
            clickSave()
        }
        return this
    }
}