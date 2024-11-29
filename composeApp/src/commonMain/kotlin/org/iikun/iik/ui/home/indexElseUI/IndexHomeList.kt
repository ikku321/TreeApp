package org.iikun.iik.ui.home.indexElseUI

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.iikun.iik.ui.ModalDrawer.IIkunHR
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import kotlin.random.Random

// 视频列表数据模型
data class IndexHomeListItem(
    val videoID: String, // 视频id
    val title: String, // 视频标题
    val img: DrawableResource, // 视频封面图片
    val size: String, // 视频集数
    val time: String, // 视频时长
    val created: String, // 作者
    val score: String // 评分
)

/**
 * 展示视频列表内容
 *
 * @creator: ii_kun
 * @email: weijikun1@icloud.com
 * @time 2024/10/25 17:34
 */
@Composable
fun VideoCard(
    item: IndexHomeListItem,
    modifier: Modifier = Modifier
) {
    // 动态高度
    val isVertical = (0..1).random() == 0 // 随机选择竖版或横版

    // 动态计算宽度和高度
    val imageWidth = if (isVertical) 216.dp else 384.dp
    val imageHeight = if (isVertical) 384.dp else 216.dp

    // 卡片圆角和图片圆角
    val CardImgRoundedCorners = 14

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .clickable {
                // 点击查看详情
            }
    ) {
        // 卡片显示图片
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = 8.dp,  // 增加阴影的深度，使卡片看起来更立体
            shape = RoundedCornerShape(CardImgRoundedCorners.dp),
            backgroundColor = Color(255, 240, 225) // 设置卡片的背景色
        ) {
            Row(
                modifier = modifier
                    .padding(2.dp)
                    .clip(RoundedCornerShape(CardImgRoundedCorners.dp))
            ) {
                // 动态图片区域
                Image(
                    painter = painterResource(item.img),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .width(imageWidth)
                        .height(imageHeight)
                )
            }
        }

        // 第一行：集数和视频时长
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // 集数
            Text(
                modifier = modifier, // 添加阴影,
                text = "${item.size}集",
                fontSize = 10.sp,
                style = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(255, 163, 68),
                    shadow = Shadow(
                        color = Color.Black,        // 阴影的颜色
                        offset = Offset(3f, 3f),   // 阴影的偏移量
                        blurRadius = 2f             // 阴影的模糊度
                    )
                ),
                color = Color(255, 240, 225)
            )
            // 时长
            Text(
                modifier = modifier, // 添加阴影,
                text = item.time,
                fontSize = 10.sp,
                style = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(255, 163, 68),
                    shadow = Shadow(
                        color = Color.Black,        // 阴影的颜色
                        offset = Offset(3f, 3f),   // 阴影的偏移量
                        blurRadius = 2f             // 阴影的模糊度
                    )
                ),
                color = Color(255, 240, 225)
            )
        }

        // 视频标题
        Text(
            text = item.title,
            fontSize = 16.sp,
            style = MaterialTheme.typography.h6.copy(
                fontWeight = FontWeight.Bold,
                color = Color(255, 163, 68),
                shadow = Shadow(
                    color = Color.Black,        // 阴影的颜色
                    offset = Offset(3f, 3f),   // 阴影的偏移量
                    blurRadius = 2f             // 阴影的模糊度
                )
            ),
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier, // 添加阴影,
            color = Color(255, 240, 225)
        )

        // 第二行：作者和评分
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // 作者
            Text(
                modifier = modifier, // 添加阴影,
                text = "^${item.created}",
                fontSize = 10.sp,
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(255, 163, 68),
                    shadow = Shadow(
                        color = Color.Black,        // 阴影的颜色
                        offset = Offset(3f, 3f),   // 阴影的偏移量
                        blurRadius = 2f             // 阴影的模糊度
                    )
                ),
                color = Color(209, 209, 209)
            )
            // 评分
            Text(
                modifier = modifier,
                text = "${item.score}分",
                fontSize = 14.sp,
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(255, 163, 68),
                    shadow = Shadow(
                        color = Color.Black,        // 阴影的颜色
                        offset = Offset(3f, 3f),   // 阴影的偏移量
                        blurRadius = 2f             // 阴影的模糊度
                    )
                ),
                fontWeight = FontWeight.Bold,
                color = Color(255, 163, 68)
            )
        }
        IIkunHR(heightIIKun = 4)
    }
}