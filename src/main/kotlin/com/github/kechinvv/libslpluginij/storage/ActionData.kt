package com.github.kechinvv.libslpluginij.storage

import kotlinx.serialization.Serializable

@Serializable
data class ActionData(val name: String, val cmd: String, val inp: String)
