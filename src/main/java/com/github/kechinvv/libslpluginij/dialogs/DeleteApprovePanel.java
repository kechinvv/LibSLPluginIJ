package com.github.kechinvv.libslpluginij.dialogs;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

import java.awt.*;

import static com.github.kechinvv.libslpluginij.LslNames.message;

public class DeleteApprovePanel extends DialogWrapper {
    protected DeleteApprovePanel(@Nullable Project project, String name) {
        super(project);
        setTitle(message("lsl.configuration.delete.title") + " " + name);
        setOKButtonText("Delete");
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        var dialogPanel = new JPanel(new BorderLayout());
        var button = new JButton("Are you sure?");
        button.setBorder(JBUI.Borders.empty(10));
        dialogPanel.add(button, BorderLayout.CENTER);
        return dialogPanel;
    }
}
