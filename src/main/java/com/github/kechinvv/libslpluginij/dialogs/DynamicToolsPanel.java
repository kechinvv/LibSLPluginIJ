package com.github.kechinvv.libslpluginij.dialogs;

import com.github.kechinvv.libslpluginij.actions.LslToolAction;
import com.github.kechinvv.libslpluginij.actions.utils.ActionUtils;
import com.github.kechinvv.libslpluginij.language.LibSLIcon;
import com.github.kechinvv.libslpluginij.storage.ActionData;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;

import static com.github.kechinvv.libslpluginij.actions.utils.ActionUtils.createAction;
import static com.github.kechinvv.libslpluginij.LslNames.message;
import static com.github.kechinvv.libslpluginij.dialogs.LibSLToolsStore.*;

public class DynamicToolsPanel extends DialogWrapper {

    private final JTextField toolName = new JTextField();
    private final JTextField cmd = new JTextField();
    private final JTextField inputFlag = new JTextField();

    private final int defaultMainHeight = 500;
    private final int defaultMainWidth = 300;
    private final Project project;

    private final JPanel rowHolderPanel = new JPanel(new GridLayout(0, 1, 1, 1));

    public DynamicToolsPanel(@Nullable Project project) {
        super(project);
        this.project = project;
        setTitle(message("lsl.configuration.title"));
        setSize(defaultMainWidth, defaultMainHeight);
        loadActions();
        loadFields();
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        var dialogPanel = new JPanel(new BorderLayout());

        var list = new JPanel(new BorderLayout());
        list.add(rowHolderPanel, BorderLayout.PAGE_START);

        var scrollable = new JBScrollPane(list);
        scrollable.setVerticalScrollBarPolicy(JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollable.setHorizontalScrollBarPolicy(JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        dialogPanel.add(scrollable, BorderLayout.CENTER);
        dialogPanel.add(createAddActionFooter(), BorderLayout.AFTER_LAST_LINE);
        return dialogPanel;
    }

    private JPanel createAddActionFooter() {
        var footer = new JPanel(new GridLayout(0, 1, 1, 1));
        var addButton = new JButton("Add tool");
        addButton.addActionListener(e -> addToolAction());

        var nameLabel = new JLabel("Tool name:");
        var cmdLabel = new JLabel("Command");
        var inputLabel = new JLabel("Workdir flag (empty if input in cmd line)");

        cmd.setToolTipText("ex: java -jar .../.../Tool.jar -m ...");
        inputFlag.setToolTipText("ex: -i");

        var marginBottom = new JPanel();
        marginBottom.setSize(toolName.getSize());

        footer.add(nameLabel);
        footer.add(toolName);
        footer.add(cmdLabel);
        footer.add(cmd);
        footer.add(inputLabel);
        footer.add(inputFlag);
        footer.add(marginBottom);
        footer.add(addButton);
        return footer;
    }

    private void addToolAction() {
        if (cmd.getText().isEmpty() || toolName.getText().isEmpty()) return;

        var action = createAction(toolName.getText().trim(), cmd.getText().trim(), inputFlag.getText().trim());
        action.register();
        saveAction(project, action);

        addActionToPanel(action);

        toolName.setText("");
        cmd.setText("");
        inputFlag.setText("");

        rowHolderPanel.revalidate();
        rowHolderPanel.repaint();
    }


    public void saveAction(Project project, LslToolAction action) {
        if (action == null) return;
        var actions = getActions(project);
        actions.put(action.name, new ActionData(action.name, action.cmd, action.input));
    }

    public void saveInputsState(Project project) {
        saveAddedFields(project, toolName.getText(), cmd.getText(), inputFlag.getText());
    }

    private void deleteAction(Project project, LslToolAction action) {
        if (action == null) return;
        var actions = getActions(project);
        actions.remove(action.name);
    }


    private void loadActions() {
        var actions = getActions(project);
        actions.forEach((name, actionData) -> {
            var action = ActionUtils.findLslAction(name);
            addActionToPanel(action);
        });
        rowHolderPanel.repaint();
        rowHolderPanel.revalidate();
    }

    private void loadFields() {
        toolName.setText(getAddedName(project));
        cmd.setText(getAddedCmd(project));
        inputFlag.setText(getAddedInput(project));
    }


    private void addActionToPanel(LslToolAction action) {
        var newTool = new JPanel(new BorderLayout());

        var firstLine = new JPanel(new BorderLayout());
        var secondLine = new JPanel(new BorderLayout());

        var labelName = new JLabel(action.name);
        labelName.setBorder(JBUI.Borders.empty(4));
        firstLine.add(labelName, BorderLayout.LINE_START);

        var deleteActionButton = new JButton(LibSLIcon.DELETE);
        deleteActionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deleteActionButton.setIcon(LibSLIcon.DELETE_HOVERED);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                deleteActionButton.setIcon(LibSLIcon.DELETE);
            }
        });
        deleteActionButton.setBorder(JBUI.Borders.empty(4));
        deleteActionButton.addActionListener(e -> {
            var deleteApprove = new DeleteApprovePanel(project, labelName.getText());
            deleteApprove.show();
            if (deleteApprove.getExitCode() == OK_EXIT_CODE) {
                rowHolderPanel.remove(newTool);
                action.unregister();
                deleteAction(project, action);
                rowHolderPanel.repaint();
                rowHolderPanel.revalidate();
            }
        });
        firstLine.add(deleteActionButton, BorderLayout.LINE_END);

        var labelCmd = new JLabel(action.cmd + " " + action.input);
        labelCmd.setBorder(JBUI.Borders.empty(4));
        secondLine.add(labelCmd);

        var copyButton = new JButton(LibSLIcon.COPY);
        copyButton.setBorder(JBUI.Borders.empty(4));
        copyButton.setToolTipText("Copy cmd value");
        copyButton.addActionListener(e -> {
            var stringSelection = new StringSelection(labelCmd.getText());
            var clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
            clpbrd.setContents(stringSelection, null);
        });
        secondLine.add(copyButton, BorderLayout.LINE_END);

        newTool.add(firstLine, BorderLayout.BEFORE_FIRST_LINE);
        newTool.add(secondLine, BorderLayout.AFTER_LAST_LINE);
        newTool.setBorder(BorderFactory.createLineBorder(JBColor.BLACK, 3));
        rowHolderPanel.add(newTool);
    }

}
