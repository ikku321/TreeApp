package org.iikun.iik.ui.load

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import tree_app.composeapp.generated.resources.Res
import tree_app.composeapp.generated.resources.compose_multiplatform
import tree_app.composeapp.generated.resources.load

/**
 * 加载页
 *
 * @creator: ii_kun
 * @email: weijikun1@icloud.com
 * @time 2024/10/25 17:34
 */
@Composable
fun TreeAppLoad(
    modifier: Modifier = Modifier,
    nav: NavHostController
) {

    LaunchedEffect(Unit, {
        delay(2000)
        nav.navigate("Index")
    })
    Image(
        modifier = modifier
            .fillMaxSize(),
        painter = painterResource(Res.drawable.load),
        contentDescription = null
    )
}




















