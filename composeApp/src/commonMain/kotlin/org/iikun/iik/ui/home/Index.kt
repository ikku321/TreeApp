package org.iikun.iik.ui.home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Text
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.iikun.iik.ui.HomePagerItem
import org.iikun.iik.ui.ModalDrawer.DrawerContent
import org.iikun.iik.ui.home.indexElseUI.IndexHomeContent
import org.iikun.iik.ui.home.indexElseUI.IndexHomeListItem
import org.jetbrains.compose.resources.painterResource
import tree_app.composeapp.generated.resources.Res
import tree_app.composeapp.generated.resources.demo
import tree_app.composeapp.generated.resources.demo2
import tree_app.composeapp.generated.resources.demo3
import tree_app.composeapp.generated.resources.demo4
import tree_app.composeapp.generated.resources.demo5
import tree_app.composeapp.generated.resources.muen
import tree_app.composeapp.generated.resources.serach
import kotlin.random.Random


// 轮播图内容（测试）
val homeBannerList = listOf(
    HomePagerItem("Blender像素动画", "搞设计的周老师", "3", "629:53", "6.7", Res.drawable.demo),
    HomePagerItem("Star Creation", "xxx作者", "3", "429:53", "7.7", Res.drawable.demo2),
    HomePagerItem("城镇青年", "xxx作者", "9", "129:53", "8.7", Res.drawable.demo3),
    HomePagerItem("猫和老鼠", "xxx作者", "999", "929:53", "9.7", Res.drawable.demo4),
    HomePagerItem("中国奇谭", "xxx作者", "9", "189:53", "10.0", Res.drawable.demo5),
)

val videosItem = ArrayList<IndexHomeListItem>()

/**
 * @creator: ii_kun
 * @email: weijikun1@icloud.com
 * @time 2024/10/25 17:34
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun IndexBottom(
    modifier: Modifier = Modifier,
    onUpdate: (Boolean) -> Unit
) {
    // 测试数据
    val videImg = listOf(
        Res.drawable.demo,
        Res.drawable.demo2,
        Res.drawable.demo3,
        Res.drawable.demo4,
        Res.drawable.demo5,
    )

    // 标题
    val titleList = listOf(
        "获奖动画《猪》引人深思",
        "最纯粹的感受!2D动画短片《Star Creation》",
        "动画短片《我和吸铁石和一个死去的朋友》",
        "星声》|“星星的孩子不为人知的内心独白”2024届南艺动画本科毕设",
        "Blender原创粉笔炭铅风格动画-孤独几平将我吞噬",
    )

    val createdList = listOf(
        "搞设计的周老师",
        "MAX放映厅",
        "Renderbus瑞云渲染",
        "思念永远"
    )

    videosItem.clear()
    repeat(10) {
        videosItem.add(
            IndexHomeListItem(
                "videos1",
                titleList[Random.nextInt(titleList.size)],
                videImg[Random.nextInt(videImg.size)],
                Random.nextInt(10).toString(),
                "139:54",
                createdList[Random.nextInt(createdList.size)],
                "9.7"
            )
        )
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed) // 抽屉状态
    val scope = rememberCoroutineScope() // 用于启动协程

    // 监听抽屉状态的变化
    LaunchedEffect(drawerState.isClosed) {
        if (drawerState.isClosed) {
            // 当抽屉关闭时，显示底部导航栏
            onUpdate(true)
        } else {
            onUpdate(false)
        }
    }

    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {
            // 左侧抽屉内容
            DrawerContent(onClose = { scope.launch { drawerState.close() } })
        },
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color(94, 133, 104))
        ) {
            FlowColumn {
                Box(modifier.fillMaxSize()) {

                    // 主页 - 探索内容
                    IndexHomeContent {
                        // 滑动时不隐藏底部导航栏
                        // onUpdate(it)
                    }


                    // 顶部菜单栏
                    Column(
                        modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Top栏
                        SelectTop(
                            modifier = modifier,
                            onMenu = {
                                // 打开则边菜单栏
                                scope.launch {
                                    onUpdate(false)
                                    drawerState.open()
                                }
                            },
                            // 主页点击事件
                            onClickHome = {  },
                            // 搜索按钮点击事件
                            onSearch = {  }
                        )
                    }
                }
            }
        }
    }
}


// 底部菜单选择栏数据模型
data class SelectMenuItem(
    val title: String,
    val id: String
)

/**
 * 子菜单选项
 */
