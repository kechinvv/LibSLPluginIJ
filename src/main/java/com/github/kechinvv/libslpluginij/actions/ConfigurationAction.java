package com.github.kechinvv.libslpluginij.actions;

import com.github.kechinvv.libslpluginij.dialogs.ConfigLibSLDialogPanel;
import com.github.kechinvv.libslpluginij.language.LibSLFileType;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;

public class ConfigurationAction extends AnAction {

    @Override
    public void update(AnActionEvent e) {
        var psi = e.getData(CommonDataKeys.PSI_FILE);
        boolean isLsl = (psi != null) && (psi.getFileType() == LibSLFileType.INSTANCE);
        e.getPresentation().setEnabledAndVisible(isLsl);
    }
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        if (e.getProject()==null ) {
            return;
        }
//        VirtualFile grammarFile = MyActionUtils.getGrammarFileFromEvent(e);
//        if ( grammarFile==null ) return;
//        LOG.info("actionPerformed "+grammarFile);

        var configDialog = ConfigLibSLDialogPanel.getDialogForm(e.getProject());
        configDialog.getPeer().setTitle("Configure LibSL");

        configDialog.show();

        if ( configDialog.getExitCode()== DialogWrapper.OK_EXIT_CODE ) {
//            configDialog.saveValues(e.getProject(), grammarFile.getPath());
        }
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.BGT;
    }
}
