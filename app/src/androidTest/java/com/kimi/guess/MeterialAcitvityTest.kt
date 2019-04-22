package com.kimi.guess

import android.util.Log
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MeterialAcitvityTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<MaterialActivity>(MaterialActivity::class.java)


    @Test
    fun guessWrong() {
        val resources = activityTestRule.activity.resources
        val secret = activityTestRule.activity.secretNumber.secret
        for (n in 1..10) {
            if (n != secret) {
                onView(withId(R.id.ed_number)).perform(clearText())
                onView(withId(R.id.ed_number)).perform(typeText(n.toString()))
                onView(withId(R.id.ok_button)).perform(click())

                val message =
                    if (n < secret) resources.getString(R.string.bigger)
                    else resources.getString(R.string.smaller)

                onView(withText(message)).check(ViewAssertions.matches(isDisplayed()))
                onView(withText(resources.getString(R.string.ok))).perform(click())
            }
        }
    }

    @Test
    fun testFloatingActionButton(){
        val resources = activityTestRule.activity.resources

        // 輸入數字
        onView(withId(R.id.ed_number)).perform(typeText("10"))
        onView(withId(R.id.ok_button)).perform(click())

        // 按下dialog ok鍵，並關閉SoftKeyboard。SoftKeyboard不關閉會影響後面測試
        onView(withText(resources.getString(R.string.ok))).perform(click())
            .perform(closeSoftKeyboard())

        // 測試右下方重玩的 FloatingActionButton
        onView(withId(R.id.fab)).perform(click())
        onView(withText(resources.getString(R.string.ok))).perform(click())

        // 確認成功重設counter計數器為0
        onView(withId(R.id.counter)).check(ViewAssertions.matches(withText("0")))

    }

}