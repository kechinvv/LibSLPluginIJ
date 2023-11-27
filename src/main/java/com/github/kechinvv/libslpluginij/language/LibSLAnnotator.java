package com.github.kechinvv.libslpluginij.language;

import com.github.kechinvv.libslpluginij.antlr.LibSLLexer;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;


final class LibSLAnnotator implements Annotator {

    @Override
    public void annotate(PsiElement element, AnnotationHolder holder) {
        PsiElement nextElement = element.getNextSibling();
        PsiElement prevElement = element.getPrevSibling();
        if (nextElement == null && prevElement == null) return;
        else if (nextElement == null) annotateNode(prevElement, element, holder, true);
        else if (prevElement == null) annotateNode(element, nextElement, holder, false);
        else {
            annotateNode(prevElement, element, holder, true);
            annotateNode(element, nextElement, holder, false);
        }
    }

    public void annotateNode(PsiElement at, PsiElement id, AnnotationHolder holder, Boolean isId) {
        IElementType nextElementType = id.getNode().getElementType();
        IElementType elementType = at.getNode().getElementType();

        if (!(nextElementType instanceof TokenIElementType) || !(elementType instanceof TokenIElementType)) return;
        if (((TokenIElementType) elementType).getANTLRTokenType() != LibSLLexer.AT
                || ((TokenIElementType) nextElementType).getANTLRTokenType() != LibSLLexer.IDENTIFIER) return;

        if (isId) highlight(id, holder);
        else highlight(at, holder);
    }

    public void highlight(PsiElement element,  AnnotationHolder holder) {
        TextRange elRange = TextRange.from(element.getTextRange().getStartOffset(), element.getTextLength());
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(elRange).textAttributes(DefaultLanguageHighlighterColors.METADATA).create();
    }

//    public void highlightTagName(PsiElement id, AnnotationHolder holder) {
//        PsiElement tag = PsiTreeUtil.prevVisibleLeaf(id);
//        if (tag != null && tag.getNode().getElementType() == tokens.get(LibSLLexer.AT)) {
//            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
//                    .range(id)
//                    .textAttributes(LibSLSyntaxHighlighter.KEYWORD)
//                    .create();
//        }
//    }

}
