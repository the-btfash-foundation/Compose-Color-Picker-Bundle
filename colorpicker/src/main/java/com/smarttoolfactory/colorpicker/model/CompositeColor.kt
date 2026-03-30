package com.smarttoolfactory.colorpicker.model

import androidx.annotation.FloatRange
import androidx.compose.ui.graphics.Color
import com.smarttoolfactory.extendedcolors.util.HSLUtil
import com.smarttoolfactory.extendedcolors.util.HSVUtil
import com.smarttoolfactory.extendedcolors.util.RGBUtil

/**
 * Interface that can be polymorph into HSV, HSL or RGB color model
 */
internal sealed interface CompositeColor {
    val color: Color
    val argbHexString: String
    val rgbHexString: String

    fun convertTo(model: ColorModel): CompositeColor
}

/**
 * Color in HSV color model
 */
internal data class ColorHSV(
    @FloatRange(from = 0.0, to = 360.0) val hue: Float,
    @FloatRange(from = 0.0, to = 1.0) val saturation: Float,
    @FloatRange(from = 0.0, to = 1.0) val value: Float,
    @FloatRange(from = 0.0, to = 1.0) val alpha: Float
) : CompositeColor {

    override val color: Color
        get() = Color.hsv(hue, saturation, value, alpha)

    override val argbHexString: String
        get() = RGBUtil.argbToHex(color.alpha, color.red, color.green, color.blue)

    override val rgbHexString: String
        get() = RGBUtil.rgbToHex(color.red, color.green, color.blue)

    override fun convertTo(model: ColorModel): CompositeColor =
        when (model) {
            ColorModel.HSV -> this
            ColorModel.HSL -> {
                val hsl = HSVUtil.hsvToHSL(hue, saturation, value)
                ColorHSL(hsl[0], hsl[1], hsl[2], alpha)
            }

            ColorModel.RGB -> {
                val rgb = HSVUtil.hsvToRGBFloat(hue, saturation, value)
                ColorRGB(rgb[0], rgb[1], rgb[2], alpha)
            }
        }
}

/**
 * Color in HSL color model
 */
internal data class ColorHSL(
    @FloatRange(from = 0.0, to = 360.0) val hue: Float,
    @FloatRange(from = 0.0, to = 1.0) val saturation: Float,
    @FloatRange(from = 0.0, to = 1.0) val lightness: Float,
    @FloatRange(from = 0.0, to = 1.0) val alpha: Float
) : CompositeColor {

    override val color: Color
        get() = Color.hsl(hue, saturation, lightness, alpha)

    override val argbHexString: String
        get() = RGBUtil.argbToHex(color.alpha, color.red, color.green, color.blue)

    override val rgbHexString: String
        get() = RGBUtil.rgbToHex(color.red, color.green, color.blue)

    override fun convertTo(model: ColorModel): CompositeColor =
        when (model) {
            ColorModel.HSL -> this
            ColorModel.HSV -> {
                val hsv = HSLUtil.hslToHSV(hue, saturation, lightness)
                ColorHSV(hsv[0], hsv[1], hsv[2], alpha)
            }

            ColorModel.RGB -> {
                val rgb = HSLUtil.hslToRGBFloat(hue, saturation, lightness)
                ColorRGB(rgb[0], rgb[1], rgb[2], alpha)
            }
        }
}

/**
 * Color in RGB color model
 */
internal data class ColorRGB(
    @FloatRange(from = 0.0, to = 1.0) val red: Float = 1f,
    @FloatRange(from = 0.0, to = 1.0) val green: Float = 1f,
    @FloatRange(from = 0.0, to = 1.0) val blue: Float = 1f,
    @FloatRange(from = 0.0, to = 1.0) val alpha: Float = 1f
) : CompositeColor {

    override val color: Color
        get() = Color(red, green, blue, alpha)

    override val argbHexString: String
        get() = RGBUtil.argbToHex(color.alpha, color.red, color.green, color.blue)

    override val rgbHexString: String
        get() = RGBUtil.rgbToHex(color.red, color.green, color.blue)

    override fun convertTo(model: ColorModel): CompositeColor =
        when (model) {
            ColorModel.RGB -> this
            ColorModel.HSV -> {
                val hsv = RGBUtil.rgbFloatToHSV(red, green, blue)
                ColorHSV(hsv[0], hsv[1], hsv[2], alpha)
            }

            ColorModel.HSL -> {
                val hsl = RGBUtil.rgbFloatToHSL(red, green, blue)
                ColorHSL(hsl[0], hsl[1], hsl[2], alpha)
            }
        }

    operator fun get(channel: RGBAChannel): Float =
        when (channel) {
            RGBAChannel.Red -> red
            RGBAChannel.Green -> green
            RGBAChannel.Blue -> blue
            RGBAChannel.Alpha -> alpha
        }
}
