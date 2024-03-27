package com.github.kechinvv.libslpluginij.toolWindow

import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.content.ContentFactory
import com.github.kechinvv.libslpluginij.MyBundle
import com.github.kechinvv.libslpluginij.actions.utils.ActionUtils.isLibSLModule
import com.github.kechinvv.libslpluginij.services.MyProjectService
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.wm.ToolWindowAnchor
import javax.swing.JButton


class MyToolWindowFactory : ToolWindowFactory {


    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        toolWindow.setAnchor(ToolWindowAnchor.BOTTOM, null)
        val myToolWindow = MyToolWindow(toolWindow)
        val content = ContentFactory.getInstance().createContent(myToolWindow.getContent(), null, false)
        toolWindow.contentManager.addContent(content)
    }

    override fun shouldBeAvailable(project: Project) = runReadAction { isLibSLModule(project, project.projectFile) }

    class MyToolWindow(toolWindow: ToolWindow) {

        private val service = toolWindow.project.service<MyProjectService>()

        fun getContent() = JBPanel<JBPanel<*>>().apply {
            val label = JBLabel(MyBundle.message("randomLabel", "?"))

            add(label)
            add(JButton(MyBundle.message("shuffle")).apply {
                addActionListener {
                    label.text = MyBundle.message("randomLabel", service.getRandomNumber())
                }
            })
        }
    }
}
