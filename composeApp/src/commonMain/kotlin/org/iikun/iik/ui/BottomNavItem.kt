package org.iikun.iik.ui

import org.jetbrains.compose.resources.DrawableResource

/**
 * @creator: ii_kun
 * @email: weijikun1@icloud.com
 * @time 2024/10/25 17:34
 */
data class BottomNavItem(
    val title: String,
    val icon: DrawableResource,
    val route: String
)
