package org.iikun.iik

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.Typeface
import org.iikun.iik.ui.NavigationApp
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import tree_app.composeapp.generated.resources.Res
import tree_app.composeapp.generated.resources.Source
import tree_app.composeapp.generated.resources.simhei
import tree_app.composeapp.generated.resources.simyou


/**
 * 树洞漫画
 */
@Composable
@Preview
fun App() {
    MaterialTheme(
        // 不适用字体打包wasm会出现乱码
        typography = Typography(
            defaultFontFamily = FontFamily(Font(Res.font.simhei, weight = FontWeight.Normal))
        )
    ) {
        NavigationApp()
    }
}













