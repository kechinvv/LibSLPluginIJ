package com.github.kechinvv.libslpluginij.actions;

import com.github.kechinvv.libslpluginij.language.LibSLFileType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class GenerateTaintConfigAction extends AnAction {
    public static final Logger LOG = Logger.getInstance("ConfigureANTLRAction");

    @Override
    public void update(AnActionEvent e) {
        boolean isLsl = Objects.requireNonNull(e.getData(CommonDataKeys.PSI_FILE)).getFileType() == LibSLFileType.INSTANCE;
        e.getPresentation().setEnabledAndVisible(isLsl);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        // Using the event, create and show a dialog
        Project currentProject = event.getProject();
        if (event.getProject() == null) {
            LOG.error("actionPerformed no project for " + event);
            return; // whoa!
        }
        LOG.info("Performed success " + currentProject.getBasePath());
        // If an element is selected in the editor, add info about it.

    }

    // Override getActionUpdateThread() when you target 2022.3 or later!

}