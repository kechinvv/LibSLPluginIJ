package com.github.kechinvv.libslpluginij.actions.utils;

import com.github.kechinvv.libslpluginij.actions.LslToolAction;
import com.github.kechinvv.libslpluginij.language.LibSLFileType;
import com.github.kechinvv.libslpluginij.project.LibSLModuleType;
import com.github.kechinvv.libslpluginij.toolWindow.LibSLToolOutputWindowFactory;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


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
        var modules = ModuleUtil.getModulesOfType(project, LibSLModuleType.getInstance());
        return !modules.isEmpty();
    }


    public static void runLslTool(@NotNull AnActionEvent e, String name, String cmd, String input) {
        var project = e.getData(CommonDataKeys.PROJECT);
        var targetFile = e.getData(CommonDataKeys.VIRTUAL_FILE);
        if (targetFile == null || project == null) return;
        processRun(name, cmd, input, targetFile);
    }


    public static void processRun(String name, String cmd, String workdir, VirtualFile targetFile) {
        var buildedCmd = cmd;
        if (!workdir.isEmpty()) buildedCmd += " " + workdir + "=" + targetFile.getPath();
        try {
            LibSLToolOutputWindowFactory.toolOutput.clear();
            LibSLToolOutputWindowFactory.toolOutput.show();
            var process = Runtime.getRuntime().exec(buildedCmd);
            LibSLToolOutputWindowFactory.toolOutput.lslPrint("Run tool " + name + " " + buildedCmd);
            var r = new BufferedReader(new InputStreamReader(process.getInputStream()));
            var errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            new Thread(() -> {
                try {
                    printer(r);
                    printer(errors);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        } catch (IOException ex) {
            LibSLToolOutputWindowFactory.toolOutput.lslPrint(ex.getClass() + ": " + ex.getMessage());
        }
    }

    private static void printer(BufferedReader reader) throws IOException {
        var line = reader.readLine();
        while (line != null) {
            LibSLToolOutputWindowFactory.toolOutput.lslPrint(line);
            line = reader.readLine();
        }
    }


    public static LslToolAction createAction(String name, String cmd, String input) {
        return new LslToolAction(name, cmd, input) {
            @Override
            public String getActionId() {
                return name;
            }
        };
    }

    public static LslToolAction findLslAction(String name) {
        ActionManager actionManager = ActionManager.getInstance();
        return (LslToolAction) actionManager.getAction(name);
    }

}
