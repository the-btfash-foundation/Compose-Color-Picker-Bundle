package com.smarttoolfactory.colorpicker.slider

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.colorpicker.model.ColorRGB
import com.smarttoolfactory.colorpicker.model.RGBAChannel
import com.smarttoolfactory.colorpicker.ui.brush.sliderAlphaHSLGradient
import com.smarttoolfactory.colorpicker.ui.brush.sliderAlphaHSVGradient
import com.smarttoolfactory.colorpicker.ui.brush.sliderAlphaRGBGradient
import com.smarttoolfactory.colorpicker.ui.brush.sliderBlueGradient
import com.smarttoolfactory.colorpicker.ui.brush.sliderGreenGradient
import com.smarttoolfactory.colorpicker.ui.brush.sliderHueHSLGradient
import com.smarttoolfactory.colorpicker.ui.brush.sliderHueHSVGradient
import com.smarttoolfactory.colorpicker.ui.brush.sliderLightnessGradient
import com.smarttoolfactory.colorpicker.ui.brush.sliderRedGradient
import com.smarttoolfactory.colorpicker.ui.brush.sliderSaturationHSLGradient
import com.smarttoolfactory.colorpicker.ui.brush.sliderSaturationHSVGradient
import com.smarttoolfactory.colorpicker.ui.brush.sliderValueGradient
import com.smarttoolfactory.colorpicker.widget.drawChecker
import com.smarttoolfactory.extendedcolors.util.roundToTwoDigits
import com.smarttoolfactory.slider.ColorfulSlider
import com.smarttoolfactory.slider.MaterialSliderDefaults
import com.smarttoolfactory.slider.SliderBrushColor

/*
    HSV Sliders
 */

/**
 * [CheckeredColorfulSlider] is a slider to select
 * [hue] in [HSV](https://en.wikipedia.org/wiki/HSL_and_HSV) color model.
 * @param hue in [0..360f]
 * @param saturation in [0..1f]
 * @param value in [0..1f]
 * @param onValueChange callback that returns change in [hue] when Slider is dragged
 */
@Composable
fun SliderHueHSV(
    modifier: Modifier = Modifier,
    @FloatRange(from = 0.0, to = 360.0) hue: Float,
    @FloatRange(from = 0.0, to = 1.0) saturation: Float,
    @FloatRange(from = 0.0, to = 1.0) value: Float,
    onValueChange: (Float) -> Unit
) {
    val sliderHueSelectionHSLGradient =
        sliderHueHSVGradient(
            saturation = saturation,
            value = value
        )

    CheckeredColorfulSlider(
        modifier = modifier,
        value = hue,
        valueRange = 0f..360f,
        onValueChange = onValueChange,
        brush = sliderHueSelectionHSLGradient,
        drawChecker = true
    )
}

/**
 * [CheckeredColorfulSlider] is a slider to select
 * [saturation] in [HSV](https://en.wikipedia.org/wiki/HSL_and_HSV) color model.
 * @param hue in [0..360f]
 * @param saturation in [0..1f]
 * @param value in [0..1f]
 * @param onValueChange callback that returns change in [saturation] when Slider is dragged
 */
@Composable
fun SliderSaturationHSV(
    modifier: Modifier = Modifier,
    @FloatRange(from = 0.0, to = 360.0) hue: Float,
    @FloatRange(from = 0.0, to = 1.0) saturation: Float,
    @FloatRange(from = 0.0, to = 1.0) value: Float,
    onValueChange: (Float) -> Unit
) {
    val sliderHueSelectionHSLGradient =
        sliderSaturationHSVGradient(
            hue,
            value
        )

    CheckeredColorfulSlider(
        modifier = modifier,
        value = saturation,
        onValueChange = onValueChange,
        brush = sliderHueSelectionHSLGradient,
        drawChecker = true
    )
}

/**
 * [CheckeredColorfulSlider] is a slider to select
 * [value] in [HSV](https://en.wikipedia.org/wiki/HSL_and_HSV) color model.
 * @param hue in [0..360f]
 * @param value in [0..1f]
 * @param onValueChange callback that returns change in [value] when Slider is dragged
 */
@Composable
fun SliderValueHSV(
    modifier: Modifier = Modifier,
    @FloatRange(from = 0.0, to = 360.0) hue: Float,
    @FloatRange(from = 0.0, to = 1.0) value: Float,
    onValueChange: (Float) -> Unit
) {
    val sliderValueGradient = sliderValueGradient(hue = hue)

    CheckeredColorfulSlider(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        brush = sliderValueGradient,
        drawChecker = true
    )
}

