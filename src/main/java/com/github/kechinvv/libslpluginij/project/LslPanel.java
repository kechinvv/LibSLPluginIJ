package com.github.kechinvv.libslpluginij.project;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;

import javax.swing.*;

public class LslPanel extends ModuleWizardStep {
    private JPanel root;
    private JLabel Name;
    private JTextField textField1;
    private JTextField textField2;

    @Override
    public JComponent getComponent() {
        return root;
    }

    @Override
    public void updateDataModel() {

    }


}
