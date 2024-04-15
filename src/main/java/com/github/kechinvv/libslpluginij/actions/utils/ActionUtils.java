package com.github.kechinvv.libslpluginij.actions.utils;

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

import static com.github.kechinvv.libslpluginij.LslNames.message;

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
        if (virtualFile == null || project == null) return false;
        var module = ProjectFileIndex.getInstance(project).getModuleForFile(virtualFile);
        if (module == null) return false;
        return Objects.equals(module.getModuleTypeName(), message("lsl.module"));
    }

    public static void runLslTool(@NotNull AnActionEvent e, String cmd, String input) {
        var project = e.getData(CommonDataKeys.PROJECT);
        var targetFile = e.getData(CommonDataKeys.VIRTUAL_FILE);
        if (targetFile == null || project == null) return;
        processRun(cmd, input, targetFile);
    }

    public static void processRun(String cmd, String workdir, VirtualFile targetFile) {
        ProcessBuilder builder;
        if (workdir.isEmpty()) builder = new ProcessBuilder(cmd);
        else builder = new ProcessBuilder(cmd, workdir + "=" + targetFile.getPath());

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
