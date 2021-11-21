package com.strangerweather.rootlessuimods.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun TabScreen() {
    val pagerState = rememberPagerState(initialPage = 0)
    Column() {
        Tabs(pagerState)
        TabsContent(pagerState)
    }

}

@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = 3) { page ->
        when (page) {
            0 -> Text(text = "Screen 1")
            1 -> Text(text = "Screen 2")
            2 -> Text(text = "Screen 3")
        }
    }
}

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {
    val list = listOf("Accent", "Drawers", "Text")
    val scope = rememberCoroutineScope()

    TabRow(selectedTabIndex = pagerState.currentPage, divider = {
        TabRowDefaults.Divider(
            thickness = 2.dp,
            color = Color.DarkGray
        )
    }) {
        list.forEachIndexed { index, _ ->

            Tab(selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = {
                    Text(
                        list[index],
                        color = if (pagerState.currentPage == index) Color.White else Color.LightGray
                    )
                }
            )
        }
    }
}