/**
 * [CheckeredColorfulSlider] that displays [alpha] change in
 * [HSV](https://en.wikipedia.org/wiki/HSL_and_HSV) color model.
 * @param hue in [0..360f]
 * @param alpha in [0..1f]
 */
@Composable
fun SliderAlphaHSV(
    modifier: Modifier = Modifier,
    @FloatRange(from = 0.0, to = 360.0) hue: Float,
    @FloatRange(from = 0.0, to = 1.0) alpha: Float,
    onValueChange: (Float) -> Unit
) {
    val sliderAlphaHSLGradient = sliderAlphaHSVGradient(hue = hue)
    CheckeredColorfulSlider(
        modifier = modifier,
        value = alpha,
        onValueChange = onValueChange,
        brush = sliderAlphaHSLGradient,
        drawChecker = true
    )
}

/*
    HSL Sliders
 */

/**
 * [CheckeredColorfulSlider] is a slider to select
 * [hue] in [HSL](https://en.wikipedia.org/wiki/HSL_and_HSV) color model.
 * @param hue in [0..360f]
 * @param saturation in [0..1f]
 * @param lightness in [0..1f]
 * @param onValueChange callback that returns change in [hue] when Slider is dragged
 */
@Composable
fun SliderHueHSL(
    modifier: Modifier = Modifier,
    @FloatRange(from = 0.0, to = 360.0) hue: Float,
    @FloatRange(from = 0.0, to = 1.0) saturation: Float,
    @FloatRange(from = 0.0, to = 1.0) lightness: Float,
    onValueChange: (Float) -> Unit
) {
    val sliderHueSelectionHSLGradient =
        sliderHueHSLGradient(
            saturation = saturation,
            lightness = lightness
        )

    CheckeredColorfulSlider(
        modifier = modifier,
        value = hue,
        valueRange = 0f..360f,
        onValueChange = onValueChange,
        brush = sliderHueSelectionHSLGradient,
        drawChecker = true
    )
}

/**
 * [CheckeredColorfulSlider] is a slider to select
 * [saturation] in [HSL](https://en.wikipedia.org/wiki/HSL_and_HSV) color model.
 * @param hue in [0..360f]
 * @param saturation in [0..1f]
 * @param lightness in [0..1f]
 * @param onValueChange callback that returns change in [saturation] when Slider is dragged
 */
@Composable
fun SliderSaturationHSL(
    modifier: Modifier = Modifier,
    @FloatRange(from = 0.0, to = 360.0) hue: Float,
    @FloatRange(from = 0.0, to = 1.0) saturation: Float,
    @FloatRange(from = 0.0, to = 1.0) lightness: Float,
    onValueChange: (Float) -> Unit
) {
    val sliderHueSelectionHSLGradient =
        sliderSaturationHSLGradient(
            hue,
            lightness
        )

    CheckeredColorfulSlider(
        modifier = modifier,
        value = saturation,
        onValueChange = onValueChange,
        brush = sliderHueSelectionHSLGradient,
        drawChecker = true
    )
}

/**
 * [CheckeredColorfulSlider] is a slider to select
 * [lightness] in [HSL](https://en.wikipedia.org/wiki/HSL_and_HSV) color model.
 * @param hue in [0..360f]
 * @param lightness in [0..1f]
 * @param saturation in [0..1f]
 * white-black in hsl, others transition from hue with lightness.
 * @param onValueChange callback that returns change in [lightness] when Slider is dragged
 */
@Composable
fun SliderLightnessHSL(
    modifier: Modifier = Modifier,
    @FloatRange(from = 0.0, to = 360.0) hue: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0) saturation: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0) lightness: Float = 0f,
    onValueChange: (Float) -> Unit
) {
    val sliderLightnessGradient = sliderLightnessGradient(hue, saturation)
    CheckeredColorfulSlider(
        modifier = modifier,
        value = lightness,
        onValueChange = onValueChange,
        brush = sliderLightnessGradient,
        drawChecker = true
    )
}

/**
 * [CheckeredColorfulSlider] is a slider to select
 * [alpha] in [HSL](https://en.wikipedia.org/wiki/HSL_and_HSV) color model.
 * @param hue in [0..360f]
 * @param alpha in [0..1f]
 * @param onValueChange callback that returns change in [alpha] when Slider is dragged
 */
@Composable
fun SliderAlphaHSL(
    @FloatRange(from = 0.0, to = 360.0) hue: Float,
    @FloatRange(from = 0.0, to = 1.0) alpha: Float,
    modifier: Modifier = Modifier,
    onValueChange: (Float) -> Unit
) {
    val sliderAlphaHSLGradient = sliderAlphaHSLGradient(hue = hue)
    CheckeredColorfulSlider(
        modifier = modifier,
        value = alpha,
        onValueChange = onValueChange,
        brush = sliderAlphaHSLGradient,
        drawChecker = true
    )
}

