package com.github.kechinvv.libslpluginij.actions.utils;

import com.github.kechinvv.libslpluginij.dialogs.LibSLConfigPropsStore;
import com.github.kechinvv.libslpluginij.language.LibSLFileType;
import com.github.kechinvv.libslpluginij.toolWindow.LibSLToolOutputWindowFactory;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public static void runLslTool(@NotNull AnActionEvent e, ToolType type) {
        var project = e.getData(CommonDataKeys.PROJECT);
        var targetFile = e.getData(CommonDataKeys.VIRTUAL_FILE);
        if (targetFile == null || project == null) return;
        if (!targetFile.isDirectory()) return;
        var props = LibSLConfigPropsStore.getProperties(project);
        switch (type) {
            case TRANSLATOR -> processRun(props.translatorCmd, props.translatorInput, targetFile);
            case TAINT_GENERATOR -> processRun(props.taintCmd, props.taintInput, targetFile);
        }

    }

    public static void processRun(String cmd, String workdir, VirtualFile targetFile) {
        ProcessBuilder builder = new ProcessBuilder(cmd, workdir + "=" + targetFile.getPath());
        try {
            var process = builder.start();
            var r = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = r.readLine();
            while (line != null) {
                LibSLToolOutputWindowFactory.toolOutput.lslPrint(line);
                line = r.readLine();
            }
        } catch (IOException ex) {
            LibSLToolOutputWindowFactory.toolOutput.lslPrint(ex.getClass() + ": " + ex.getMessage());
        }
        LibSLToolOutputWindowFactory.toolOutput.show();
    }

}
