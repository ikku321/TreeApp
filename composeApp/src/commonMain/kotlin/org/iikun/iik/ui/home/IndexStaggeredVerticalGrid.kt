package org.iikun.iik.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * 自定义爆破流
 *
 * @creator: ii_kun
 * @email: weijikun1@icloud.com
 * @time 2024/10/25 17:34
 */
@Composable
fun StaggeredVerticalGrid(
    modifier: Modifier = Modifier,
    columns: Int,
    spacing: Dp = 8.dp,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        val columnWidth = (constraints.maxWidth - (columns - 1) * spacing.toPx().toInt()) / columns
        val columnHeights = IntArray(columns) { 0 }

        val placeables = measurables.map { measurable ->
            val columnIndex = columnHeights.indexOfMin()
            val placeable = measurable.measure(
                constraints.copy(
                    maxWidth = columnWidth
                )
            )
            columnHeights[columnIndex] += placeable.height + spacing.toPx().toInt()
            placeable to columnIndex
        }

        val totalHeight = columnHeights.maxOrNull()?.coerceAtLeast(constraints.minHeight) ?: constraints.minHeight

        layout(constraints.maxWidth, totalHeight) {
            val yOffsets = IntArray(columns) { 0 }

            placeables.forEach { (placeable, columnIndex) ->
                val x = columnIndex * (columnWidth + spacing.toPx().toInt())
                val y = yOffsets[columnIndex]
                placeable.placeRelative(x, y)
                yOffsets[columnIndex] += placeable.height + spacing.toPx().toInt()
            }
        }
    }
}

fun IntArray.indexOfMin(): Int = this.indices.minByOrNull { this[it] } ?: 0