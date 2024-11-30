package org.iikun.iik.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import tree_app.composeapp.generated.resources.Res
import tree_app.composeapp.generated.resources.bofang
import tree_app.composeapp.generated.resources.demo
import tree_app.composeapp.generated.resources.l
import tree_app.composeapp.generated.resources.r

// 数据模型
data class HomePagerItem(
    val title: String,
    val created: String,
    val size: String,
    val time: String,
    val score: String,
    val img: DrawableResource // 图片
)

/**
 *
 * 主页 - 探索 轮播图样式
 * @creator: ii_kun
 * @email: weijikun1@icloud.com
 * @time 2024/10/25 17:34
 */
@Composable
fun HomePagerBanner(
    modifier: Modifier = Modifier,
    image: DrawableResource
) {
    Box(modifier = modifier.fillMaxSize()) {
        // 主页图片 - 中间透明区域
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        // 底部区域 - 渐深绿色
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp) // 设置渐变区域的高度
                .align(Alignment.BottomCenter) // 底部对齐
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent, // 深绿色
                            Color(94,133,104).copy(alpha = 0.15f), // 浅绿色
                            Color(94,133,104).copy(alpha = 0.25f), // 浅绿色
                            Color(94,133,104).copy(alpha = 0.35f), // 浅绿色
                            Color(94,133,104).copy(alpha = 0.45f), // 浅绿色
                            Color(94,133,104).copy(alpha = 0.65f), // 浅绿色
                            Color(94,133,104).copy(alpha = 0.85f), // 浅绿色
                            Color(94, 133, 104).copy(alpha = 0.97f), // 浅绿色
                            Color(94, 133, 104).copy(alpha = 0.98f), // 浅绿色
                            Color(94, 133, 104).copy(alpha = 1f), // 浅绿色
                        )
                    )
                )
        )
    }
}


/**
 * 主要内容
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HoneBanner(
    modifier: Modifier = Modifier,
    items: List<HomePagerItem>
) {
    val pagerState = rememberPagerState(pageCount = { items.size })
    val coroutineScope = rememberCoroutineScope()
    val autoScrollInterval = 3000L // 自动切换时间间隔，单位为毫秒

    // 自动滚动
    LaunchedEffect(pagerState) {
        while (true) {
            delay(autoScrollInterval)
            coroutineScope.launch {
                val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(600.dp) // 设置明确高度
    ) {
        Box(modifier = modifier.fillMaxSize()) {

            // 使用pager来播放图片
            HorizontalPager(
                state = pagerState,
                modifier = modifier.fillMaxSize()
            ) { page ->
                val item = items[page]
                HomePagerBanner(image = item.img)
            }

            // 左右按钮
            Row(
                modifier = modifier
                    .align(Alignment.Center),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {
                    coroutineScope.launch {
                        val previousPage = (pagerState.currentPage - 1).coerceAtLeast(0)
                        pagerState.animateScrollToPage(previousPage)
                    }
                }) {
                    Icon(
                        modifier = modifier.size(40.dp),
                        painter = painterResource(Res.drawable.r),
                        contentDescription = "Previous",
                        tint = Color(0f, 0f, 0f, 0.40f)
                    )
                }
                Spacer(modifier = Modifier.weight(1f)) // 空间分隔
                IconButton(onClick = {
                    coroutineScope.launch {
                        val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
                        pagerState.animateScrollToPage(nextPage)
                    }
                }) {
                    Icon(
                        modifier = modifier.size(40.dp),
                        painter = painterResource(Res.drawable.l),
                        contentDescription = "Next",
                        tint = Color(0f, 0f, 0f, 0.40f)
                    )
                }
            }

            // 显示信息
            Box(
                modifier = modifier
                    .align(Alignment.BottomStart)
            ) {
                val currentItem = items[pagerState.currentPage]
                FlowColumn {
                    Row(
                        modifier = modifier.padding(start = 10.dp)
                    ) {
                        Text(
                            text = currentItem.title,
                            fontSize = 30.sp,
                            color = Color(255, 240, 225),
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Row {

                        // 推荐视频简介
                        Column(modifier = modifier.weight(1f).padding(start = 10.dp)) {
                            Text(
                                modifier = modifier,
                                text = "^${currentItem.created}",
                                fontSize = 14.sp,
                                color = Color(255, 240, 225)
                            )
                            Text(
                                text = "${currentItem.size}集 ${currentItem.time}时长",
                                fontSize = 14.sp,
                                color = Color(255, 240, 225)
                            )
                        }

                        // TODO 播放按钮
                        Column(
                            modifier = modifier
                                .weight(1f)
                                .padding(bottom = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            IconButton(onClick = { }) {
                                Icon(
                                    modifier = modifier
                                        .size(60.dp),
                                    painter = painterResource(Res.drawable.bofang),
                                    contentDescription = null,
                                    tint = Color(255, 240, 224)
                                )
                            }
                        }

                        // 显示评分
                        Column(
                            modifier = modifier
                                .weight(1f)
                                .padding(end = 10.dp, bottom = 5.dp),
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            Text(
                                text = "${currentItem.score}分",
                                fontSize = 30.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(255, 162, 67),
                                style = MaterialTheme.typography.h6.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(255, 163, 68),
                                    shadow = Shadow(
                                        color = Color.Black,        // 阴影的颜色
                                        offset = Offset(9f, 15f),   // 阴影的偏移量
                                        blurRadius = 2f             // 阴影的模糊度
                                    )
                                )
                            )
                        }
                    }

                    Spacer(modifier.height(10.dp))

                    // 圆点指示器
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 14.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        items.forEachIndexed { index, _ ->
                            Box(
                                modifier = Modifier
                                    .size(18.dp) // 总大小
                                    .padding(3.dp) // 圆点之间的间距
                                    .let { baseModifier ->
                                        if (pagerState.currentPage == index) {
                                            // 当前页：填充颜色
                                            baseModifier.background(
                                                Color(255, 240, 225),
                                                shape = CircleShape
                                            )
                                        } else {
                                            // 非当前页：仅边缘
                                            baseModifier
                                                .border(
                                                    width = 3.dp, // 边框厚度
                                                    color = Color(255, 240, 225), // 边框颜色
                                                    shape = CircleShape
                                                )
                                                .background(Color.Transparent) // 中间透明
                                        }
                                    }
                            )
                        }
                    }


                }
            }


        }
    }
}




























