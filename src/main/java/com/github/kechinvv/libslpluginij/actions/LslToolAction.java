package com.github.kechinvv.libslpluginij.actions;

import com.github.kechinvv.libslpluginij.actions.utils.ToolType;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

import static com.github.kechinvv.libslpluginij.actions.utils.ActionUtils.*;

abstract class LslToolAction extends AnAction {

    abstract String getActionId();
    @Override
    public void update(AnActionEvent e) {
        e.getPresentation().setEnabledAndVisible(true);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        runLslTool(e, ToolType.TRANSLATOR);
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.BGT;
    }

    public  void register() {
        ActionManager actionManager = ActionManager.getInstance();
        String actionId = getActionId();
        if (actionManager.getAction(actionId) == null) {
            System.out.println("REGISTER ACTION " + actionId);
            actionManager.registerAction(actionId, this);
        }
    }

}