@Composable
fun SelectMenu(
    modifier: Modifier = Modifier,
    items: List<SelectMenuItem>
) {
    // 子菜单边距
    val w = 30
    // 默认是s1选择
    var isActive by remember { mutableStateOf("s1") }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = w.dp, end = w.dp)
    ) {
        items.forEach { item ->
            Row(
                modifier = modifier
                    .weight(1f)
                    .height(40.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val isBoolean = item.id == isActive
                Text(
                    text = item.title,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isBoolean) Color.Black else Color(255,240,225),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .clickable {
                            isActive = item.id
                        }
                        .background(
                            if (isBoolean) Color(255, 240, 225) else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 3.dp)
                        .animateContentSize()
                )
            }
        }
    }
}

/**
 * top栏UI
 * @param onMenu 菜单栏点击事件
 * @param onSearch 搜索按钮点击事件
 * @param onClickHome 主页点击事件
 * @param onClickElse 其他点击事件
 */
@Composable
fun SelectTop(
    modifier: Modifier = Modifier,
    onMenu: (Boolean) -> Unit = {  },
    onSearch: (Boolean) -> Unit = {  },
    onClickHome: (Boolean) -> Unit = {  },
    onClickElse: (Boolean) -> Unit = {  }
) {
    // 记录当前选中的按钮索引
    val selectedIndex = remember { mutableStateOf(0) }
    val w = 1f
    val re = 3 // 左右边距

    Column(
        modifier = modifier
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = 1f), // 更深的黑色，渐变向透明
                        Color.Black.copy(alpha = 0.99f), // 更深的黑色，渐变向透明
                        Color.Black.copy(alpha = 0.95f), // 更深的黑色，渐变向透明
                        Color.Black.copy(alpha = 0.90f), // 更深的黑色，渐变向透明
                        Color.Black.copy(alpha = 0.80f), // 更深的黑色，渐变向透明
                        Color.Black.copy(alpha = 0.60f), // 更深的黑色，渐变向透明
                        Color.Black.copy(alpha = 0.30f), // 更深的黑色，渐变向透明
                        Color.Black.copy(alpha = 0.10f), // 更深的黑色，渐变向透明
                        Color.Transparent // 完全透明
                    )
                )
            )
    ) {
        Row(
            modifier = modifier.height(50.dp)
        ) {
            // 左侧菜单按钮
            Row(
                modifier = modifier
                    .weight(w)
                    .padding(start = re.dp)
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onMenu(true) }) {
                    Icon(
                        modifier = modifier.size(28.dp, 28.dp),
                        painter = painterResource(Res.drawable.muen),
                        contentDescription = null,
                        tint = Color(255,240,225)
                    )
                }
            }

            // 主页文字按钮
            Row(
                modifier = modifier
                    .weight(w)
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val isSelected = selectedIndex.value == 0
                Text(
                    text = "主页",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = if (isSelected) Color.Black else Color(254,241,224),
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .clickable {
                            selectedIndex.value = 0
                            onClickHome(true)
                            onClickElse(false)
                        }
                        .background(
                            if (isSelected) Color(255,240,225) else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 3.dp)
                        .animateContentSize()
                )
            }

            // 其他文字按钮
            Row(
                modifier = modifier
                    .weight(w)
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val isSelected = selectedIndex.value == 1
                Text(
                    text = "其他",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = if (isSelected) Color.Black else Color(254,241,224),
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .clickable {
                            selectedIndex.value = 1
                            onClickElse(true)
                            onClickHome(false)
                        }
                        .background(
                            if (isSelected) Color(255,240,225) else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 3.dp)
                        .animateContentSize()
                )
            }

            // 搜索按钮
            Row(
                modifier = modifier
                    .weight(w)
                    .padding(end = re.dp)
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onSearch(true) }) {
                    Icon(
                        modifier = modifier.size(28.dp, 28.dp),
                        painter = painterResource(Res.drawable.serach),
                        contentDescription = null,
                        tint = Color(255,240,225)
                    )
                }
            }
        }

        if (selectedIndex.value == 0) {
            SelectMenu(items = HomeItems)
        }
        if (selectedIndex.value == 1) {
            SelectMenu(items = ElseItems)
        }

    }
}


val HomeItems = listOf(
    SelectMenuItem("探索", "s1"),
    SelectMenuItem("分类", "s2"),
    SelectMenuItem("排行", "s3"),
    SelectMenuItem("今日", "s4"),
)

val ElseItems = listOf(
    SelectMenuItem("综合", "s1"),
    SelectMenuItem("咨询", "s2"),
    SelectMenuItem("练习", "s3"),
    SelectMenuItem("分镜", "s4"),
    SelectMenuItem("教程", "s5"),
    SelectMenuItem("参考", "s6"),
    SelectMenuItem("剪辑", "s7"),
)





















