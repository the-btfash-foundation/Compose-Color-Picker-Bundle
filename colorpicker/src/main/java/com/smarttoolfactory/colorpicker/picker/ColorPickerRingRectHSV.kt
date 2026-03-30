package com.smarttoolfactory.colorpicker.picker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.colorpicker.model.ColorHSV
import com.smarttoolfactory.colorpicker.model.ColorModel
import com.smarttoolfactory.colorpicker.selector.HueSelectorRing
import com.smarttoolfactory.colorpicker.selector.SelectorRectSaturationValueHSV
import com.smarttoolfactory.colorpicker.selector.SelectorRingProperties
import com.smarttoolfactory.colorpicker.slider.CompositeSliderPanel
import com.smarttoolfactory.colorpicker.widget.ColorDisplayRoundedRect
import com.smarttoolfactory.colorpicker.widget.ColorModelChangeTabRow
import com.smarttoolfactory.extendedcolors.util.ColorUtil
import com.smarttoolfactory.extendedcolors.util.ColorUtil.colorToHSV

/**
 * ColorPicker with [HueSelectorRing] hue selector and [SelectorRectSaturationValueHSV]
 * saturation lightness Selector that uses [HSV](https://en.wikipedia.org/wiki/HSL_and_HSV)
 * color model as base.
 * This color picker has tabs section that can be changed between
 * HSL, HSV and RGB color models and color can be set using [CompositeSliderPanel] which contains
 * sliders for each color models.
 *
 * @param initialColor color that is passed to this picker initially.
 * @param onColorChange callback that is triggered when [Color] is changed using [HueSelectorRing],
 * [SelectorRectSaturationValueHSV] or [CompositeSliderPanel]
 */
@Composable
fun ColorPickerRingRectHSV(
    modifier: Modifier = Modifier,
    initialColor: Color,
    ringProperties: SelectorRingProperties = SelectorRingProperties(),
    onColorChange: (Color, String) -> Unit
) {
    var inputColorModel by remember { mutableStateOf(ColorModel.HSV) }

    val hsvArray = colorToHSV(initialColor)

    var hue by remember { mutableStateOf(hsvArray[0]) }
    var saturation by remember { mutableStateOf(hsvArray[1]) }
    var value by remember { mutableStateOf(hsvArray[2]) }
    var alpha by remember { mutableStateOf(initialColor.alpha) }

    val currentColor =
        Color.hsv(hue = hue, saturation = saturation, value = value, alpha = alpha)

    onColorChange(currentColor, ColorUtil.colorToHexAlpha(currentColor))

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        // Initial and Current Colors
        ColorDisplayRoundedRect(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp, vertical = 10.dp),
            initialColor = initialColor,
            currentColor = currentColor
        )

        Box(contentAlignment = Alignment.Center) {
            // Ring Shaped Hue Selector
            HueSelectorRing(
                modifier = Modifier.fillMaxWidth(1f),
                hue = hue,
                properties = ringProperties
            ) { hueChange ->
                hue = hueChange
            }

            // Rect Shaped Saturation and Lightness Selector
            SelectorRectSaturationValueHSV(
                modifier =
                    Modifier
                        .fillMaxWidth(ringProperties.innerRadiusFraction * .65f)
                        .aspectRatio(1f),
                hue = hue,
                saturation = saturation,
                value = value,
                selectionRadius = ringProperties.selectorRadius
            ) { s, v ->
                saturation = s
                value = v
            }
        }

        // HSL-HSV-RGB Color Model Change Tabs
        ColorModelChangeTabRow(
            modifier = Modifier.width(350.dp),
            colorModel = inputColorModel,
            onColorModelChange = {
                inputColorModel = it
            }
        )

        // HSL-HSV-RGB Sliders
        CompositeSliderPanel(
            modifier = Modifier.padding(start = 10.dp, end = 7.dp),
            compositeColor =
                ColorHSV(
                    hue = hue,
                    saturation = saturation,
                    value = value,
                    alpha = alpha
                ),
            onColorChange = {
                (it as? ColorHSV)?.let { color ->
                    hue = color.hue
                    saturation = color.saturation
                    value = color.value
                    alpha = color.alpha
                }
            },
            showAlphaSlider = true,
            inputColorModel = inputColorModel,
            outputColorModel = ColorModel.HSV
        )
    }
}
