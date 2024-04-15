package com.github.kechinvv.libslpluginij.actions;

import com.github.kechinvv.libslpluginij.dialogs.DynamicToolsPanel;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

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
        var toolsPanel = new DynamicToolsPanel(e.getProject());
        toolsPanel.show();
    }


    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.BGT;
    }
}
