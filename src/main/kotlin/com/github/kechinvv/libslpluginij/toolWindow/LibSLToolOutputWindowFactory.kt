package com.github.kechinvv.libslpluginij.toolWindow

import com.github.kechinvv.libslpluginij.actions.utils.ActionUtils.isLibSLModule
import com.github.kechinvv.libslpluginij.services.MyProjectService
import com.intellij.execution.filters.TextConsoleBuilderFactory
import com.intellij.execution.ui.ConsoleView
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowAnchor
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.Content
import com.intellij.ui.content.ContentFactory
import javax.swing.JComponent


class LibSLToolOutputWindowFactory : ToolWindowFactory {

    companion object {
        lateinit var console: ConsoleView
    }


    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val myToolWindow = LibSLToolOutput(toolWindow)
        toolWindow.contentManager.addContent(myToolWindow.getContent())
    }

    override fun isApplicable(project: Project) = runReadAction { isLibSLModule(project, project.projectFile) }

    override fun shouldBeAvailable(project: Project) = runReadAction { isLibSLModule(project, project.projectFile) }

    class LibSLToolOutput(toolWindow: ToolWindow) {

        private val project = toolWindow.project

        fun getContent() : Content {
            val contentFactory = ContentFactory.getInstance()
            val factory = TextConsoleBuilderFactory.getInstance()
            val consoleBuilder = factory.createBuilder(project)
            console = consoleBuilder.console
            val consoleComponent = console.component
            val content = contentFactory.createContent(consoleComponent, "", false)
            content.isCloseable = false
            content.setDisposer(console)
            return content
        }
    }
}
