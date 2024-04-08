package com.github.kechinvv.libslpluginij.actions;

import com.github.kechinvv.libslpluginij.dialogs.ConfigLibSLDialogPanelOld;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;

import static com.github.kechinvv.libslpluginij.LslNames.message;
import static com.github.kechinvv.libslpluginij.actions.utils.ActionUtils.visibleForDir;
import static com.github.kechinvv.libslpluginij.actions.utils.ActionUtils.visibleForFile;

public class ConfigurationActionOld extends AnAction {

    @Override
    public void update(AnActionEvent e) {
        e.getPresentation().setEnabledAndVisible(visibleForDir(e) || visibleForFile(e));
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        if (e.getProject() == null) {
            return;
        }
        var configDialog = ConfigLibSLDialogPanelOld.getDialogForm(e.getProject());
        configDialog.getPeer().setTitle(message("lsl.configuration.title"));

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
