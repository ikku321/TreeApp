package org.iikun.iik.ui.ModalDrawer

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import tree_app.composeapp.generated.resources.Res
import tree_app.composeapp.generated.resources.dingdan
import tree_app.composeapp.generated.resources.dingdan2
import tree_app.composeapp.generated.resources.fulitequan
import tree_app.composeapp.generated.resources.hepl
import tree_app.composeapp.generated.resources.huiz
import tree_app.composeapp.generated.resources.ikai
import tree_app.composeapp.generated.resources.llls
import tree_app.composeapp.generated.resources.pal
import tree_app.composeapp.generated.resources.settings
import tree_app.composeapp.generated.resources.sys

val a = UserInfoModel(
    headPortrait = Res.drawable.ikai,
    name = "树洞开发人员",
    badge = "十八枚 >",
    account = "648.00",
    age = "100天",
    grade = "三段",
    experience = "400经验",
    concern = "999",
    AfanOfSomeone = "999",
    collection = "999",
)

val drawerList = listOf(
    DrawerMenuItem(icon = Res.drawable.dingdan2, title = "订单", cls = false),
    DrawerMenuItem(icon = Res.drawable.llls, title = "浏览历史", cls = false),
    DrawerMenuItem(icon = Res.drawable.fulitequan, title = "我的福利特权", cls = true),

    DrawerMenuItem(title = "创作者中心", cls = false),
    DrawerMenuItem(title = "装扮中心", cls = false),
    DrawerMenuItem(title = "活动中心", cls = true),

    DrawerMenuItem(title = "分享树里动画", cls = false),
    DrawerMenuItem(title = "站长想说的话", cls = false),
    DrawerMenuItem(title = "树里公约", cls = false),
    DrawerMenuItem(title = "倾述中心", cls = false),
    DrawerMenuItem(title = "关于树里", cls = true),

    DrawerMenuItem(title = "切换账号", cls = false),
    DrawerMenuItem(title = "退出登录/关闭", cls = false)
)

// 菜单栏数据模型
data class DrawerMenuItem(
    val icon: DrawableResource? = null,
    val title: String,
    val cls: Boolean // 分类
)

/**
 * // 抽屉内容
 *
 * @creator: ii_kun
 * @email: weijikun1@icloud.com
 * @time 2024/10/25 17:34
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DrawerContent(
    onClose: () -> Unit
) {
    FlowColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(94, 133, 104))
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        // Top1
        TopOne()
        // 分界线
        IIkunHR(heightIIKun = 10)
        // 用户信息卡片
        UserInfoCard(items = a)
        // 菜单列表
        DrawerMenuList(items = drawerList)
    }
}


/**
 * 菜单栏列表
 */
@Composable
fun DrawerMenuList(
    modifier: Modifier = Modifier,
    items: List<DrawerMenuItem>
) {
    Column {
        items.forEach { item ->
            Row(
                modifier = modifier
                    .padding(start = 20.dp, top = 5.dp, end = 5.dp)
                    .clickable {  }
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (item.icon != null) {
                    Image(
                        modifier = modifier
                            .size(30.dp, 30.dp),
                        painter = painterResource(item.icon),
                        contentDescription = null
                    )
                }
                // 文字
                Text(
                    text = item.title,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color(255,240,225)
                )
            }
            // 是否显示分界线
            if (item.cls) {
                IIkunHR(heightIIKun = 10)
            }
        }
    }
}

/**
 * TODO 粉丝 点赞 收藏
 */
@Composable
fun FansLikeCollection(
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.padding(5.dp)) {
        Row(
            modifier = modifier.weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "79",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "关注",
                    fontSize = 14.sp
                )
            }
        }


        // 分界线
        Spacer(
            modifier = modifier
                .height(25.dp)
                .width(3.dp)
                .background(Color(173, 173, 173))
                .align(Alignment.CenterVertically)
        )

        Row(
            modifier = modifier.weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "35",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "粉丝",
                    fontSize = 14.sp
                )
            }
        }


        // 分界线
        Spacer(
            modifier = modifier
                .height(25.dp)
                .width(3.dp)
                .background(Color(173, 173, 173))
                .align(Alignment.CenterVertically)
        )

        Row(
            modifier = modifier.weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "426",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "收藏",
                    fontSize = 14.sp
                )
            }
        }

    }
}


