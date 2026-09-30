package ru.edu.qamid.pages

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import ru.edu.qamid.R

/**
 * Page Object экрана "Control panel" (управление новостями).
 */
class ControlPanelPage {

    fun checkControlPanelIsDisplayed(): ControlPanelPage {
        onView(withText("Control panel")).check(matches(isDisplayed()))
        return this
    }

    fun checkNewsListIsDisplayed(): ControlPanelPage {
        onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()))
        return this
    }

    /**
     * Открывает форму создания новости — клик по "+" в панели.
     */
    fun clickCreateNewsButton(): ControlPanelPage {
        onView(withId(R.id.news_edit_button)).perform(click())
        return this
    }

    fun clickEditFirstNews(): ControlPanelPage {
        onView(withId(R.id.news_list_recycler_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                clickChildViewWithId(R.id.news_item_edit_image_view)
            )
        )
        return this
    }

    fun clickDeleteFirstNews(): ControlPanelPage {
        onView(withId(R.id.news_list_recycler_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                clickChildViewWithId(R.id.news_item_delete_image_view)
            )
        )
        return this
    }

    fun checkNewsWithTitleExists(title: String): ControlPanelPage {
        onView(withId(R.id.news_list_recycler_view))
            .perform(
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