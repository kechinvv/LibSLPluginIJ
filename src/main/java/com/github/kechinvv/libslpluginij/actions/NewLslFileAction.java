package com.github.kechinvv.libslpluginij.actions;

import com.github.kechinvv.libslpluginij.language.LibSLIcon;
import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import static com.github.kechinvv.libslpluginij.LslNames.message;

public class NewLslFileAction extends CreateFileFromTemplateAction implements DumbAware {
    public static final String NEW_LSL_FILE = message("lsl.file.decl");

    public NewLslFileAction() {
        super(NEW_LSL_FILE, "", LibSLIcon.FILE);
    }

    @Override
    protected void buildDialog(@NotNull Project project, @NotNull PsiDirectory directory, CreateFileFromTemplateDialog.@NotNull Builder builder) {
        builder.setTitle(NEW_LSL_FILE)
                .addKind(message("lsl.file.template.empty"), LibSLIcon.FILE, message("lsl.file.template.empty.path"))
                .addKind(message("lsl.file.template.header"), LibSLIcon.FILE, message("lsl.file.template.header.path"))
                .addKind(message("lsl.file.template.automaton"), LibSLIcon.FILE, message("lsl.file.template.automaton.path"))
                .addKind(message("lsl.file.template.type"), LibSLIcon.FILE, message("lsl.file.template.type.path"));
    }

    @Override
    protected String getActionName(PsiDirectory directory, @NonNls @NotNull String newName, @NonNls String templateName) {
        return NEW_LSL_FILE;
    }


}
