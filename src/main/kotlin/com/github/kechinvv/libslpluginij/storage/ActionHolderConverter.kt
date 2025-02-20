package com.github.kechinvv.libslpluginij.storage

import com.intellij.util.xmlb.Converter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ActionHolderConverter : Converter<Map<String, ActionData>>() {
    override fun toString(value: Map<String, ActionData>): String {
        return Json.encodeToString(value)
    }

    override fun fromString(value: String): Map<String, ActionData> {
        return Json.decodeFromString(value) ?: emptyMap()
    }
}