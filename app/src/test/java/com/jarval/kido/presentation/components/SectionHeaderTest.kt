package com.jarval.kido.presentation.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.jarval.kido.ui.theme.KidoTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SectionHeaderTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `SectionHeader displays correctly and returns the right category name`(){
        //GIVEN
        var capturedCategory = ""
        val title = "Test title"
        val actionLabel = "Test action label"

        composeTestRule.setContent {
            KidoTheme() {
                SectionHeader(
                    title, actionLabel, { capturedCategory = it }
                )
            }
        }

        //THEN
        composeTestRule.onNodeWithText(title).assertExists()
        composeTestRule.onNodeWithText(actionLabel).assertExists()

        //WHEN
        composeTestRule.onNodeWithText(actionLabel).performClick()

        //THEN
        assert(capturedCategory == "This is the Category Name")

    }

}