/*
    RGB Sliders
 */

/**
 * [CheckeredColorfulSlider] that displays change in
 * [RGB](https://en.wikipedia.org/wiki/RGB_color_model) color model.
 */
@Composable
internal fun SliderRGBA(
    color: ColorRGB,
    channel: RGBAChannel,
    modifier: Modifier = Modifier,
    onChange: (ColorRGB) -> Unit
) {
    CheckeredColorfulSlider(
        modifier = modifier,
        value = color[channel],
        onValueChange = { value ->
            when (channel) {
                RGBAChannel.Red -> onChange(color.copy(red = value))
                RGBAChannel.Green -> onChange(color.copy(green = value))
                RGBAChannel.Blue -> onChange(color.copy(blue = value))
                RGBAChannel.Alpha -> onChange(color.copy(alpha = value))
            }
        },
        brush = when (channel) {
            RGBAChannel.Red -> sliderRedGradient()
            RGBAChannel.Green -> sliderGreenGradient()
            RGBAChannel.Blue -> sliderBlueGradient()
            RGBAChannel.Alpha -> sliderAlphaRGBGradient(color)
        },
        drawChecker = channel == RGBAChannel.Alpha
    )
}

internal fun onColorChangeCallback(onChange: (Color) -> Unit): (ColorRGB) -> Unit {
    return {
        onChange(it.color)
    }
}

@Composable
fun SliderRedRGBA(
    modifier: Modifier = Modifier,
    color: Color,
    onColorChange: (Color) -> Unit,
) {
    SliderRGBA(ColorRGB(color.red, color.green, color.blue, color.alpha), RGBAChannel.Red, modifier, onColorChangeCallback(onColorChange))
}

@Composable
fun SliderGreenRGBA(
    modifier: Modifier = Modifier,
    color: Color,
    onColorChange: (Color) -> Unit,
) {
    SliderRGBA(ColorRGB(color.red, color.green, color.blue, color.alpha), RGBAChannel.Green, modifier, onColorChangeCallback(onColorChange))
}

@Composable
fun SliderBlueRGBA(
    modifier: Modifier = Modifier,
    color: Color,
    onColorChange: (Color) -> Unit,
) {
    SliderRGBA(ColorRGB(color.red, color.green, color.blue, color.alpha), RGBAChannel.Blue, modifier, onColorChangeCallback(onColorChange))
}

@Composable
fun SliderAlphaRGBA(
    modifier: Modifier = Modifier,
    color: Color,
    onColorChange: (Color) -> Unit,
) {
    SliderRGBA(ColorRGB(color.red, color.green, color.blue, color.alpha), RGBAChannel.Alpha, modifier, onColorChangeCallback(onColorChange))
}


@Preview
@Composable
private fun RGBASliderPrev() {
    var color by remember {
        mutableStateOf(ColorRGB())
    }
    SliderRGBA(color = color, channel = RGBAChannel.Green) {
        color = it
    }
}

/**
 * Slider implementation that uses [ColorfulSlider] to create **Slider** with gradient
 * colors with custom thumb radius and track height, and draws checker pattern behind the
 * track of [ColorfulSlider] if [drawChecker] is set to true
 * @param value that is read by [ColorfulSlider]
 * @param valueRange is the values are selected from
 * @param onValueChange is triggered when slider is dragged returns float between [valueRange]
 * @param brush is used for drawing gradient for track of [ColorfulSlider]
 * @param drawChecker when set to true draws checker behind track of [ColorfulSlider] to
 * display alpha
 */
@Composable
private fun CheckeredColorfulSlider(
    value: Float,
    brush: Brush,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    drawChecker: Boolean = false,
    onValueChange: (Float) -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        if (drawChecker) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(11.dp)
                    .drawChecker(shape = RoundedCornerShape(6.dp))
            )
        }

        ColorfulSlider(
            value = value,
            modifier = Modifier,
            thumbRadius = 12.dp,
            trackHeight = 12.dp,
            onValueChange = { value -> onValueChange(value.roundToTwoDigits()) },
            valueRange = valueRange,
            coerceThumbInTrack = true,
            colors = MaterialSliderDefaults.materialColors(
                activeTrackColor = SliderBrushColor(brush = brush),
                inactiveTrackColor = SliderBrushColor(color = Color.Transparent)
            ),
            drawInactiveTrack = false
        )
    }
}
