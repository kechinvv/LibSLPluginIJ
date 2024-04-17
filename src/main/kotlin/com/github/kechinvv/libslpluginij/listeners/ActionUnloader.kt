package com.github.kechinvv.libslpluginij.listeners

import com.github.kechinvv.libslpluginij.actions.utils.ActionUtils
import com.github.kechinvv.libslpluginij.dialogs.LibSLToolsStore
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener

class ActionUnloader : ProjectManagerListener {

    override fun projectClosing(project: Project) {
        LibSLToolsStore.getActions(project).forEach { (name, _) ->
            ActionUtils.findLslAction(name)?.unregister()
        }
    }
}