package org.iikun.iik.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * @creator: ii_kun
 * @email: weijikun1@icloud.com
 * @time 2024/10/25 17:34
 */
@Composable
fun MeBottom(modifier: Modifier = Modifier) {
    Row(modifier.fillMaxSize().background(Color.Black)) {
        Text("我的主页")
    }
}