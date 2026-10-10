package ru.edu.qamid.pages

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import io.qameta.allure.kotlin.Allure
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import ru.edu.qamid.R

/**
 * Page Object экрана "Control panel" (управление новостями).
 */
class ControlPanelPage {

    private val controlPanelTitle: ViewInteraction = onView(withText("Control panel"))
    private val newsRecyclerView: ViewInteraction = onView(withId(R.id.news_list_recycler_view))
    private val createNewsButton: ViewInteraction = onView(withId(R.id.news_edit_button))

    fun checkControlPanelIsDisplayed(): ControlPanelPage {
        Allure.step("Проверить, что Control panel отображается") {
            controlPanelTitle.check(matches(isDisplayed()))
        }
        return this
    }

    fun checkNewsListIsDisplayed(): ControlPanelPage {
        Allure.step("Проверить, что список новостей отображается") {
            newsRecyclerView.check(matches(isDisplayed()))
        }
        return this
    }

    fun clickCreateNewsButton(): ControlPanelPage {
        Allure.step("Нажать кнопку создания новости (+)") {
            createNewsButton.perform(click())
        }
        return this
    }

    fun clickEditFirstNews(): ControlPanelPage {
        Allure.step("Нажать кнопку редактирования первой новости") {
            newsRecyclerView.perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    clickChildViewWithId(R.id.news_item_edit_image_view)
                )
            )
        }
        return this
    }

    fun clickDeleteFirstNews(): ControlPanelPage {
        Allure.step("Нажать кнопку удаления первой новости") {
            newsRecyclerView.perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    clickChildViewWithId(R.id.news_item_delete_image_view)
                )
            )
        }
        return this
    }

    fun checkNewsWithTitleExists(title: String): ControlPanelPage {
        Allure.step("Проверить, что новость с заголовком '$title' существует в списке") {
            newsRecyclerView.perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(withText(title))
                )
            )
            onView(
                allOf(
                    withId(R.id.news_item_title_text_view),
                    withText(title)
                )
            ).check(matches(isDisplayed()))
        }
        return this
    }

    /**
     * Возвращает ViewAction, кликающий по дочерней view с заданным id
     * внутри элемента RecyclerView.
     */
    private fun clickChildViewWithId(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? = null

            override fun getDescription(): String = "Click child view with id $id"

            override fun perform(uiController: UiController, view: View) {
                val childView = view.findViewById<View>(id)
                    ?: throw AssertionError("Child view with id $id not found")
                childView.performClick()
                uiController.loopMainThreadUntilIdle()
            }
        }
    }
}