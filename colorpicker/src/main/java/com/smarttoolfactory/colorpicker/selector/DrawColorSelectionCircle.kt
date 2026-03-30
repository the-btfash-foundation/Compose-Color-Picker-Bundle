package com.smarttoolfactory.colorpicker.selector

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlin.math.min

/**
 * Draw selection circle with white and black colors
 * Stroke draws half in and half out of the current radius.
 * With 200 radius 20 stroke width starts from 190 and ends at 210.
 * @param center center of the selection circle
 * @param radius radius of the selection circle
 */
internal fun DrawScope.drawHueSelectionCircle(center: Offset, radius: Float) {
    drawCircle(
        Color.White,
        radius = radius,
        center = center,
        style = Stroke(width = radius / 4)
    )

    drawCircle(
        Color.Black,
        radius = radius + radius / 8,
        center = center,
        style = Stroke(width = radius / 8)
    )
}

internal fun DrawScope.drawHueSelectionIndicator(
    center: Offset,
    width: Float,
    height: Float
) {
    val innerStrokeWidth = min(width, height) / 4f
    val outerStrokeWidth = innerStrokeWidth / 2f

    drawPillStroke(
        center = center,
        width = width,
        height = height,
        color = Color.White,
        strokeWidth = innerStrokeWidth
    )
    drawPillStroke(
        center = center,
        width = width + outerStrokeWidth * 2f,
        height = height + outerStrokeWidth * 2f,
        color = Color.Black,
        strokeWidth = outerStrokeWidth
    )
}

private fun DrawScope.drawPillStroke(
    center: Offset,
    width: Float,
    height: Float,
    color: Color,
    strokeWidth: Float
) {
    val topLeft = Offset(
        x = center.x - width / 2f,
        y = center.y - height / 2f
    )
    val cornerRadius = min(width, height) / 2f

    drawRoundRect(
        color = color,
        topLeft = topLeft,
        size = Size(width, height),
        cornerRadius = CornerRadius(cornerRadius, cornerRadius),
        style = Stroke(width = strokeWidth)
    )
}
