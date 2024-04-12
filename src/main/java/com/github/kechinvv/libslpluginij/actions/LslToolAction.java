package com.github.kechinvv.libslpluginij.actions;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

import static com.github.kechinvv.libslpluginij.actions.utils.ActionUtils.runLslTool;

public abstract class LslToolAction extends AnAction {

    public String toolName;
    String cmd;
    String input;

    public LslToolAction(String cmd, String input) {
        this.cmd = cmd;
        this.input = input;
    }

    abstract public String getActionId();

    @Override
    public void update(AnActionEvent e) {
        e.getPresentation().setEnabledAndVisible(true);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        runLslTool(e, cmd, input);
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.BGT;
    }

    public void register() {
        ActionManager actionManager = ActionManager.getInstance();
        String actionId = getActionId();
        if (actionManager.getAction(actionId) == null) {
            System.out.println("REGISTER ACTION " + actionId);
            actionManager.registerAction(actionId, this);
        }
    }

    public void unregister() {
        ActionManager actionManager = ActionManager.getInstance();
        String actionId = getActionId();
        if (actionManager.getAction(actionId) != null) {
            System.out.println("UNREGISTER ACTION " + actionId);
            actionManager.unregisterAction(actionId);
        }
    }

}
