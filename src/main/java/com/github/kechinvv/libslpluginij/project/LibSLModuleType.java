package com.github.kechinvv.libslpluginij.project;

import com.github.kechinvv.libslpluginij.language.LibSLIcon;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

import static com.github.kechinvv.libslpluginij.LslNames.message;

public class LibSLModuleType extends ModuleType<LibSLModuleBuilder> {
    protected LibSLModuleType() {
        super(message("lsl.module"));
    }

    @NotNull
    public static LibSLModuleType getInstance() {
        return (LibSLModuleType) ModuleTypeManager.getInstance().findByID(message("lsl.module"));
    }

    @Override
    public @NotNull LibSLModuleBuilder createModuleBuilder() {
        return new LibSLModuleBuilder();
    }

    @Override
    public @NotNull @Nls(capitalization = Nls.Capitalization.Title) String getName() {
        return message("lsl.main.name");
    }

    @Override
    public @NotNull @Nls(capitalization = Nls.Capitalization.Sentence) String getDescription() {
        return message("lsl.module.type.description");
    }

    @Override
    public @NotNull Icon getNodeIcon(boolean b) {
        return LibSLIcon.PROJECT;
    }


}
