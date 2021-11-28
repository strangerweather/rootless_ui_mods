package com.strangerweather.rootlessuimods.components.tabs

import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState, listOfTitles: List<String>) {
    val scope = rememberCoroutineScope()

    TabRow(selectedTabIndex = pagerState.currentPage, divider = {
        TabRowDefaults.Divider(
            thickness = 2.dp,
            color = Color.DarkGray
        )
    }) {
        listOfTitles.forEachIndexed { index, _ ->

            Tab(selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = {
                    Text(
                        listOfTitles[index],
                        color = if (pagerState.currentPage == index) Color.White else Color.LightGray
                    )
                }
            )
        }
    }
}
