package com.coinflip.takehome.ui.coinentity

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.coinflip.takehome.ui.coin.CoinCard
import com.coinflip.takehome.ui.coin.CoinMarketScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * UI tests for [CoinMarketScreen].
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class CoinNetworkMarketEntityScreenTest {

    @get:Rule var hiltrule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()


    @Before
    fun setup() {
        hiltrule.inject()

        composeTestRule.setContent {
            CoinCard(coinName = "Bitcoin", price = "500", imgUrl = "", onClick = {})
        }
    }

    @Test
    fun testCoinCard(){
        composeTestRule.onNodeWithText("Bitcoin").assertIsDisplayed()
        composeTestRule.onNodeWithText("500").assertIsDisplayed()
    }

}

