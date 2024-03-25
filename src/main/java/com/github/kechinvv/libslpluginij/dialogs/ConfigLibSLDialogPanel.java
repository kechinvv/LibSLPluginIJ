package com.github.kechinvv.libslpluginij.dialogs;

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

import static com.github.kechinvv.libslpluginij.dialogs.LibSLConfigPropsStore.getProperties;

public class ConfigLibSLDialogPanel extends DialogWrapper {
    private JPanel dialogContents;
    private TextFieldWithBrowseButton translatorBin;
    private JTextField translatorRun;
    private TextFieldWithBrowseButton taintBin;
    private JTextField taintRun;

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
        var dirChooser = FileChooserDescriptorFactory.createSingleFolderDescriptor();
        translatorBin.addBrowseFolderListener("Select path to binary", null, project, dirChooser);
        translatorBin.setTextFieldPreferredWidth(50);
        dirChooser = FileChooserDescriptorFactory.createSingleFolderDescriptor();
        taintBin.addBrowseFolderListener("Select path to binary", null, project, dirChooser);
        taintBin.setTextFieldPreferredWidth(50);
        loadValues(project);
    }

    private void loadValues(Project project) {
        var props = getProperties(project);
        translatorBin.setText(props.translatorBin);
        translatorRun.setText(props.translatorRun);
        taintBin.setText(props.taintBin);
        taintRun.setText(props.taintRun);
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return dialogContents;
    }


    public void saveValues(Project project) {
        LibSLConfigProps lslProps = getProperties(project);

        lslProps.translatorBin = translatorBin.getText();
        lslProps.translatorRun = translatorRun.getText();
        lslProps.taintBin = taintBin.getText();
        lslProps.taintRun = taintRun.getText();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
