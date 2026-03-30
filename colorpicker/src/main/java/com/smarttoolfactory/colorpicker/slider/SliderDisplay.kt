package com.smarttoolfactory.colorpicker.slider

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.model.ColorRGB
import com.smarttoolfactory.colorpicker.model.RGBAChannel
import com.smarttoolfactory.colorpicker.model.RGBAChannel.Blue
import com.smarttoolfactory.colorpicker.model.RGBAChannel.Green
import com.smarttoolfactory.colorpicker.model.RGBAChannel.Red
import com.smarttoolfactory.extendedcolors.util.fractionToPercent
import com.smarttoolfactory.extendedcolors.util.fractionToRGBString

/*
    HSV Slider Displays
 */

/**
 * Composable that shows a title as initial letter, title color and a Slider to select
 * [hue] in [HSV](https://en.wikipedia.org/wiki/HSL_and_HSV) color model.
 * @param hue in [0..360f]
 * @param saturation in [0..1f]
 * @param value in [0..1f]
 * @param onValueChange callback that returns change in [hue] when Slider is dragged
 */
@Composable
fun SliderDisplayHueHSV(
    modifier: Modifier,
    @FloatRange(from = 0.0, to = 360.0) hue: Float,
    @FloatRange(from = 0.0, to = 1.0) saturation: Float,
    @FloatRange(from = 0.0, to = 1.0) value: Float,
    onValueChange: (Float) -> Unit
) {
    TitledSliderDisplay(
        modifier = modifier,
        leadingLabel = "Hue",
        trailingLabel = "${hue.toInt()}°"
    ) {
        SliderHueHSL(
            hue = hue,
            saturation = saturation,
            lightness = value,
            onValueChange = onValueChange
        )
    }
}

/**
 * Composable that shows a title as initial letter, title color and a Slider to select
 * [saturation] in [HSV](https://en.wikipedia.org/wiki/HSL_and_HSV) color model.
 * @param hue in [0..360f]
 * @param saturation in [0..1f]
 * @param value in [0..1f]
 * @param onValueChange callback that returns change in [saturation] when Slider is dragged
 */
@Composable
fun SliderDisplaySaturationHSV(
    modifier: Modifier,
    @FloatRange(from = 0.0, to = 360.0) hue: Float,
    @FloatRange(from = 0.0, to = 1.0) saturation: Float,
    @FloatRange(from = 0.0, to = 1.0) value: Float,
    onValueChange: (Float) -> Unit
) {
    TitledSliderDisplay(
        modifier = modifier,
        leadingLabel = "Saturation",
        trailingLabel = "${saturation.fractionToPercent()}"
    ) {
        SliderSaturationHSV(
            hue = hue,
            saturation = saturation,
            value = value,
            onValueChange = onValueChange
        )
    }
}

/**
 * Composable that shows a title as initial letter, title color and a Slider to select
 * [value] in [HSV](https://en.wikipedia.org/wiki/HSL_and_HSV) color model.
 * @param hue in [0..360f]
 * @param value in [0..1f]
 * @param onValueChange callback that returns change in [value] when Slider is dragged
 */
@Composable
fun SliderDisplayValueHSV(
    modifier: Modifier,
    @FloatRange(from = 0.0, to = 360.0) hue: Float,
    @FloatRange(from = 0.0, to = 1.0) value: Float,
    onValueChange: (Float) -> Unit
) {
    TitledSliderDisplay(
        modifier = modifier,
        leadingLabel = "Value",
        trailingLabel = "${value.fractionToPercent()}"
    ) {
        SliderValueHSV(
            hue = hue,
            value = value,
            onValueChange = onValueChange
        )
    }
}

/**
 * Composable that shows a title as initial letter, title color and a Slider to select
 * [alpha] in [HSV](https://en.wikipedia.org/wiki/HSL_and_HSV) color model.
 * @param hue in [0..360f]
 * @param alpha in [0..1f]
 * @param onValueChange callback that returns change in [alpha] when Slider is dragged
 */
@Composable
fun SliderDisplayAlphaHSV(
    modifier: Modifier,
    @FloatRange(from = 0.0, to = 360.0) hue: Float,
    @FloatRange(from = 0.0, to = 1.0) alpha: Float,
    onValueChange: (Float) -> Unit
) {
    TitledSliderDisplay(
        modifier = modifier,
        leadingLabel = "Alpha",
        trailingLabel = "${alpha.fractionToPercent()}"
    ) {
        SliderAlphaHSV(
            hue = hue,
            alpha = alpha,
            onValueChange = onValueChange
        )
    }
}

/*
    HSL Slider Displays
 */

/**
 * Composable that shows a title as initial letter, title color and a Slider to select
 * [hue] in [HSL](https://en.wikipedia.org/wiki/HSL_and_HSV) color model.
 * @param hue in [0..360f]
 * @param saturation in [0..1f]
 * @param lightness in [0..1f]
 * @param onValueChange callback that returns change in [hue] when Slider is dragged
 */
