package com.github.kechinvv.libslpluginij.language.highlight;

import com.github.kechinvv.libslpluginij.antlr.LibSLLexer;
import com.github.kechinvv.libslpluginij.language.psi.rules.LslAnnotationArgs;
import com.github.kechinvv.libslpluginij.language.psi.rules.LslAnnotationUsage;
import com.github.kechinvv.libslpluginij.language.utils.LslPsiUtils;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.jetbrains.annotations.NotNull;


final class LibSLAnnotator implements Annotator {
    public static final Logger LOG = Logger.getInstance("Annotator");

    private final TextRange importRange = new TextRange(0, 6);
    private final TextRange includeRange = new TextRange(0, 7);

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof LslAnnotationUsage)
            LslPsiUtils.getFilteredSiblings(element.getFirstChild(), it -> !(it instanceof LslAnnotationArgs))
                    .forEach(it -> highlightRule(it, holder));
        else if (element.getNode().getElementType() instanceof TokenIElementType tokenIElementType)
            switch (tokenIElementType.getANTLRTokenType()) {
                case LibSLLexer.ImportStatement -> highlightTextRange(element, holder, importRange);
                case LibSLLexer.IncludeStatement -> highlightTextRange(element, holder, includeRange);
            }
    }


    public void highlightRule(PsiElement element, AnnotationHolder holder) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element)
                .textAttributes(DefaultLanguageHighlighterColors.METADATA)
                .create();
    }

    public void highlightTextRange(PsiElement element, AnnotationHolder holder, TextRange subRange) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element.getTextRange().cutOut(subRange))
                .textAttributes(DefaultLanguageHighlighterColors.KEYWORD)
                .create();
    }
}
