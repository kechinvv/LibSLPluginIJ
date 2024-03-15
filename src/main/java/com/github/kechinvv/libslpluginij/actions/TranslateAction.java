package com.github.kechinvv.libslpluginij.actions;

import com.github.kechinvv.libslpluginij.language.LibSLFileType;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import org.jetbrains.annotations.NotNull;

public class TranslateAction extends AnAction {

    @Override
    public void update(AnActionEvent e) {
        var psi = e.getData(CommonDataKeys.PSI_FILE);
        boolean isLsl = (psi != null) && (psi.getFileType() == LibSLFileType.INSTANCE);
        e.getPresentation().setEnabledAndVisible(isLsl);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.BGT;
    }
}
