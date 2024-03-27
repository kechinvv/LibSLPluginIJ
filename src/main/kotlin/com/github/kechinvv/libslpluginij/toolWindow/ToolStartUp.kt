package com.github.kechinvv.libslpluginij.toolWindow

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import com.intellij.openapi.wm.ToolWindowManager

class ToolStartUp : StartupActivity {
    override fun runActivity(project: Project) {
        ToolWindowManager.getInstance(project).getToolWindow("LibSL Output")?.let {
            if (!it.isActive) it.activate(null, false)
            it.hide()
        }
    }
}