package com.github.kechinvv.libslpluginij.dialogs;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class ConfigLibSLDialogPanel extends DialogWrapper {
    private JPanel dialogContents;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;

    protected ConfigLibSLDialogPanel(@Nullable Project project) {
        super(project);
    }

    public static ConfigLibSLDialogPanel getDialogForm(final Project project) {
        var grammarFrom = new ConfigLibSLDialogPanel(project);
        grammarFrom.init();
//        grammarFrom.initAntlrFields(project, qualFileName);
        return grammarFrom;
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return dialogContents;
    }
}
