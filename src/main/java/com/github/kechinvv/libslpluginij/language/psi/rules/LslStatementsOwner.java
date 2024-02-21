package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;

import java.util.List;

public interface LslStatementsOwner extends PsiElement {

    public default List<LslStatement> getStatements() {
        return PsiTreeUtil.getChildrenOfTypeAsList(this, LslStatement.class);
    }
}

