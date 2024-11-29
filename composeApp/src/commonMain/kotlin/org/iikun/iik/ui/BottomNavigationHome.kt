package org.iikun.iik.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.iikun.iik.ui.home.IndexBottom
import org.iikun.iik.ui.home.MeBottom
import org.iikun.iik.ui.home.MessageBottom
import org.iikun.iik.ui.home.StateBottom
import org.iikun.iik.ui.home.TreeBottom
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import tree_app.composeapp.generated.resources.Res
import tree_app.composeapp.generated.resources.index
import tree_app.composeapp.generated.resources.load
import tree_app.composeapp.generated.resources.me
import tree_app.composeapp.generated.resources.msg
import tree_app.composeapp.generated.resources.pal
import tree_app.composeapp.generated.resources.tree

/**
 * 点击底部按钮切换页面
 * @creator: ii_kun
 * @email: weijikun1@icloud.com
 * @time 2024/10/25 17:34
 */
@Composable
fun BottomNavigationHome(
    modifier: Modifier = Modifier,
    nav: NavHostController
) {
    var numberUI by remember{ mutableStateOf(0) }
    // 使用一个 state 来控制底部按钮的显示/隐藏
    var isBottomNavVisible by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when(numberUI) {
            0 -> IndexBottom(onUpdate = { isBottomNavVisible = it })
            1 -> TreeBottom()
            2 -> StateBottom()
            3 -> MessageBottom()
            4 -> MeBottom()
            else -> IndexBottom(onUpdate = { isBottomNavVisible = it })
        }

        // 底部导航栏
        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.BottomCenter) // 确保导航栏对齐底部并居中
                .padding(15.dp)
                .clip(RoundedCornerShape(40.dp))
                .fillMaxWidth(), // 保证底部导航栏宽度,
            visible = isBottomNavVisible
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter) // 确保导航栏对齐底部并居中
                    .padding(15.dp)
                    .clip(RoundedCornerShape(40.dp))
                    .fillMaxWidth() // 保证底部导航栏宽度
            ) {
                AnimatedBottomNav(
                    onItemSelected = { numberId -> numberUI = numberId }
                )
            }
        }

    }
}

/**
 * 自定义底部导航栏，包含5个按钮
 */
@Composable
fun AnimatedBottomNav(
    modifier: Modifier = Modifier,
    onItemSelected: (Int) -> Unit = {}
) {
    val selectedIndex = remember { mutableStateOf(0) } // 当前选中的按钮索引

    val items = listOf(
        BottomNavItem("动画", Res.drawable.index, ""),
        BottomNavItem("树里", Res.drawable.tree, ""),
        BottomNavItem("动态", Res.drawable.pal, ""),
        BottomNavItem("消息", Res.drawable.msg, ""),
        BottomNavItem("我的", Res.drawable.me, "")
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .widthIn(max = 400.dp)
            .background(Color(0f, 0f, 0f, 1f)) // 黑色，透明度 80%
            .padding(start = 5.dp, end = 5.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEachIndexed { index, item ->
            AnimatedBottomNavItem(
                icon = item.icon,
                label = item.title,
                isSelected = selectedIndex.value == index,
                onClick = {
                    selectedIndex.value = index
                    onItemSelected(index) // 回调选中的按钮索引
                }
            )
        }
    }
}

/**
 * 单个底部导航按钮，选中时显示动画文字
 */
@Composable
fun AnimatedBottomNavItem(
    icon: DrawableResource,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val textWidth by animateDpAsState(
        targetValue = if (isSelected) 40.dp else 0.dp, // 动画控制文字宽度
        animationSpec = tween(durationMillis = 300)
    )

    Row(
        modifier = Modifier
            .clickable { onClick() }
            .height(35.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(if (isSelected) Color(94,133,104) else Color.Transparent)
            .padding(start = 10.dp)
            .animateContentSize(), // 平滑调整大小
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 图标
        Icon(
            painter = painterResource(icon),
            contentDescription = label,
            tint = Color(255,240,225),
            modifier = Modifier.size(20.dp)
        )
        // 动画显示文字
        Spacer(modifier = Modifier.width(2.dp))
        Row(
            modifier = Modifier
                .width(textWidth)
                .height(30.dp), // 保持和 Row 一致的高度,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isSelected) {
                Text(
                    text = label,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center, // 文字在自身内部居中
                    fontWeight = FontWeight.Bold,
                    color = Color(255, 240, 225),
                    modifier = Modifier
                        .fillMaxWidth() // Text 占满 Box 宽度
                        .fillMaxHeight()
                )
            }
        }
    }
}













