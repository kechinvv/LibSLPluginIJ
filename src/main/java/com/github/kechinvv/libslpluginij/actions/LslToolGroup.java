package com.github.kechinvv.libslpluginij.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.Project;

import javax.swing.*;

public class LslToolGroup extends DefaultActionGroup {


    public LslToolGroup(String shortName, boolean popup) {
        this(shortName, popup, (Icon) null);
    }


    public LslToolGroup(String shortName, boolean popup, Icon icon) {
        super(shortName, popup);
        getTemplatePresentation().setIcon(icon);
    }

    public LslToolGroup() {
    }


    protected final Project getProject(AnActionEvent event) {
        return (Project) event.getDataContext().getData("project");
    }
}
