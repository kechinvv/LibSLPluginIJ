package com.github.kechinvv.libslpluginij.language.psi;

import com.github.kechinvv.libslpluginij.language.LibSL;
import com.github.kechinvv.libslpluginij.language.LibSLFileType;
import com.github.kechinvv.libslpluginij.language.psi.rules.*;
import com.github.kechinvv.libslpluginij.language.utils.LslUtils;
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

    //TODO: refactoring!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    public Collection<LslIdentifier> getTypeDefBlockNames() {
        return findChildrenOfType(this, LslTypeDefBlock.class).stream()
                .map(lslTypeDefBlock -> findChildOfType(lslTypeDefBlock, LslPeriodSeparatedFullName.class))
                .map(fullNamePsi -> findChildOfType(fullNamePsi, LslIdentifier.class)).toList();
    }

    public Collection<LslIdentifier> getAutomatonNames() {
        return findChildrenOfType(this, LslAutomatonDecl.class).stream()
                .map(lslAutomatonDecl -> findChildOfType(lslAutomatonDecl, LslIdentifier.class)).toList();
    }

    public Collection<LslIdentifier> getTypeAliasesNames() {
        return findChildrenOfType(this, LslTypeAliasStatement.class).stream()
                .map(lslTypeAliasStatement -> findChildOfType(lslTypeAliasStatement, LslIdentifier.class)).toList();
    }


    //TODO: null safety
    public Collection<LslIdentifier> getActionsDeclarationsNames() {
        return findChildrenOfType(this, LslActionDecl.class).stream()
                .map(lslActionDecl -> (LslIdentifier) LslUtils.getFilteredSiblings(lslActionDecl.getFirstChild(),
                        it -> it instanceof LslIdentifier).get(0)).toList();
    }

}
