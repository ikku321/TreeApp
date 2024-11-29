package org.iikun.iik.ui.home.indexElseUI

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * 自定义瀑布流
 *
 * @creator: ii_kun
 * @email: weijikun1@icloud.com
 * @time 2024/10/25 17:34
 */
@Composable
fun StaggeredVerticalGrid(
    // 可选的修饰符，用于自定义布局属性
    modifier: Modifier = Modifier,
    // 网格的列数
    columns: Int,
    // 列之间的间距，默认为 8.dp
    spacing: Dp = 8.dp,
    // 网格内容的UI
    content: @Composable () -> Unit
) {
    Layout(
        content = content, // 内容的组合项
        modifier = modifier // 应用到网格的修饰符
    ) { measurables, constraints ->
        // 每列的宽度，减去间距后平均分配给每列
        val columnWidth = (constraints.maxWidth - (columns - 1) * spacing.toPx().toInt()) / columns
        // 用于记录每列当前累积的高度
        val columnHeights = IntArray(columns) { 0 }

        // 测量所有的子组件，决定它们的列索引，并计算每列的高度
        val placeables = measurables.map { measurable ->
            // 找到当前高度最小的列，优先放置内容
            val columnIndex = columnHeights.indexOfMin()
            // 测量子组件，宽度受列宽限制
            val placeable = measurable.measure(
                constraints.copy(
                    maxWidth = columnWidth // 限制子组件的最大宽度为列宽
                )
            )
            // 更新对应列的累计高度，加上子组件高度和间距
            columnHeights[columnIndex] += placeable.height + spacing.toPx().toInt()
            // 将测量后的组件与其所在列索引保存
            placeable to columnIndex
        }

        // 计算网格布局的总高度，取各列累计高度的最大值，与最小高度限制比较后取更大值
        val totalHeight = columnHeights.maxOrNull()?.coerceAtLeast(constraints.minHeight) ?: constraints.minHeight

        layout(constraints.maxWidth, totalHeight) {
            // 每列当前的 Y 偏移量，用于确定子组件的垂直位置
            val yOffsets = IntArray(columns) { 0 }

            // 将子组件放置到对应的位置
            placeables.forEach { (placeable, columnIndex) ->
                // 计算子组件的 X 坐标（列索引 * 列宽 + 间距）
                val x = columnIndex * (columnWidth + spacing.toPx().toInt())
                // 获取子组件在当前列中的 Y 坐标
                val y = yOffsets[columnIndex]
                // 放置子组件
                placeable.placeRelative(x, y)
                // 更新当前列的 Y 偏移量
                yOffsets[columnIndex] += placeable.height + spacing.toPx().toInt()
            }
        }
    }
}

// 扩展函数，用于找到 IntArray 中最小值的索引
fun IntArray.indexOfMin(): Int = this.indices.minByOrNull { this[it] } ?: 0