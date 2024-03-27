package com.github.kechinvv.libslpluginij.actions;

import com.github.kechinvv.libslpluginij.actions.utils.ToolType;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.Logger;
import org.jetbrains.annotations.NotNull;

import static com.github.kechinvv.libslpluginij.actions.utils.ActionUtils.*;

public class TranslateAction extends AnAction {
    public final static Logger LOG = Logger.getInstance("TranslateAction");

    @Override
    public void update(AnActionEvent e) {
        e.getPresentation().setEnabledAndVisible(visibleForDir(e) || visibleForFile(e));
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        runLslTool(e, ToolType.TRANSLATOR);
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.BGT;
    }
}
