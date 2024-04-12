package com.github.kechinvv.libslpluginij.actions;

import com.github.kechinvv.libslpluginij.dialogs.DynamicToolsPanel;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogWrapper;
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
//        var toolsPanel = ConfigLibSLDialogPanel.getDialogForm(e.getProject());
//        toolsPanel.getPeer().setTitle(message("lsl.configuration.title"));
        var toolsPanel = new DynamicToolsPanel(e.getProject());
        toolsPanel.show();
//        var newAction = new LslToolAction("a", "b") {
//            @Override
//            String getActionId() {
//                return "TEST_ACTION";
//            }
//        };
//        newAction.getTemplatePresentation().setIcon(LibSLIcon.FILE);
//        newAction.getTemplatePresentation().setText("TEST ACTION");
//        DefaultActionGroup mainMenu = (DefaultActionGroup) ActionManager.getInstance().getAction("ProjectViewPopupMenu");
//        mainMenu.add(newAction, new Constraints(Anchor.BEFORE, "com.intellij.tools.ExternalToolsGroup"));
//        newAction.register();

        if (toolsPanel.getExitCode() == DialogWrapper.OK_EXIT_CODE) {
            toolsPanel.saveAction(e.getProject(), null);
        }
    }



    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.BGT;
    }
}
