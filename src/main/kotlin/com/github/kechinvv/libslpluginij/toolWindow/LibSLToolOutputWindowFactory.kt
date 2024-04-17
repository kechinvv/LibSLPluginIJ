package com.github.kechinvv.libslpluginij.toolWindow

import com.github.kechinvv.libslpluginij.actions.utils.ActionUtils.isLibSLModule
import com.intellij.execution.filters.TextConsoleBuilderFactory
import com.intellij.execution.ui.ConsoleView
import com.intellij.execution.ui.ConsoleViewContentType
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.Content
import com.intellij.ui.content.ContentFactory
import java.text.SimpleDateFormat
import java.util.*


class LibSLToolOutputWindowFactory : ToolWindowFactory {

    companion object {
        lateinit var toolOutput: LibSLToolOutput
    }


    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        toolOutput = LibSLToolOutput(toolWindow)
        toolWindow.contentManager.addContent(toolOutput.getContent())
    }

    override fun isApplicable(project: Project) = runReadAction { isLibSLModule(project, project.projectFile) }

    override fun shouldBeAvailable(project: Project) = runReadAction { isLibSLModule(project, project.projectFile) }

    class LibSLToolOutput(val toolWindow: ToolWindow) {

        private val project = toolWindow.project
        private lateinit var console: ConsoleView

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

        fun lslPrint(message: String) {
            val timeStamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().time)
            console.print("$timeStamp: $message\n", ConsoleViewContentType.SYSTEM_OUTPUT)
        }

        fun clear() {
            console.clear()
        }

        fun show() {
            toolWindow.show()
        }
    }
}
