package com.github.kechinvv.libslpluginij.activities

import com.github.kechinvv.libslpluginij.actions.LslToolAction
import com.github.kechinvv.libslpluginij.dialogs.LibSLToolsStore
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectPostStartupActivity

class ActionLoader : ProjectPostStartupActivity {
    override suspend fun execute(project: Project) {
        val actions = LibSLToolsStore.getActions(project)
        actions.forEach { (_: String?, action: LslToolAction) ->
            if (!action.wasRegistered()) action.register()
        }
    }
}