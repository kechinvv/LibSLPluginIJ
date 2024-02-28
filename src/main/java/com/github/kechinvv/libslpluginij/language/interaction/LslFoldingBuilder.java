package com.github.kechinvv.libslpluginij.language.interaction;

import com.github.kechinvv.libslpluginij.language.psi.rules.*;
import com.github.kechinvv.libslpluginij.language.utils.Pair;
import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.PsiElementProcessor;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;

public class LslFoldingBuilder extends FoldingBuilderEx {
    @Override
    public FoldingDescriptor @NotNull [] buildFoldRegions(@NotNull PsiElement psiElement, @NotNull Document document, boolean b) {
        var descriptors = new HashSet<FoldingDescriptor>();
        PsiElementProcessor<PsiElement> processor = it -> {
            Pair<TextRange, String> textRangeAndPlaceholderText = getTextRangeStringPair(it);

            if (textRangeAndPlaceholderText != null) {
                var textRange = textRangeAndPlaceholderText.first;
                var placeholderText = textRangeAndPlaceholderText.second;
                if (isOnMultipleLines(textRange, document)) {
                    descriptors.add(new FoldingDescriptor(psiElement.getNode(), textRange, null, placeholderText));
                }
            }
            return true;
        };
        PsiTreeUtil.processElements(processor, psiElement);
        return descriptors.toArray(new FoldingDescriptor[0]);
    }

    @Nullable
    private Pair<TextRange, String> getTextRangeStringPair(PsiElement it) {
        Pair<TextRange, String> textRangeAndPlaceholderText;
        if (it instanceof LslFunctionBody || it instanceof LslConditionBody ||
                it instanceof LslTypeDefBlockBody || it instanceof LslAutomatonBody)
            textRangeAndPlaceholderText = new Pair<>(it.getTextRange(), "{...}");
        else if (it instanceof LslShiftFromList)
            textRangeAndPlaceholderText = new Pair<>(it.getTextRange(), "(...)");
        else if (it instanceof PsiComment)
            textRangeAndPlaceholderText = new Pair<>(it.getTextRange(), "/*...*/");
        else if (it instanceof LslArrayLiteral || it instanceof LslShiftByList)
            textRangeAndPlaceholderText = new Pair<>(it.getTextRange(), "[...]");
        else textRangeAndPlaceholderText = null;
        return textRangeAndPlaceholderText;
    }

    private boolean isOnMultipleLines(TextRange textRange, Document document) {
        var startLine = document.getLineNumber(textRange.getStartOffset());
        var endLine = document.getLineNumber(textRange.getEndOffset());
        return startLine != endLine;
    }


    @Override
    public @Nullable String getPlaceholderText(@NotNull ASTNode astNode) {
        return null;
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode astNode) {
        return false;
    }
}
