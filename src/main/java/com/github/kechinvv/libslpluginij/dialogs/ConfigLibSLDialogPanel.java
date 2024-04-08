package com.github.kechinvv.libslpluginij.dialogs;

import com.github.kechinvv.libslpluginij.actions.LslToolAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

import java.util.ArrayList;

import static com.github.kechinvv.libslpluginij.dialogs.LibSLToolsStore.getActions;

public class ConfigLibSLDialogPanel extends DialogWrapper {
    private JPanel dialogContents;
    private TextFieldWithBrowseButton translatorCmd;
    private JTextField translatorInput;
    private TextFieldWithBrowseButton taintCmd;
    private JTextField taintInput;
    private JTextPane commandForTerminalWithoutTextPane;
    private JTextPane commandForTerminalWithoutTextPane1;
    private JTextPane onlyParameterNameTextArea;
    private JTextPane onlyParameterNameTextPane;

    protected ConfigLibSLDialogPanel(@Nullable Project project) {
        super(project);
    }

    public static ConfigLibSLDialogPanel getDialogForm(final Project project) {
        var grammarFrom = new ConfigLibSLDialogPanel(project);
        grammarFrom.init();
        grammarFrom.initFields(project);
        return grammarFrom;
    }

    private void initFields(Project project) {
//        var dirChooser = FileChooserDescriptorFactory.createSingleFolderDescriptor();
//        translatorCmd.addBrowseFolderListener("Select path to binary", null, project, dirChooser);
//        translatorCmd.setTextFieldPreferredWidth(50);
//        dirChooser = FileChooserDescriptorFactory.createSingleFolderDescriptor();
//        taintCmd.addBrowseFolderListener("Select path to binary", null, project, dirChooser);
//        taintCmd.setTextFieldPreferredWidth(50);
        loadValues(project);
    }

    private void loadValues(Project project) {
        var actions = getActions(project);

    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return dialogContents;
    }


    public void savaAction(Project project, LslToolAction action) {
        ArrayList<LslToolAction> actions = getActions(project);
        actions.add(action);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
