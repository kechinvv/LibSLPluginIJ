package com.github.kechinvv.libslpluginij.language.interaction;

import com.github.kechinvv.libslpluginij.language.psi.LibSLPSIFileRoot;
import com.github.kechinvv.libslpluginij.language.psi.LibSLTokenSets;
import com.github.kechinvv.libslpluginij.language.psi.rules.LslElseStatement;
import com.github.kechinvv.libslpluginij.language.psi.rules.LslTargetType;
import com.github.kechinvv.libslpluginij.language.psi.rules.LslTopLevelDecl;
import com.github.kechinvv.libslpluginij.language.psi.rules.interfaces.LslParametersOwner;
import com.github.kechinvv.libslpluginij.language.psi.rules.interfaces.LslStatement;
import com.github.kechinvv.libslpluginij.language.psi.rules.interfaces.LslStatementsOwner;
import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.github.kechinvv.libslpluginij.language.utils.LslUtils.getPrevSiblingSkipSpacesAndComments;

public class LibSLBlock extends AbstractBlock {

    private final SpacingBuilder spacingBuilder;

    protected LibSLBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment, SpacingBuilder spacingBuilder) {
        super(node, wrap, alignment);
        this.spacingBuilder = spacingBuilder;
    }

    @Override
    protected List<Block> buildChildren() {
        List<Block> blocks = new ArrayList<>();
        ASTNode child = myNode.getFirstChildNode();
        while (child != null) {
            if (child.getElementType() != TokenType.WHITE_SPACE) {
                Block block = new LibSLBlock(child, null, null, spacingBuilder);
                blocks.add(block);
            }
            child = child.getTreeNext();
        }
        return blocks;
    }

    @Override
    public Indent getIndent() {
        var elementType = myNode.getElementType();
        var element = myNode.getPsi();
        var parentElement = element.getParent();

        //No tabulation for highest statements
        if (parentElement instanceof LibSLPSIFileRoot ||
                parentElement instanceof LslTopLevelDecl)
            return Indent.getNoneIndent();

        //Tabulation for return type in signatures (if it uses few lines)
        if (getPrevSiblingSkipSpacesAndComments(element) instanceof LslParametersOwner)
            return Indent.getNormalIndent();

        //No tab relative to if for else
        if (element instanceof LslElseStatement)
            return Indent.getNoneIndent();

        //Tabulation for params in proc/fun/annotation/action signatures
        if (parentElement instanceof LslParametersOwner) {
            var previewSib = element.getPrevSibling();
            var maybeSpace = parentElement.getPrevSibling();
            if (previewSib == null || maybeSpace instanceof PsiWhiteSpace)
                return Indent.getContinuationIndent();

            var previewSibParent = parentElement.getPrevSibling();

            int spaces = 0;
            while (previewSibParent != null) {
                spaces += previewSibParent.getTextLength();
                previewSibParent = previewSibParent.getPrevSibling();
            }
            return Indent.getSpaceIndent(spaces);
        }

        //Tabulation for statements and comments in body
        if (parentElement instanceof LslStatementsOwner &&
                (element instanceof LslStatement || LibSLTokenSets.INSTANCE.COMMENTS.contains(elementType)))
            return Indent.getNormalIndent();

        //Tabulation for libsl version declaration, links e.t.c.
        if (LibSLTokenSets.INSTANCE.TAB_HEADER.contains(elementType) ||
                element instanceof LslTargetType)
            return Indent.getNormalIndent();

        return Indent.getNoneIndent();
    }


    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        return spacingBuilder.getSpacing(this, child1, child2);
    }

    @Override
    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }


}