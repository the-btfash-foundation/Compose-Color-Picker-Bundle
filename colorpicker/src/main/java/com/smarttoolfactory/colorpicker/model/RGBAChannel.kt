package com.smarttoolfactory.colorpicker.model

enum class RGBAChannel {
    Red,
    Green,
    Blue,
    Alpha;

    val isAlpha get() = this == Alpha
}
