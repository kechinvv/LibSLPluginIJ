package com.github.kechinvv.libslpluginij.language.interaction;

import com.github.kechinvv.libslpluginij.language.psi.rules.LslArrayLiteral;
import com.github.kechinvv.libslpluginij.language.psi.rules.LslFunctionBody;
import com.github.kechinvv.libslpluginij.language.psi.rules.LslFunctionsList;
import com.github.kechinvv.libslpluginij.language.psi.rules.LslIdentifierList;
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
            Pair<TextRange, String> textRangeAndPlaceholderText;
            if (it instanceof LslFunctionBody || it instanceof LslIdentifierList || it instanceof LslFunctionsList)
                textRangeAndPlaceholderText = new Pair<>(it.getTextRange(), "...");
            else if (it instanceof PsiComment)
                textRangeAndPlaceholderText = new Pair<>(it.getTextRange(), "/*...*/");
            else if (it instanceof LslArrayLiteral)
                textRangeAndPlaceholderText = new Pair<>(it.getTextRange(), "[...]");
            else textRangeAndPlaceholderText = null;

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

    private boolean isOnMultipleLines(TextRange textRange, Document document) {
        var startLine = document.getLineNumber(textRange.getStartOffset());
        var endLine = document.getLineNumber(textRange.getEndOffset());
        return startLine != endLine;
    }


//    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
//        val descriptors = mutableListOf<FoldingDescriptor>()
//        PsiTreeUtil.processElements(root) {
//            val textRangeAndPlaceholderText = when (it) {
//                is JavaScriptFunctionBody, is JavaScriptBlock, is JavaScriptObject, is JavaScriptCaseBlock -> Pair(it.textRange, "{...}")
//                is JavaScriptArray -> Pair(it.textRange, "[...]")
//                is JavaScriptClassDeclaration -> {
//                    val openBraceOffset = it.node.findChildByType(JavaScriptTypes.OPEN_BRACE)?.startOffset
//                    if (openBraceOffset == null) null
//                    else Pair(TextRange(openBraceOffset, it.textRange.endOffset), "{...}")
//                }
//                is PsiComment -> {
//                    if (it.node.elementType == JavaScriptTypes.MULTILINE_COMMENT) Pair(it.textRange, "/*...*/")
//                    else null
//                }
//                else -> null
//            }
//            if (textRangeAndPlaceholderText != null) {
//                val textRange = textRangeAndPlaceholderText.first
//                val placeholderText = textRangeAndPlaceholderText.second
//                if (isOnMultipleLines(textRange, document)) {
//                    descriptors.add(FoldingDescriptor(it.node, textRange, null, placeholderText))
//                }
//            }
//            return@processElements true
//        }
//        return descriptors.toTypedArray()
//    }


    @Override
    public @Nullable String getPlaceholderText(@NotNull ASTNode astNode) {
        return null;
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode astNode) {
        return false;
    }
}
