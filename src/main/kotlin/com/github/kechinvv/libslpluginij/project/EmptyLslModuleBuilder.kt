package com.github.kechinvv.libslpluginij.project

import com.github.kechinvv.libslpluginij.language.LibSLIcon
import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.ide.wizard.*
import com.intellij.openapi.module.ModuleTypeManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ModifiableRootModel
import com.intellij.openapi.util.Pair
import com.intellij.openapi.util.io.FileUtil
import com.intellij.openapi.vfs.LocalFileSystem
import java.io.File
import javax.swing.Icon


class EmptyLslModuleBuilder : GeneratorNewProjectWizardBuilderAdapter(EmptyNewModuleWizard()) {

    override fun setupRootModel(rootModel: ModifiableRootModel) {
        val contentEntry = doAddContentEntry(rootModel)
        contentEntry

    }
    class EmptyNewModuleWizard : GeneratorNewProjectWizard {
        override val id: String = "libsl"
        override val name: String = "LibSL"
        override val description: String = "Create LibSL specification"
        override val icon: Icon = LibSLIcon.FILE;

        override fun createStep(context: WizardContext) =
            RootNewProjectWizardStep(context).chain(
                ::newProjectWizardBaseStepWithoutGap,
                ::GitNewProjectWizardStep,
                ::Step
            )
    }


    private class Step(parent: NewProjectWizardStep) : AbstractNewProjectWizardStep(parent) {
        override fun setupProject(project: Project) {
            val moduleType = ModuleTypeManager.getInstance().findByID("LIBSL_MODULE")
            moduleType.createModuleBuilder().commit(project)
        }
    }
}