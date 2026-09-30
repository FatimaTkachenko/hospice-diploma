package ru.edu.qamid.base

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith
import ru.edu.qamid.ui.AppActivity

@RunWith(AndroidJUnit4::class)
abstract class BaseTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(AppActivity::class.java)
}