package com.github.kechinvv.libslpluginij.dialogs;

import com.github.kechinvv.libslpluginij.actions.LslToolAction;
import com.github.kechinvv.libslpluginij.actions.utils.ActionUtils;
import com.github.kechinvv.libslpluginij.language.LibSLIcon;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.util.ui.InlineIconButton;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

import static com.github.kechinvv.libslpluginij.actions.utils.ActionUtils.createAction;
import static com.github.kechinvv.libslpluginij.dialogs.LibSLToolsStore.getActions;
import static com.github.kechinvv.libslpluginij.LslNames.message;

public class DynamicToolsPanel extends DialogWrapper {

    private JTextField toolName;
    private JTextField cmd;
    private JTextField inputFlag;
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

        toolName = new JTextField();
        cmd = new JTextField();
        inputFlag = new JTextField();

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
        //TODO: impl structure for serialization
        actions.put(action.name, action.cmd + "@tempkostil " + action.input);
    }

    private void deleteAction(Project project, LslToolAction action) {
        if (action == null) return;
        var actions = getActions(project);
        actions.remove(action.name);
    }


    private void loadActions() {
        var actions = getActions(project);
        actions.forEach((name, actionDataStr) -> {
            var action = ActionUtils.findLslAction(name);
            var actionData = actionDataStr.split("@tempkostil");
            if (action == null) action = createAction(name, actionData[0], actionData[1].trim());
            if (!action.wasRegistered()) action.register();
            addActionToPanel(action);
        });
        rowHolderPanel.repaint();
        rowHolderPanel.revalidate();
    }


    private void addActionToPanel(LslToolAction action) {
        var newTool = new JPanel(new BorderLayout());

        var labelName = new JLabel(action.name);
        labelName.setBorder(JBUI.Borders.empty(4));
        newTool.add(labelName, BorderLayout.LINE_START);

        var deleteActionButton = new InlineIconButton(LibSLIcon.DELETE, LibSLIcon.DELETE_HOVERED);
        deleteActionButton.setBorder(JBUI.Borders.empty(4));
        deleteActionButton.setActionListener(e -> {
            rowHolderPanel.remove(newTool);
            action.unregister();
            deleteAction(project, action);
            rowHolderPanel.repaint();
            rowHolderPanel.revalidate();
        });
        newTool.add(deleteActionButton, BorderLayout.LINE_END);

        var labelCmd = new JLabel(action.cmd + " " + action.input);
        labelCmd.setBorder(JBUI.Borders.empty(4));
        newTool.add(labelCmd, BorderLayout.AFTER_LAST_LINE);

        newTool.setBorder(BorderFactory.createLineBorder(JBColor.BLACK, 3));
        rowHolderPanel.add(newTool);
    }

}
