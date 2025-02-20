package com.github.kechinvv.libslpluginij.language;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

import static com.github.kechinvv.libslpluginij.LslNames.message;

public final class LibSLFileType extends LanguageFileType {

    public static final LibSLFileType INSTANCE = new LibSLFileType();

    private LibSLFileType() {
        super(LibSL.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return message("lsl.file.decl");
    }

    @NotNull
    @Override
    public String getDescription() {
        return message("lsl.file.descr");
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return message("lsl.file.ext");
    }

    @Override
    public Icon getIcon() {
        return LibSLIcon.FILE;
    }

}
