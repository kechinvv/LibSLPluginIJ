package com.github.kechinvv.libslpluginij.actions;

import com.github.kechinvv.libslpluginij.dialogs.LibSLConfigPropsStore;
import com.github.kechinvv.libslpluginij.language.LibSLFileType;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.diagnostic.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class TranslateAction extends AnAction {
    public final static Logger LOG = Logger.getInstance("TranslateAction");

    @Override
    public void update(AnActionEvent e) {
        //TODO: need refactoring
        var psi = e.getData(CommonDataKeys.PSI_FILE);
        var virtualFile = e.getData(CommonDataKeys.VIRTUAL_FILE);
        var project = e.getData(CommonDataKeys.PROJECT);
        var isLslModule = false;
        if (project != null && virtualFile != null)
            isLslModule = Objects.equals(
                    ProjectFileIndex.getInstance(project).getModuleForFile(virtualFile).getModuleTypeName(),
                    "LIBSL_MODULE");
        boolean isLsl = (psi != null) && (psi.getFileType() == LibSLFileType.INSTANCE);
        e.getPresentation().setEnabledAndVisible(isLsl || isLslModule);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        var project = e.getData(CommonDataKeys.PROJECT);
        var file = e.getData(CommonDataKeys.VIRTUAL_FILE);
        if (file == null || project == null) return;
        if(!file.isDirectory()) return;
        var props = LibSLConfigPropsStore.getProperties(project);
        var cmd = props.translatorCmd;
        var workdir = props.translatorInput;
        ProcessBuilder builder = new ProcessBuilder(cmd, workdir+ "=" + file.getPath());
        try {
            var process = builder.start();
            var r = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) { break; }
                System.out.println(line);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.BGT;
    }
}
