package com.github.kechinvv.libslpluginij.language.psi;

import com.github.kechinvv.libslpluginij.antlr.LibSLLexer;
import com.github.kechinvv.libslpluginij.language.LibSL;
import com.github.kechinvv.libslpluginij.language.LibSLFileType;
import com.github.kechinvv.libslpluginij.language.psi.rules.*;
import com.github.kechinvv.libslpluginij.language.utils.LslUtils;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.TokenType;
import com.intellij.psi.util.PsiTreeUtil;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.jetbrains.annotations.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.github.kechinvv.libslpluginij.language.LibSLParserDefinition.tokens;
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
                .map(lslAutomatonDecl -> findChildOfType(lslAutomatonDecl, LslIdentifier.class)).toList();
    }

    public Collection<LslIdentifier> getTypeAliasesNames() {
        return findChildrenOfType(this, LslTypeAliasStatement.class).stream()
                .map(lslTypeAliasStatement -> findChildOfType(lslTypeAliasStatement, LslIdentifier.class)).toList();
    }


    public Collection<LslIdentifier> getActionsDeclarationsNames() {
        return findChildrenOfType(this, LslActionDecl.class).stream()
                .map(lslActionDecl -> {
                    var listIds = LslUtils.getFilteredSiblings(lslActionDecl.getFirstChild(),
                            it -> it instanceof LslIdentifier);
                    if (!listIds.isEmpty()) return (LslIdentifier) listIds.get(0);
                    else return null;
                }).filter(Objects::nonNull).toList();
    }

    public Collection<LslIdentifier> getAnnotationsDeclarationsNames() {
        return findChildrenOfType(this, LslAnnotationDecl.class).stream()
                .map(lslAnnotationDecl -> {
                    var listIds = LslUtils.getFilteredSiblings(lslAnnotationDecl.getFirstChild(),
                            it -> it instanceof LslIdentifier);
                    if (!listIds.isEmpty()) return (LslIdentifier) listIds.get(0);
                    else return null;
                }).filter(Objects::nonNull).toList();
    }

    public Collection<PsiElement> getImports() {
        return findChildrenOfType(this, LslGlobalStatement.class).stream()
                .map(PsiElement::getFirstChild)
                .filter(it -> it.getNode().getElementType() instanceof TokenIElementType tok
                        && tok.getANTLRTokenType() == LibSLLexer.ImportStatement)
                .toList();
    }

    public Map<String, String> getImportsPathsMap() {
        var importsMap = new HashMap<String, String>();
        var imports = getImports();
        imports.forEach(it -> {
            var paths = it.getText().split("[ ;]");
            if (paths.length < 2) return;
            var path = paths[1];
            var folders = path.split("/");
            if (folders.length == 0) return;
            var fileName = folders[folders.length - 1];
            importsMap.put(fileName, path);
        });
        return importsMap;
    }

}
