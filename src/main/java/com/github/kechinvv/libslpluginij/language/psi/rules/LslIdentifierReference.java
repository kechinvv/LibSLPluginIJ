package com.github.kechinvv.libslpluginij.language.psi.rules;

import com.github.kechinvv.libslpluginij.language.psi.LibSLPSIFileRoot;
import com.intellij.psi.*;
import com.intellij.util.Consumer;
import com.intellij.util.SmartList;
import com.intellij.util.containers.SmartHashSet;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LslIdentifierReference extends PsiPolyVariantReferenceBase<LslIdentifier> {
    public LslIdentifierReference(@NotNull PsiElement psiElement) {
        super((LslIdentifier) psiElement);
    }

    @Override
    public ResolveResult @NotNull [] multiResolve(boolean incompleteCode) {
        var declarations = new SmartList<PsiElement>();
        var name = myElement.getName();
//        processDeclarations {
//            if (it.name == name) declarations.add(it)
//        }
        return PsiElementResolveResult.createResults(declarations);
    }

    @Override
    public Object @NotNull [] getVariants() {
        var variants = new SmartHashSet<String>();
        Consumer<PsiNamedElement> adder = it -> {
            var name = it.getName();
            if (name != null) variants.add(name);
        };
        processDeclarations(adder);
        return variants.toArray();
    }

    @Override
    public PsiElement handleElementRename(@NotNull String newElementName) {
        return myElement.setName(newElementName);
    }


    private void processDeclarations(Consumer<PsiNamedElement> callback) {
        //PsiElement prev = myElement;
        var current = myElement.getParent();
        while (current != null) {
            processDeclarationsIteration(current, callback);
            //prev = current;
            current = current.getParent();
        }
    }

    private void processDeclarationsIteration(PsiElement current, Consumer<PsiNamedElement> callback) {

    }

}