/**
 * TODO 等级和经验条
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GradeExperience(
    modifier: Modifier = Modifier
) {
    var progress by remember { mutableStateOf(0.4f) }
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )

    FlowColumn(
        modifier = modifier
            .padding(start = 10.dp, end = 10.dp)
    ) {
        // 文字内容指示
        Row {
            Text(
                modifier = modifier
                    .weight(1f),
                text = "筑基期: 三段",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Start
            )
            Text(
                modifier = modifier
                    .weight(1f),
                text = "400经验/1200经验",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.End
            )
        }
        // 进度条指示
        LinearProgressIndicator(
            modifier = modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color(119, 82, 54)),
            progress = animatedProgress,
            color = Color(215, 108, 71)
        )
    }
}


/**
 * TODO 用户信息卡片
 */
@Composable
fun UserInfoCard(
    modifier: Modifier = Modifier,
    items: UserInfoModel
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(Int.MAX_VALUE.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 10.dp,
        backgroundColor = Color(255, 240, 223)
    ) {
        Column {

            // TODO 第一个ui内容， 头像， 名称， 徽章， 账号余额， 树龄， 个人主页
            Row(
                modifier = modifier.padding(5.dp)
            ) {
                Card(
                    modifier = modifier
                        .clip(RoundedCornerShape(100.dp)),
                    elevation = 10.dp
                ) {
                    // 头像
                    Image(
                        modifier = modifier
                            .size(60.dp, 60.dp),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(items.headPortrait),
                        contentDescription = null
                    )
                }


                Row(
                    modifier = modifier
                        .padding(start = 10.dp, end = 10.dp)
                ) {
                    // 名称和徽章
                    Column(
                        modifier = modifier.weight(2f),
                        horizontalAlignment = Alignment.Start
                    ) {
                        // 名称
                        Text(
                            text = items.name,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier.height(5.dp))
                        // 徽章
                        Row {
                            // 徽章
                            Card(
                                modifier
                                    .clip(RoundedCornerShape(10.dp)),
                                backgroundColor = Color(215, 108, 71)
                            ) {
                                Row(
                                    modifier = modifier
                                        .padding(2.dp),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    // 徽章图标
                                    Image(
                                        modifier = modifier
                                            .size(15.dp, 15.dp),
                                        painter = painterResource(Res.drawable.huiz),
                                        contentDescription = null
                                    )
                                    // 徽章等级
                                    Text(
                                        text = items.badge,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                            // 账号余额和树龄
                            Column(
                                modifier = modifier
                                    .padding(start = 5.dp)
                            ) {
                                Text(
                                    text = "账号余额: ${items.account}",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )

                                Text(
                                    text = "树龄: ${items.age}",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            }
                        }

                    }

                    // 个人主页
                    Column(
                        modifier = modifier
                            .weight(1f),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = "个人主页 >",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
            }

            // TODO 等级和经验条
            GradeExperience()

            // TODO 粉丝 点赞 收藏
            FansLikeCollection()
        }
    }
}


/**
 * TODO 分界线
 */
@Composable
fun IIkunHR(
    modifier: Modifier = Modifier,
    heightIIKun: Int
) {
    Spacer(modifier = modifier.height(heightIIKun.dp))
    Row(
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 5.dp)
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(2.dp),
            elevation = 10.dp,
            backgroundColor = Color(174, 201, 181)
        ) { }
    }
    Spacer(modifier = modifier.height(heightIIKun.dp))
}


/**
 * TODO  侧边栏顶部显示主要内容
 * 扫一扫
 * 帮助与客服
 * 设置
 */
@Composable
fun TopOne(
    modifier: Modifier = Modifier
) {
    val items = listOf(
        TopOneItem("扫一扫", Res.drawable.sys),
        TopOneItem("客服与帮助", Res.drawable.hepl),
        TopOneItem("设置", Res.drawable.settings),
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        items.forEach { item ->
            Row(
                modifier = modifier
                    .weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = modifier
                            .size(30.dp),
                        painter = painterResource(item.icon),
                        contentDescription = null
                    )
                    Text(
                        text = item.title,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(255, 240, 223)
                    )
                }
            }
        }
    }
}

























