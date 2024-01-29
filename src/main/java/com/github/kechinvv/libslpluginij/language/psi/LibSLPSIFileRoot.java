package com.github.kechinvv.libslpluginij.language.psi;

import com.github.kechinvv.libslpluginij.language.LibSL;
import com.github.kechinvv.libslpluginij.language.LibSLFileType;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.*;

public class LibSLPSIFileRoot extends PsiFileBase {

    public LibSLPSIFileRoot(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, LibSL.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return LibSLFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "LibSL File";
    }

}
