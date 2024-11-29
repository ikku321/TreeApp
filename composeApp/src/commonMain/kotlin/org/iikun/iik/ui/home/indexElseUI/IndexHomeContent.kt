package org.iikun.iik.ui.home.indexElseUI

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.iikun.iik.ui.HoneBanner
import org.iikun.iik.ui.home.homeBannerList
import org.iikun.iik.ui.home.videosItem

/**
 * 主页 - 探索内容
 *
 * @creator: ii_kun
 * @email: weijikun1@icloud.com
 * @time 2024/10/25 17:34
 */
@Composable
fun IndexHomeContent(
    modifier: Modifier = Modifier,
    onUpdate: (Boolean) -> Unit
) {
    // 创建 LazyListState 来监听列表的滚动状态
    val scrollState = rememberLazyListState()
    var lastScrollOffset by remember { mutableStateOf(0) } // 用来存储上一次的滚动偏移量
    // 使用一个 state 来控制底部按钮的显示/隐藏
    val isBottomNavVisible = remember { mutableStateOf(true) }
    // 监听列表滚动状态
    LaunchedEffect(scrollState.firstVisibleItemIndex, scrollState.firstVisibleItemScrollOffset) {
        val currentOffset = scrollState.firstVisibleItemScrollOffset
        // 判断是否向上或向下滑动
        if (currentOffset > lastScrollOffset) {
            // 向上滑动，隐藏底部按钮
            isBottomNavVisible.value = false
        } else if (currentOffset < lastScrollOffset) {
            // 向下滑动，显示底部按钮
            isBottomNavVisible.value = true
        }
        // 更新 lastScrollOffset 为当前的偏移量
        lastScrollOffset = currentOffset
        // 更新底部按钮的显示状态
        onUpdate(isBottomNavVisible.value)
    }

    // 轮播图海拔内容
    Column(
        modifier = modifier
    ) {
        // 内容列表
        LazyColumn(
            state = scrollState, // 给 LazyColumn 绑定滚动状态
            modifier = modifier.fillMaxHeight()
        ) {
            // 轮播图
            item {
                // 轮播图海报
                HoneBanner(items = homeBannerList)
            }

            // 瀑布流
            item {

                StaggeredVerticalGrid(
                    modifier = Modifier.fillMaxHeight(),
                    columns = 2,
                    spacing = 8.dp
                ) {
                    videosItem.forEach { item ->
                        // 视频列表UI
                        VideoCard(item = item, modifier = Modifier.padding(4.dp))
                    }
                }
                
            }
        }
    }
}