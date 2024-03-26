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

import static com.github.kechinvv.libslpluginij.actions.utils.ActionUtils.visibleForDir;
import static com.github.kechinvv.libslpluginij.actions.utils.ActionUtils.visibleForFile;

public class ConfigurationAction extends AnAction {

    @Override
    public void update(AnActionEvent e) {
        e.getPresentation().setEnabledAndVisible(visibleForDir(e) || visibleForFile(e));
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
