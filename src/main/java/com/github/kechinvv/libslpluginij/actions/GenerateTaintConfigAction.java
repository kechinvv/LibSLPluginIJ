package com.github.kechinvv.libslpluginij.actions;

import com.github.kechinvv.libslpluginij.language.LibSLFileType;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.github.kechinvv.libslpluginij.actions.utils.ActionUtils.visibleForDir;
import static com.github.kechinvv.libslpluginij.actions.utils.ActionUtils.visibleForFile;


public class GenerateTaintConfigAction extends AnAction {
    public static final Logger LOG = Logger.getInstance("ConfigureANTLRAction");

    @Override
    public void update(AnActionEvent e) {
        e.getPresentation().setEnabledAndVisible(visibleForDir(e) || visibleForFile(e));
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        // Using the event, create and show a dialog
        VirtualFile currentFile = event.getData(PlatformDataKeys.VIRTUAL_FILE);
        if (currentFile == null) {
            LOG.error("actionPerformed no project for " + event);
            return;
        }
        LOG.info("Performed success " + currentFile.getCanonicalPath());
        // If an element is selected in the editor, add info about it.
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.BGT;
    }

}