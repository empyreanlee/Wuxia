package com.example.wuxia.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.wuxia.R

data class Sage(
    @StringRes val name: Int,
    @StringRes val desc: Int,
    @DrawableRes val image: Int,
)

