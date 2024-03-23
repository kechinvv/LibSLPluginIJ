package com.github.kechinvv.libslpluginij.project

import com.github.kechinvv.libslpluginij.language.LibSLIcon
import com.intellij.ide.util.projectWizard.SourcePathsBuilder
import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.ide.wizard.*
import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ModifiableRootModel
import com.intellij.openapi.util.Pair
import com.intellij.openapi.util.io.FileUtil
import com.intellij.openapi.vfs.LocalFileSystem
import org.jetbrains.annotations.NonNls
import java.io.File
import javax.swing.Icon


class EmptyLslModuleBuilder : GeneratorNewProjectWizardBuilderAdapter(EmptyNewModuleWizard()), SourcePathsBuilder {
    private var sourcePaths: MutableList<Pair<String, String>>? = null



    override fun setupRootModel(rootModel: ModifiableRootModel) {
        val contentEntry = doAddContentEntry(rootModel)
        sourcePaths?.let {
            for (sourcePath in it) {
                val first = sourcePath.first
                File(first).mkdirs()
                val sourceRoot = LocalFileSystem.getInstance()
                    .refreshAndFindFileByPath(FileUtil.toSystemIndependentName(first))
                if (sourceRoot != null) {
                    contentEntry!!.addSourceFolder(sourceRoot, false, sourcePath.second!!)
                }
            }
        }
    }

    class EmptyNewModuleWizard : GeneratorNewProjectWizard {
        override val id: String = "libsl"
        override val name: String = "LibSL"
        override val description: String = "Create LibSL specification"
        override val icon: Icon = LibSLIcon.FILE;

        override fun createStep(context: WizardContext) =
            RootNewProjectWizardStep(context).chain(
                ::newProjectWizardBaseStepWithoutGap,
                ::GitNewProjectWizardStep
//                ::Step
            )
    }


    private class Step(parent: NewProjectWizardStep) : AbstractNewProjectWizardStep(parent) {
        override fun setupProject(project: Project) {
            val moduleType = LibSLModuleType.getInstance()
            val moduleBuilder = moduleType.createModuleBuilder()
            moduleBuilder.commit(project)
        }
    }

    override fun getSourcePaths(): MutableList<Pair<String, String>> {
        if (sourcePaths == null) {
            val paths: MutableList<Pair<String, String>> = mutableListOf()
            @NonNls val path = getContentEntryPath() + File.separator + "spec"
            File(path).mkdirs()
            paths.add(Pair.create(path, ""))
            return paths
        }
        return sourcePaths!!
    }

    override fun setSourcePaths(sourcePaths: MutableList<Pair<String, String>>?) {
        this.sourcePaths = sourcePaths
    }

    override fun addSourcePath(sourcePathInfo: Pair<String, String>?) {
        sourcePathInfo?.let { getSourcePaths().add(it) }
    }
}