package com.github.kechinvv.libslpluginij.toolWindow

import com.github.kechinvv.libslpluginij.LslNames.message
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import com.intellij.openapi.wm.ToolWindowManager

class ToolStartUp : StartupActivity {
    override fun runActivity(project: Project) {
        ToolWindowManager.getInstance(project).getToolWindow(message("lsl.tool.output.name"))?.let {
            if (!it.isActive) it.activate(null, false)
            it.hide()
        }
    }
}