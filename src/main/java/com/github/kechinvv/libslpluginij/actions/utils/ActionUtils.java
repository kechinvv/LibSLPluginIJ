package com.github.kechinvv.libslpluginij.actions.utils;

import com.github.kechinvv.libslpluginij.language.LibSLFileType;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.vfs.VirtualFile;

import java.util.Objects;

public class ActionUtils {

    public static boolean visibleForDir(AnActionEvent e) {
        var virtualFile = e.getData(CommonDataKeys.VIRTUAL_FILE);
        var project = e.getData(CommonDataKeys.PROJECT);
        var visible = false;
        if (project != null && virtualFile != null) {
            visible = virtualFile.isDirectory() && isLibSLModule(project, virtualFile);
        }
        return visible;
    }

    public static boolean visibleForFile(AnActionEvent e) {
        var psi = e.getData(CommonDataKeys.PSI_FILE);
        return (psi != null) && (psi.getFileType() == LibSLFileType.INSTANCE);
    }

    public static boolean isLibSLModule(Project project, VirtualFile virtualFile) {
        var module = ProjectFileIndex.getInstance(project).getModuleForFile(virtualFile);
        if (module == null) return false;
        return Objects.equals(module.getModuleTypeName(), "LIBSL_MODULE");
    }
}
