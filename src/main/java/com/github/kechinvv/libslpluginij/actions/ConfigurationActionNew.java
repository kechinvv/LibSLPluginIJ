package com.github.kechinvv.libslpluginij.actions;

import com.github.kechinvv.libslpluginij.dialogs.ConfigLibSLDialogPanel;
import com.github.kechinvv.libslpluginij.dialogs.ConfigLibSLDialogPanelNew;
import com.github.kechinvv.libslpluginij.language.LibSLIcon;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

import static com.github.kechinvv.libslpluginij.actions.utils.ActionUtils.visibleForDir;
import static com.github.kechinvv.libslpluginij.actions.utils.ActionUtils.visibleForFile;

public class ConfigurationActionNew extends AnAction {

    @Override
    public void update(AnActionEvent e) {
        e.getPresentation().setEnabledAndVisible(visibleForDir(e) || visibleForFile(e));
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        var newAction = new LslToolAction() {
            @Override
            String getActionId() {
                return "TEST_ACTION";
            }
        };
        newAction.getTemplatePresentation().setIcon(LibSLIcon.FILE);
        newAction.getTemplatePresentation().setText("TEST ACTION");
        DefaultActionGroup mainMenu = (DefaultActionGroup) ActionManager.getInstance().getAction("ProjectViewPopupMenu");
        mainMenu.add(newAction, new Constraints(Anchor.BEFORE, "com.intellij.tools.ExternalToolsGroup"));
        newAction.register();

    }



    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.BGT;
    }
}
