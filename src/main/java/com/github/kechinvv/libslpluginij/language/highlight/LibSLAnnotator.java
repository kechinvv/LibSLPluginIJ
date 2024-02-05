package com.github.kechinvv.libslpluginij.language.highlight;

import com.github.kechinvv.libslpluginij.antlr.LibSLLexer;
import com.github.kechinvv.libslpluginij.language.psi.rules.LslAnnotationArgs;
import com.github.kechinvv.libslpluginij.language.psi.rules.LslAnnotationUsage;
import com.github.kechinvv.libslpluginij.language.utils.LslUtils;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;


final class LibSLAnnotator implements Annotator {

    @Override
    public void annotate(PsiElement element, AnnotationHolder holder) {
        if (element instanceof LslAnnotationUsage)
            LslUtils.getFilteredSiblings(element.getFirstChild(), it -> !(it instanceof LslAnnotationArgs))
                    .forEach(it -> highlight(it, holder));

    }


    public void highlight(PsiElement element, AnnotationHolder holder) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element).textAttributes(DefaultLanguageHighlighterColors.METADATA).create();
    }


}
