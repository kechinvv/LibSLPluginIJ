package com.github.kechinvv.libslpluginij.actions;

import com.github.kechinvv.libslpluginij.language.LibSLIcon;
import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class NewLslFileAction extends CreateFileFromTemplateAction implements DumbAware {
    public static final String NEW_LSL_FILE = "LibSL File";
    public NewLslFileAction() {
        super(NEW_LSL_FILE, "", LibSLIcon.FILE);
    }

    @Override
    protected void buildDialog(@NotNull Project project, @NotNull PsiDirectory directory, CreateFileFromTemplateDialog.@NotNull Builder builder) {
        builder.setTitle(NEW_LSL_FILE)
                .addKind("Empty", LibSLIcon.FILE, "Empty.lsl")
                .addKind("Header", LibSLIcon.FILE, "Header.lsl")
                .addKind("Automaton", LibSLIcon.FILE, "Automaton.lsl")
                .addKind("Type", LibSLIcon.FILE, "Type.lsl");
    }

    @Override
    protected String getActionName(PsiDirectory directory, @NonNls @NotNull String newName, @NonNls String templateName) {
        return NEW_LSL_FILE;
    }


}
