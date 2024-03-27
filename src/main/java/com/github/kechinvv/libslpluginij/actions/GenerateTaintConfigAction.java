package com.github.kechinvv.libslpluginij.actions;

import com.github.kechinvv.libslpluginij.actions.utils.ToolType;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.diagnostic.Logger;
import org.jetbrains.annotations.NotNull;

import static com.github.kechinvv.libslpluginij.actions.utils.ActionUtils.*;


public class GenerateTaintConfigAction extends AnAction {
    public static final Logger LOG = Logger.getInstance("ConfigureANTLRAction");

    @Override
    public void update(AnActionEvent e) {
        e.getPresentation().setEnabledAndVisible(visibleForDir(e) || visibleForFile(e));
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        runLslTool(e, ToolType.TAINT_GENERATOR);
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.BGT;
    }

}