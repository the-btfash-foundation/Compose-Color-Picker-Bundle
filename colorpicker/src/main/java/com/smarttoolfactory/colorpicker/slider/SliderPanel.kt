package com.smarttoolfactory.colorpicker.slider

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.smarttoolfactory.slider.ColorfulSlider

/**
 * Composable that contains variant number of [ColorfulSlider],
 * and current value from slider depending on **onChange** callbacks are null or not.
 *
 * Based on availability of callbacks order of sliders are placed in order from top to bottom
 * ```
 * Hue Slider
 * Saturation Slider
 * Value Slider
 * Alpha Slider
 * ```
 * @param hue Hue of HSV in [0..360f]
 * @param saturation Hue of HSV in [0..1f]
 * @param value Hue of HSV in [0..1f]
 * @param alpha Hue of HSV in [0..1f]
 * @param onHueChange lambda in which [hue] should be updated. when this lambda is not nul
 * [SliderHueHSV] is added to Column of sliders.
 * @param onSaturationChange lambda in which [saturation] should be updated. when this lambda is not nul
 * [[SliderSaturationHSV] is added to Column of sliders.
 * @param onValueChange lambda in which [value] should be updated. when this lambda is not nul
 * [[SliderValueHSV] is added to Column of sliders.
 * @param onAlphaChange lambda in which [alpha] should be updated. when this lambda is not nul
 * [[SliderAlphaHSV] is added to Column of Sliders.
 */
@Composable
fun SliderPanelHSV(
    modifier: Modifier,
    @FloatRange(from = 0.0, to = 360.0) hue: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0) saturation: Float = .5f,
    @FloatRange(from = 0.0, to = 1.0) value: Float = .5f,
    @FloatRange(from = 0.0, to = 1.0) alpha: Float = 1f,
    onHueChange: ((Float) -> Unit)? = null,
    onSaturationChange: ((Float) -> Unit)? = null,
    onValueChange: ((Float) -> Unit)? = null,
    onAlphaChange: ((Float) -> Unit)? = null
) {
    Column(modifier) {
        onHueChange?.let { onHueChanged ->
            SliderHueHSV(
                modifier = Modifier,
                hue = hue,
                saturation = saturation,
                value = value,
                onValueChange = onHueChanged
            )
        }

        onSaturationChange?.let { onSaturationChanged ->
            SliderSaturationHSV(
                modifier = Modifier,
                hue = hue,
                saturation = saturation,
                value = value,
                onValueChange = onSaturationChanged
            )
        }

        onValueChange?.let { onValueChanged ->
            SliderValueHSV(
                modifier = Modifier,
                hue = hue,
                value = value,
                onValueChange = onValueChanged
            )
        }

        onAlphaChange?.let { onAlphaChanged ->
            SliderAlphaHSV(
                modifier = Modifier,
                hue = hue,
                alpha = alpha,
                onValueChange = onAlphaChanged
            )
        }
    }
}

/**
 * Composable that contains variant number of [ColorfulSlider],
 * and current value from slider depending on **onChange** callbacks are null or not.
 *
 * Based on availability of callbacks order of sliders are placed in order from top to bottom
 * ```
 * Hue Slider
 * Saturation Slider
 * Lightness Slider
 * Alpha Slider
 * ```
 * @param hue Hue of HSL hue in [0..360f]
 * @param saturation Hue of HSL  in [0..1f]
 * @param lightness Hue of HSL in [0..1f]
 * @param alpha Hue of HSL in [0..1f]
 * @param onHueChange lambda in which [hue] should be updated. when this lambda is not nul
 * [SliderHueHSL] is added to Column of sliders.
 * @param onSaturationChange lambda in which [saturation] should be updated. when this lambda is not nul
 * [[SliderSaturationHSL] is added to Column of sliders.
 * @param onLightnessChange lambda in which [lightness] should be updated. when this lambda is not nul
 * [[SliderLightnessHSL] is added to Column of sliders.
 * @param onAlphaChange lambda in which [alpha] should be updated. when this lambda is not nul
 * [SliderAlphaHSL] is added to Column of sliders.
 */
@Composable
fun SliderPanelHSL(
    modifier: Modifier,
    @FloatRange(from = 0.0, to = 360.0) hue: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0) saturation: Float = .5f,
    @FloatRange(from = 0.0, to = 1.0) lightness: Float = .5f,
    @FloatRange(from = 0.0, to = 1.0) alpha: Float = 1f,
    onHueChange: ((Float) -> Unit)? = null,
    onSaturationChange: ((Float) -> Unit)? = null,
    onLightnessChange: ((Float) -> Unit)? = null,
    onAlphaChange: ((Float) -> Unit)? = null
) {
    Column(modifier) {
        onHueChange?.let { onHueChanged ->
            SliderHueHSL(
                modifier = Modifier,
                hue = hue,
                saturation = saturation,
                lightness = lightness,
                onValueChange = onHueChanged
            )
        }

        onSaturationChange?.let { onSaturationChanged ->
            SliderSaturationHSL(
                modifier = Modifier,
                hue = hue,
                saturation = saturation,
                lightness = lightness,
                onValueChange = onSaturationChanged
            )
        }

        onLightnessChange?.let { onValueChanged ->
            SliderLightnessHSL(
                modifier = Modifier,
                lightness = lightness,
                onValueChange = onValueChanged
            )
        }

        onAlphaChange?.let { onAlphaChanged ->
            SliderAlphaHSL(
                modifier = Modifier,
                hue = hue,
                alpha = alpha,
                onValueChange = onAlphaChanged
            )
        }
    }
}
