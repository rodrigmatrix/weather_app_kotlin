package com.rodrigmatrix.protonweather.data.provider

import com.rodrigmatrix.protonweather.internal.UnitSystem

interface UnitProvider {
    fun getUnitSystem(): UnitSystem
}