package com.wangkm.jetsnack.ui.home

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wangkm.jetsnack.model.Filter
import com.wangkm.jetsnack.model.SnackCollection
import com.wangkm.jetsnack.model.SnackRepo
import com.wangkm.jetsnack.ui.components.FilterBar
import com.wangkm.jetsnack.ui.components.JetsnackDivider
import com.wangkm.jetsnack.ui.components.JetsnackSurface
import com.wangkm.jetsnack.ui.components.SnackCollection

/**
 * @author: created by wangkm
 * @time: 2022/07/12 12:25
 * @desc：
 * @email: 1240413544@qq.com
 */

@Composable
fun Feed(
    onSnackClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val snackCollections = remember { SnackRepo.getSnacks() }
    val filters = remember { SnackRepo.getFilters() }
    Feed(
        snackCollections,
        filters,
        onSnackClick,
        modifier
    )
}

@Composable
private fun Feed(
    snackCollections: List<SnackCollection>,
    filters: List<Filter>,
    onSnackClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {

    JetsnackSurface(modifier = modifier.fillMaxSize()) {
        Box {
            SnackCollectionList(snackCollections, filters, onSnackClick)
            DestinationBar()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun SnackCollectionList(
    snackCollections: List<SnackCollection>,
    filters: List<Filter>,
    onSnackClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    var filtersVisible by rememberSaveable { mutableStateOf(false) }
    Box(modifier) {
        LazyColumn {

            item {
                Spacer(
                    Modifier.windowInsetsTopHeight(
                        WindowInsets.statusBars.add(WindowInsets(top = 56.dp))
                    )
                )
                FilterBar(filters, onShowFilters = { filtersVisible = true })
            }
            itemsIndexed(snackCollections) { index, snackCollection ->
                if (index > 0) {
                    JetsnackDivider(thickness = 2.dp)
                }

                SnackCollection(
                    snackCollection = snackCollection,
                    onSnackClick = onSnackClick,
                    index = index
                )
            }
        }
    }
    AnimatedVisibility(
        visible = filtersVisible,
        enter = slideInVertically() + expandVertically(
            expandFrom = Alignment.Top
        ) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        FilterScreen(
            onDismiss = { filtersVisible = false }
        )
    }
}