@Composable
fun SliderDisplayHueHSL(
    modifier: Modifier,
    @FloatRange(from = 0.0, to = 360.0) hue: Float,
    @FloatRange(from = 0.0, to = 1.0) saturation: Float,
    @FloatRange(from = 0.0, to = 1.0) lightness: Float,
    onValueChange: (Float) -> Unit
) {
    TitledSliderDisplay(
        modifier = modifier,
        leadingLabel = "Hue",
        trailingLabel = "${hue.toInt()}°"
    ) {
        SliderHueHSL(
            hue = hue,
            saturation = saturation,
            lightness = lightness,
            onValueChange = onValueChange
        )
    }
}

/**
 * Composable that shows a title as initial letter, title color and a Slider to select
 * [saturation] in [HSL](https://en.wikipedia.org/wiki/HSL_and_HSV) color model.
 * @param hue in [0..360f]
 * @param saturation in [0..1f]
 * @param lightness in [0..1f]
 * @param onValueChange callback that returns change in [saturation] when Slider is dragged
 */
@Composable
fun SliderDisplaySaturationHSL(
    modifier: Modifier,
    @FloatRange(from = 0.0, to = 360.0) hue: Float,
    @FloatRange(from = 0.0, to = 1.0) saturation: Float,
    @FloatRange(from = 0.0, to = 1.0) lightness: Float,
    onValueChange: (Float) -> Unit
) {
    TitledSliderDisplay(
        modifier = modifier,
        leadingLabel = "Saturation",
        trailingLabel = "${saturation.fractionToPercent()}"
    ) {
        SliderSaturationHSL(
            hue = hue,
            saturation = saturation,
            lightness = lightness,
            onValueChange = { value ->
                onValueChange(value)
            }
        )
    }
}

/**
 * Composable that shows a title as initial letter, title color and a Slider to select
 * [lightness] in [HSL](https://en.wikipedia.org/wiki/HSL_and_HSV) color model.
 * @param lightness in [0..1f]
 * @param onValueChange callback that returns change in [lightness] when Slider is dragged
 */
@Composable
fun SliderDisplayLightnessHSL(
    modifier: Modifier,
    @FloatRange(from = 0.0, to = 1.0) lightness: Float,
    onValueChange: (Float) -> Unit
) {
    TitledSliderDisplay(
        modifier = modifier,
        leadingLabel = "Lightness",
        trailingLabel = "${lightness.fractionToPercent()}"
    ) {
        SliderLightnessHSL(
            lightness = lightness,
            onValueChange = onValueChange
        )
    }
}

/**
 * Composable that shows a title as initial letter, title color and a Slider to select
 * [alpha] in [HSL](https://en.wikipedia.org/wiki/HSL_and_HSV) color model.
 * @param hue in [0..360f]
 * @param onValueChange callback that returns change in [alpha] when Slider is dragged
 */
@Composable
fun SliderDisplayAlphaHSL(
    modifier: Modifier,
    @FloatRange(from = 0.0, to = 360.0) hue: Float,
    @FloatRange(from = 0.0, to = 1.0) alpha: Float,
    onValueChange: (Float) -> Unit
) {
    TitledSliderDisplay(
        modifier = modifier,
        leadingLabel = "Alpha",
        trailingLabel = "${alpha.fractionToPercent()}"
    ) {
        SliderAlphaHSV(
            hue = hue,
            alpha = alpha,
            onValueChange = onValueChange
        )
    }
}

/*
    RGB Slider Displays
 */

/**
 * Composable that shows a title as initial letter, title color and a Slider to select
 * channel value in [RGB](https://en.wikipedia.org/wiki/RGB_color_model) color model.
 */
@Composable
internal fun TitledSliderRGBA(
    color: ColorRGB,
    channel: RGBAChannel,
    modifier: Modifier = Modifier,
    onChange: (ColorRGB) -> Unit
) {
    TitledSliderDisplay(
        modifier = modifier,
        leadingLabelColor = when (channel) {
            Red -> Color.Red
            Green -> Color.Green
            Blue -> Color.Blue
            Alpha -> LocalContentColor.current
        },
        leadingLabel = channel.name.first().toString(),
        trailingLabel = color[channel].fractionToRGBString()
    ) {
        SliderRGBA(color, channel, onChange = onChange)
    }
}

/**
 * Composable that shows a title as initial letter, title color and a Slider to pick color.
 * @param leadingLabel Title is positioned left side of the slider and presented with only initial letter
 * @param trailingLabel shows the value retrieved from [slider] value change.
 * @param slider is Composable that uses [CheckeredColorfulSlider]
 */
@Composable
private fun TitledSliderDisplay(
    leadingLabel: String,
    trailingLabel: String,
    modifier: Modifier,
    leadingLabelColor: Color = LocalContentColor.current,
    slider: @Composable () -> Unit
) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = leadingLabel,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = leadingLabelColor
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            slider()
        }
        Text(
            text = trailingLabel,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(30.dp)
        )
    }
}
