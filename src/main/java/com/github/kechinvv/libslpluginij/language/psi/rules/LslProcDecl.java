package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.github.kechinvv.libslpluginij.language.LibSL;
import com.github.kechinvv.libslpluginij.language.psi.rules.interfaces.LslCallable;
import com.github.kechinvv.libslpluginij.language.psi.rules.interfaces.LslHeader;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import org.antlr.intellij.adaptor.SymtabUtils;
import org.antlr.intellij.adaptor.psi.IdentifierDefSubtree;
import org.antlr.intellij.adaptor.psi.ScopeNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class LslProcDecl extends LslIdentifierOwner implements LslCallable {
    public LslProcDecl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public LslProcHeader header() {
        return PsiTreeUtil.findChildOfType(this, LslProcHeader.class);
    }

    public LslFunctionBody body() {
        return PsiTreeUtil.findChildOfType(this, LslFunctionBody.class);
    }
}
