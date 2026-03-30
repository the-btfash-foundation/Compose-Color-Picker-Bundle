package com.smarttoolfactory.colorpicker.picker

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.colorpicker.model.ColorHSL
import com.smarttoolfactory.colorpicker.model.ColorModel
import com.smarttoolfactory.colorpicker.selector.HueSelectorRing
import com.smarttoolfactory.colorpicker.selector.SelectorDiamondSaturationLightnessHSL
import com.smarttoolfactory.colorpicker.selector.SelectorRectSaturationLightnessHSL
import com.smarttoolfactory.colorpicker.selector.SelectorRingProperties
import com.smarttoolfactory.colorpicker.slider.CompositeSliderPanel
import com.smarttoolfactory.colorpicker.widget.ColorModelChangeTabRow
import com.smarttoolfactory.extendedcolors.util.ColorUtil.colorToHSL

@Preview
@Composable
private fun Prev() {
    CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.bodyLarge) {
        ColorPickerRingHSL(
            initialColor = Color.Red,
            colorModel = ColorModel.RGB,
            showAlphaSlider = false,
            isColorModelSelectable = false,
            onColorChange = {}
        )
    }
}

/**
 * ColorPicker with [HueSelectorRing] hue selector and [SelectorRectSaturationLightnessHSL]
 * saturation lightness Selector that uses [HSL](https://en.wikipedia.org/wiki/HSL_and_HSV)
 * color model as base.
 * This color picker has tabs section that can be changed between
 * HSL, HSV and RGB color models and color can be set using [CompositeSliderPanel] which contains
 * sliders for each color models.
 *
 * @param initialColor color that is passed to this picker initially.
 * @param onColorChange callback that is triggered when [Color] is changed using [HueSelectorRing],
 * [SelectorDiamondSaturationLightnessHSL] or [CompositeSliderPanel]
 */
@Composable
fun ColorPickerRingHSL(
    initialColor: Color,
    colorModel: ColorModel,
    modifier: Modifier = Modifier,
    ringProperties: SelectorRingProperties = SelectorRingProperties(),
    saturationLightnessSelectorShape: SaturationLightnessSelectorShape = SaturationLightnessSelectorShape.Rect,
    selectorRadius: Dp = 8.dp,
    showAlphaSlider: Boolean = true,
    sliderPanelModifier: Modifier = Modifier.padding(start = 10.dp, end = 8.dp),
    isColorModelSelectable: Boolean = true,
    onColorChange: (Color) -> Unit
) {
    var inputColorModel by remember(colorModel) { mutableStateOf(colorModel) }

    var color by remember {
        val hslArray = colorToHSL(initialColor)
        mutableStateOf(
            ColorHSL(
                hue = hslArray[0],
                saturation = hslArray[1],
                lightness = hslArray[2],
                alpha = initialColor.alpha
            )
        )
    }

    LaunchedEffect(color) { onColorChange(color.color) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(contentAlignment = Alignment.Center) {
            // Ring Shaped Hue Selector
            HueSelectorRing(
                hue = color.hue,
                properties = ringProperties,
                onChange = { color = color.copy(hue = it) }
            )

            // Saturation Lightness Selector
            AnimatedContent(saturationLightnessSelectorShape) { shape ->
                when (shape) {
                    SaturationLightnessSelectorShape.Rect ->
                        SelectorRectSaturationLightnessHSL(
                            modifier = Modifier
                                .fillMaxWidth(ringProperties.innerRadiusFraction * .65f)
                                .aspectRatio(1f),
                            hue = color.hue,
                            saturation = color.saturation,
                            lightness = color.lightness,
                            selectionRadius = selectorRadius
                        ) { s, l -> color = color.copy(saturation = s, lightness = l) }

                    SaturationLightnessSelectorShape.Diamond ->
                        SelectorDiamondSaturationLightnessHSL(
                            modifier = Modifier.fillMaxWidth(ringProperties.innerRadiusFraction * .9f),
                            hue = color.hue,
                            saturation = color.saturation,
                            lightness = color.lightness,
                            selectionRadius = selectorRadius
                        ) { s, l -> color = color.copy(saturation = s, lightness = l) }
                }
            }
        }

        if (isColorModelSelectable) {
            ColorModelChangeTabRow(
                modifier = Modifier.fillMaxWidth(0.8f),
                colorModel = inputColorModel,
                onColorModelChange = { inputColorModel = it }
            )
        }

        CompositeSliderPanel(
            modifier = sliderPanelModifier,
            compositeColor = color,
            onColorChange = { color = it as ColorHSL },
            showAlphaSlider = showAlphaSlider,
            inputColorModel = inputColorModel,
            outputColorModel = ColorModel.HSL
        )
    }
}
