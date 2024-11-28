package org.iikun.iik.ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.iikun.iik.ui.home.IndexBottom
import org.iikun.iik.ui.home.MeBottom
import org.iikun.iik.ui.home.MessageBottom
import org.iikun.iik.ui.home.StateBottom
import org.iikun.iik.ui.home.TreeBottom
import org.iikun.iik.ui.load.TreeAppLoad

/**
 * 导航路由
 *
 * @creator: ii_kun
 * @email: weijikun1@icloud.com
 * @time 2024/10/25 17:34
 */
@Composable
fun NavigationApp(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "load") {
        // 加载页面
        composable("load") {
            TreeAppLoad(nav = navController)
        }
        // 主页面
        composable("Index") {
            BottomNavigationHome(nav = navController)
        }
    }
}






















