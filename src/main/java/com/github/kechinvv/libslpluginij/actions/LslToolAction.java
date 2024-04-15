package com.github.kechinvv.libslpluginij.actions;

import com.github.kechinvv.libslpluginij.language.LibSLIcon;
import com.intellij.openapi.actionSystem.*;
import org.jetbrains.annotations.NotNull;

import static com.github.kechinvv.libslpluginij.actions.utils.ActionUtils.runLslTool;

public abstract class LslToolAction extends AnAction {

    public String name;
    public String cmd;
    public String input;

    public LslToolAction(String name, String cmd, String input) {
        this.name = name;
        this.cmd = cmd;
        this.input = input;
        this.getTemplatePresentation().setIcon(LibSLIcon.FILE);
        this.getTemplatePresentation().setText(this.name);
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

    public boolean wasRegistered() {
        ActionManager actionManager = ActionManager.getInstance();
        return actionManager.getAction(getActionId()) != null;
    }

    public void register() {
        DefaultActionGroup mainMenu = (DefaultActionGroup) ActionManager.getInstance().getAction("ProjectViewPopupMenu");
        mainMenu.add(this, new Constraints(Anchor.BEFORE, "com.intellij.tools.ExternalToolsGroup"));

        ActionManager actionManager = ActionManager.getInstance();
        String actionId = getActionId();
        if (actionManager.getAction(actionId) == null) {
            System.out.println("REGISTER ACTION " + actionId);
            actionManager.registerAction(actionId, this);
        }
    }

    public void unregister() {
        DefaultActionGroup mainMenu = (DefaultActionGroup) ActionManager.getInstance().getAction("ProjectViewPopupMenu");
        mainMenu.remove(this);

        ActionManager actionManager = ActionManager.getInstance();
        String actionId = getActionId();
        if (actionManager.getAction(actionId) != null) {
            System.out.println("UNREGISTER ACTION " + actionId);
            actionManager.unregisterAction(actionId);
        }
    }

    public String getName() {
        return getActionId();
    }

}
