package com.github.kechinvv.libslpluginij.language.psi;

import com.github.kechinvv.libslpluginij.language.LibSL;
import com.github.kechinvv.libslpluginij.language.LibSLFileType;
import com.github.kechinvv.libslpluginij.language.psi.rules.LslAutomatonDecl;
import com.github.kechinvv.libslpluginij.language.psi.rules.LslIdentifier;
import com.github.kechinvv.libslpluginij.language.psi.rules.LslPeriodSeparatedFullName;
import com.github.kechinvv.libslpluginij.language.psi.rules.LslTypeDefBlock;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.*;

import java.util.Collection;

import static com.intellij.psi.util.PsiTreeUtil.*;

public class LibSLPSIFileRoot extends PsiFileBase implements PsiElement {

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

    public Collection<LslIdentifier> getTypeDefBlockNames() {
        return findChildrenOfType(this, LslTypeDefBlock.class).stream()
                .map(lslTypeDefBlock -> findChildOfType(lslTypeDefBlock, LslPeriodSeparatedFullName.class))
                .map(fullNamePsi -> findChildOfType(fullNamePsi, LslIdentifier.class)).toList();
    }

    public Collection<LslIdentifier> getAutomatonNames() {
        return findChildrenOfType(this, LslAutomatonDecl.class).stream()
                .map(lslTypeDefBlock -> findChildOfType(lslTypeDefBlock, LslIdentifier.class)).toList();
    }

}
