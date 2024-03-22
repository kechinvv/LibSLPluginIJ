package com.github.kechinvv.libslpluginij.project;

import com.intellij.ide.starters.local.StarterContext;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;

import javax.swing.*;

public class LslPanel extends ModuleWizardStep {
    StarterContext context;

    public LslPanel(StarterContext context) {
        this.context = context;
    }
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
