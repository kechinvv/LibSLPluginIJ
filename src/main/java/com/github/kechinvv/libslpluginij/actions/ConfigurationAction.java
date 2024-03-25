package com.github.kechinvv.libslpluginij.actions;

import com.github.kechinvv.libslpluginij.dialogs.ConfigLibSLDialogPanel;
import com.github.kechinvv.libslpluginij.language.LibSLFileType;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ConfigurationAction extends AnAction {

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
        if (e.getProject() == null) {
            return;
        }
        var configDialog = ConfigLibSLDialogPanel.getDialogForm(e.getProject());
        configDialog.getPeer().setTitle("Configure LibSL");

        configDialog.show();

        if (configDialog.getExitCode() == DialogWrapper.OK_EXIT_CODE) {
            configDialog.saveValues(e.getProject());
        }
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.BGT;
    }
}
