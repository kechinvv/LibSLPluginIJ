package com.github.kechinvv.libslpluginij.project;

import com.intellij.compiler.CompilerWorkspaceConfiguration;
import com.intellij.ide.util.projectWizard.JavaModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleBuilderListener;
import com.intellij.ide.util.projectWizard.SourcePathsBuilder;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import org.jetbrains.annotations.NotNull;

public class LibSLModuleBuilder extends JavaModuleBuilder implements SourcePathsBuilder, ModuleBuilderListener {
    @Override
    public void moduleCreated(@NotNull Module module) {
        CompilerWorkspaceConfiguration.getInstance(module.getProject()).CLEAR_OUTPUT_DIRECTORY = false;
    }


    @NotNull
    @Override
    public ModuleType getModuleType() {
        return LibSLModuleType.getInstance();
    }
}
