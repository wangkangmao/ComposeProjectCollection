package com.wangkm.jetcaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoTracker
import com.wangkm.jetcaster.ui.JetcasterApp
import com.wangkm.jetcaster.ui.theme.JetcasterTheme
import com.wangkm.jetcaster.util.DevicePosture
import com.wangkm.jetcaster.util.isBookPosture
import com.wangkm.jetcaster.util.isSeparatingPosture
import com.wangkm.jetcaster.util.isTableTopPosture
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val devicePosture = WindowInfoTracker.getOrCreate(this).windowLayoutInfo(this)
            .flowWithLifecycle(this.lifecycle)
            .map { layoutInfo ->
                val foldingFeature =
                    layoutInfo.displayFeatures.filterIsInstance<FoldingFeature>().firstOrNull()
                when {
                    isTableTopPosture(foldingFeature) ->
                        DevicePosture.TableTopPosture(foldingFeature.bounds)
                    isBookPosture(foldingFeature) ->
                        DevicePosture.BookPosture(foldingFeature.bounds)
                    isSeparatingPosture(foldingFeature) ->
                        DevicePosture.SeparatingPosture(foldingFeature.bounds, foldingFeature.orientation)
                    else -> DevicePosture.NormalPosture
                }
            }
            .stateIn(
                scope = lifecycleScope,
                started = SharingStarted.Eagerly,
                initialValue = DevicePosture.NormalPosture
            )

        setContent {
            JetcasterTheme {
                JetcasterApp(devicePosture)
            }
        }

    }
}
