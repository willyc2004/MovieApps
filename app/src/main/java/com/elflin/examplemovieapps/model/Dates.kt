package com.elflin.examplemovieapps.model

import kotlinx.serialization.Serializable

@Serializable
data class Dates(
    val maximum: String,
    val minimum: String
)