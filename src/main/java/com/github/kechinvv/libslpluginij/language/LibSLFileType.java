package com.github.kechinvv.libslpluginij.language;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public final class LibSLFileType extends LanguageFileType {

    public static final LibSLFileType INSTANCE = new LibSLFileType();

    private LibSLFileType() {
        super(LibSL.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "LibSL File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Library Specification language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "lsl";
    }

    @Override
    public Icon getIcon() {
        return LibSLIcon.FILE;
    }

}
