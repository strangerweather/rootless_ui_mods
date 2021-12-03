package com.strangerweather.rootlessuimods.components.tabs

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun GenericTabScreen (pagerState: PagerState, listOfTitles: List<String>) {
    val scope = rememberCoroutineScope()

    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        divider = {
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
