package com.github.kechinvv.libslpluginij.dialogs;

import com.github.kechinvv.libslpluginij.actions.LslToolAction;
import com.github.kechinvv.libslpluginij.language.LibSLIcon;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.Anchor;
import com.intellij.openapi.actionSystem.Constraints;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBScrollPane;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

import static com.github.kechinvv.libslpluginij.dialogs.LibSLToolsStore.getActions;
import static com.github.kechinvv.libslpluginij.LslNames.message;

public class DynamicToolsPanel extends DialogWrapper {

    private JTextField toolName;
    private JTextField cmd;
    private JTextField inputFlag;
    private final int defaultMainHeight = 500;
    private final int defaultMainWidth = 300;
    private final int defaultToolHeight = 50;
    private final int defaultToolWidth = defaultMainWidth - 40;
    private Project project;

    private JPanel rowHolderPanel = new JPanel(new GridLayout(0, 1, 1, 1));

    public DynamicToolsPanel(@Nullable Project project) {
        super(project);
        this.project = project;
        setTitle(message("lsl.configuration.title"));
        setSize(defaultMainWidth, defaultMainHeight);
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        var dialogPanel = new JPanel(new BorderLayout());

        var list = new JPanel(new BorderLayout());
        list.add(rowHolderPanel, BorderLayout.PAGE_START);

        var scrollable = new JBScrollPane(list);
        scrollable.setVerticalScrollBarPolicy(JBScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
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

        footer.add(nameLabel);
        footer.add(toolName);
        footer.add(cmdLabel);
        footer.add(cmd);
        footer.add(inputLabel);
        footer.add(inputFlag);
        footer.add(addButton);
        return footer;
    }

    private void addToolAction() {
        if (cmd.getText().isEmpty() || toolName.getText().isEmpty()) return;

        var toolNameCleared = toolName.getText().trim();
        var cmdCleared = cmd.getText().trim();
        var inputFlagCleared = cmd.getText().trim();

        var newTool = new JPanel(new BorderLayout());
        newTool.add(new JLabel(toolNameCleared), BorderLayout.WEST);
        newTool.add(new JLabel(cmdCleared + " " + inputFlagCleared), BorderLayout.WEST);

        newTool.setBorder(BorderFactory.createLineBorder(JBColor.BLACK, 3));

//        var action = createAction(toolName.getText(), cmd.getText(), inputFlag.getText());
//        saveAction(project, action);

        toolName.setText("");
        cmd.setText("");
        inputFlag.setText("");

        rowHolderPanel.add(newTool);
        rowHolderPanel.revalidate();
        rowHolderPanel.repaint();
    }


    public void saveAction(Project project, LslToolAction action) {
        if (action == null) return;
        HashMap<String, LslToolAction> actions = getActions(project);
        actions.put(action.toolName, action);
    }

    private LslToolAction createAction(String name, String cmd, String input) {
        var newAction = new LslToolAction(cmd, input) {
            @Override
            public String getActionId() {
                return name;
            }
        };
        newAction.getTemplatePresentation().setIcon(LibSLIcon.FILE);
        newAction.getTemplatePresentation().setText("TEST ACTION");
        DefaultActionGroup mainMenu = (DefaultActionGroup) ActionManager.getInstance().getAction("ProjectViewPopupMenu");
        mainMenu.add(newAction, new Constraints(Anchor.BEFORE, "com.intellij.tools.ExternalToolsGroup"));
        newAction.register();
        return newAction;
    }

}
