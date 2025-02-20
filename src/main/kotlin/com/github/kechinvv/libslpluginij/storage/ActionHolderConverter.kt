package com.github.kechinvv.libslpluginij.storage

import com.intellij.util.xmlb.Converter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ActionHolderConverter : Converter<Map<String, ActionData>>() {
    override fun toString(actions: Map<String, ActionData>): String {
        return Json.encodeToString(actions)
    }

    override fun fromString(actions: String): Map<String, ActionData> {
        return Json.decodeFromString(actions) ?: emptyMap()
    }
}