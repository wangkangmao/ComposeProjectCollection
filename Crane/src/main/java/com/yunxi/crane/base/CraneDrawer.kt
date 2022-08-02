package com.yunxi.crane.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yunxi.crane.R
import com.yunxi.crane.ui.CraneTheme

/**
 * @author: created by wangkm
 * @time: 2022/08/02 12:11
 * @desc：
 * @email: 1240413544@qq.com
 */

private val screens = listOf(
    R.string.screen_title_find_trips,
    R.string.screen_title_my_trips,
    R.string.screen_title_saved_trips,
    R.string.screen_title_price_alerts,
    R.string.screen_title_my_account
)

@Composable
fun CraneDrawer(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 48.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_crane_drawer),
            contentDescription = stringResource(R.string.cd_drawer)
        )
        for (screenTitleResource in screens) {
            Spacer(Modifier.height(24.dp))
            Text(text = stringResource(id = screenTitleResource), style = MaterialTheme.typography.h4)
        }
    }
}

@Preview
@Composable
fun CraneDrawerPreview() {
    CraneTheme {
        CraneDrawer()
    }
}