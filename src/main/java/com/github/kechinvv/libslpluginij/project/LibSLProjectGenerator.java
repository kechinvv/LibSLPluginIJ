package com.github.kechinvv.libslpluginij.project;

import com.github.kechinvv.libslpluginij.language.LibSLIcon;
import com.intellij.facet.ui.ValidationResult;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.platform.DirectoryProjectGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class LibSLProjectGenerator implements DirectoryProjectGenerator {
    @Override
    public @NotNull @NlsContexts.Label String getName() {
        return "LibSL";
    }

    @Override
    public @Nullable Icon getLogo() {
        return LibSLIcon.FILE;
    }

    @Override
    public void generateProject(@NotNull Project project, @NotNull VirtualFile virtualFile, @NotNull Object o, @NotNull Module module) {

    }

    @Override
    public @NotNull ValidationResult validate(@NotNull String s) {
        return ValidationResult.OK;
    }
}
