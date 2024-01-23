package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.github.kechinvv.libslpluginij.language.LibSL;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.SymtabUtils;
import org.antlr.intellij.adaptor.psi.IdentifierDefSubtree;
import org.antlr.intellij.adaptor.psi.ScopeNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ProcDecl extends IdentifierDefSubtree implements ScopeNode {
    public ProcDecl(@NotNull ASTNode node, @NotNull IElementType idElementType) {
        super(node, idElementType);
    }

    @Nullable
    @Override
    public PsiElement resolve(PsiNamedElement element) {
        return SymtabUtils.resolve(this, LibSL.INSTANCE,
                element, "/lsl/proc/ID");
    }
}
