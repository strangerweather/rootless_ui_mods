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
fun ColorModsTabScreen() {
    val pagerState = rememberPagerState(initialPage = 0)
    Column(Modifier.height(200.dp)) {
        GenericTabScreen(
            pagerState,
            listOf("Accent", "Drawers Light", "Drawers Dark", "Clock Text", "Testing")
        )
        ColorModsTabsContent(pagerState)
    }

}

@ExperimentalPagerApi
@Composable
fun ColorModsTabsContent(pagerState: PagerState) {

    val viewModel: MainViewModel = viewModel()

    HorizontalPager(state = pagerState, count = 5) { page ->
        when (page) {
            0 -> Tabs(
                tabTitle = stringResource(id = R.string.tab1_color_title), tabText = stringResource(
                    id = R.string.tab1_color_explanation
                )
            )
            1 -> Tabs(
                tabTitle = stringResource(id = R.string.tab2_color_title), tabText = stringResource(
                    id = R.string.tab2_color_explanation
                )
            )
            2 -> Tabs(
                tabTitle = stringResource(id = R.string.tab3_color_title), tabText = stringResource(
                    id = R.string.tab3_color_explanation
                )
            )
            3 -> Tabs(
                tabTitle = stringResource(id = R.string.tab4_color_title), tabText = stringResource(
                    id = R.string.tab4_color_explanation
                )
            )
            4 -> Tabs(
                tabTitle = "Testing", tabText = ""
            )
        }
        viewModel.onColorPageChanged(currentPage)
    }
}