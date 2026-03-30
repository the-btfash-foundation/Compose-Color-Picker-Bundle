package com.smarttoolfactory.colorpicker.selector

import androidx.annotation.FloatRange
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.smarttoolfactory.colorpicker.ui.gradientColorScaleHSVReversed
import com.smarttoolfactory.colorpicker.util.calculateAngleFromLocalCoordinates
import com.smarttoolfactory.colorpicker.util.calculateDistanceFromCenter
import com.smarttoolfactory.gesture.detectMotionEvents

@Preview
@Composable
private fun Prev() {
    Box { HueSelectorRing(hue = 270f, onChange = {}) }
}

/**
 * @param outerRadiusFraction radius ratio of outer radius of ring in percent
 * @param innerRadiusFraction radius ratio of inner radius of ring in percent
 * @param borderStrokeColor color for drawing border outer and inner positions of ring
 * @param borderStrokeWidth width of stroke for drawing border inner and outer positions of ring
 * @param selectorThickness thickness of selection indicator that moves based on touch position
 */
data class SelectorRingProperties(
    val outerRadiusFraction: Float = 1f,
    val innerRadiusFraction: Float = 0.75f,
    val borderStrokeColor: Color = Color.Black,
    val borderStrokeWidth: Dp = 1.dp,
    val selectorThickness: Dp = 16.dp
)

@Composable
fun HueSelectorRing(
    @FloatRange(from = 0.0, to = 360.0) hue: Float,
    modifier: Modifier = Modifier,
    properties: SelectorRingProperties = SelectorRingProperties(),
    onChange: (Float) -> Unit
) {
    val currentOnChange by rememberUpdatedState(onChange)
    val density = LocalDensity.current
    val coercedHue = hue.coerceIn(0f, 360f)

    Canvas(
        modifier = modifier
            .aspectRatio(1f)
            .hueSelectorInput(properties, density, currentOnChange)
    ) {
        val geometry = properties.toGeometry(size, density)

        drawCircle(
            brush = Brush.sweepGradient(
                colors = gradientColorScaleHSVReversed,
                center = geometry.center
            ),
            radius = geometry.ringRadius,
            style = Stroke(width = geometry.ringThickness)
        )

        drawCircle(
            color = properties.borderStrokeColor,
            radius = geometry.innerRadiusPx - geometry.borderStrokeWidthPx / 2f,
            style = Stroke(width = geometry.borderStrokeWidthPx)
        )
        drawCircle(
            color = properties.borderStrokeColor,
            radius = geometry.outerRadiusPx + geometry.borderStrokeWidthPx / 2f,
            style = Stroke(width = geometry.borderStrokeWidthPx)
        )

        withTransform({
            rotate(degrees = -coercedHue)
        }) {
            drawHueSelectionIndicator(
                center = Offset(geometry.center.x + geometry.ringRadius, geometry.center.y),
                width = geometry.ringThickness,
                height = geometry.selectorThicknessPx
            )
        }
    }
}

private fun Modifier.hueSelectorInput(
    properties: SelectorRingProperties,
    density: Density,
    onChange: (Float) -> Unit
): Modifier =
    pointerInput(properties, density, onChange) {
        var isTouched = false

        fun updateHue(position: Offset) {
            val geometry = properties.toGeometry(size.toSize(), density)
            if (position in geometry) {
                onChange(calculateAngleFromLocalCoordinates(geometry.center, position))
            }
        }

        detectMotionEvents(
            onDown = {
                val geometry = properties.toGeometry(size.toSize(), density)
                isTouched = it.position in geometry
                if (isTouched) {
                    onChange(calculateAngleFromLocalCoordinates(geometry.center, it.position))
                    it.consume()
                }
            },
            onMove = {
                if (isTouched) {
                    updateHue(it.position)
                    it.consume()
                }
            },
            onUp = {
                if (isTouched) it.consume()
                isTouched = false
            },
            delayAfterDownInMillis = 20
        )
    }

private data class SelectorRingGeometry(
    val center: Offset,
    val innerRadiusPx: Float,
    val outerRadiusPx: Float,
    val ringThickness: Float,
    val ringRadius: Float,
    val borderStrokeWidthPx: Float,
    val selectorThicknessPx: Float
) {
    operator fun contains(position: Offset): Boolean =
        calculateDistanceFromCenter(center, position) in innerRadiusPx..outerRadiusPx
}

private fun SelectorRingProperties.toGeometry(size: Size, density: Density): SelectorRingGeometry {
    val radiusPx = size.minDimension / 2f
    val center = Offset(size.width / 2f, size.height / 2f)

    val innerRadiusPx =
        (radiusPx * innerRadiusFraction.coerceIn(0f, 1f)).coerceAtLeast(0f)
    val outerRadiusPx =
        (radiusPx * outerRadiusFraction.coerceIn(0f, 1f)).coerceAtLeast(innerRadiusPx)

    val ringThickness = outerRadiusPx - innerRadiusPx
    val ringRadius = innerRadiusPx + ringThickness / 2f

    val borderStrokeWidthPx = with(density) {
        borderStrokeWidth.toPx().coerceAtMost(innerRadiusPx * 0.2f)
    }

    val selectorRadiusPx = with(density) { selectorThickness.toPx() }

    return SelectorRingGeometry(
        center = center,
        innerRadiusPx = innerRadiusPx,
        outerRadiusPx = outerRadiusPx,
        ringThickness = ringThickness,
        ringRadius = ringRadius,
        borderStrokeWidthPx = borderStrokeWidthPx,
        selectorThicknessPx = selectorRadiusPx
    )
}
