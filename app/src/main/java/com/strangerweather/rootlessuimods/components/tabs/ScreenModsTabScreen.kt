package com.strangerweather.rootlessuimods.components.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.strangerweather.rootlessuimods.R
import com.strangerweather.rootlessuimods.utils.MainViewModel

@ExperimentalPagerApi
@Composable
fun ScreenModsTabScreen() {
    val pagerState = rememberPagerState(initialPage = 0)
    Column(Modifier.height(200.dp)) {
        Tabs(pagerState, listOf("Accent", "Drawers", "Text"))
        ScreenModsTabsContent(pagerState)
    }

}

@ExperimentalPagerApi
@Composable
fun ScreenModsTabsContent(pagerState: PagerState) {

    val viewModel: MainViewModel = viewModel()

    HorizontalPager(state = pagerState, count = 3) { page ->
        when (page) {
            0 -> Tabs(
                tabTitle = stringResource(id = R.string.tab1_title), tabText = stringResource(
                    id = R.string.tab1_explanation
                )
            )
            1 -> Tabs(
                tabTitle = stringResource(id = R.string.tab2_title), tabText = stringResource(
                    id = R.string.tab2_explanation
                )
            )
            2 -> Tabs(
                tabTitle = stringResource(id = R.string.tab3_title), tabText = stringResource(
                    id = R.string.tab3_explanation
                )
            )
        }
        viewModel.onPageChanged(currentPage)
    }
}