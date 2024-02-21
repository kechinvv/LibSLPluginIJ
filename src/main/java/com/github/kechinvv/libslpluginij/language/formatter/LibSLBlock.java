package com.github.kechinvv.libslpluginij.language.formatter;

import com.github.kechinvv.libslpluginij.language.psi.LibSLPSIFileRoot;
import com.github.kechinvv.libslpluginij.language.psi.LibSLTokenSets;
import com.github.kechinvv.libslpluginij.language.psi.rules.LslStatement;
import com.github.kechinvv.libslpluginij.language.psi.rules.LslStatementsOwner;
import com.github.kechinvv.libslpluginij.language.psi.rules.LslTopLevelDecl;
import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LibSLBlock extends AbstractBlock {


    protected LibSLBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment) {
        super(node, wrap, alignment);
    }

    @Override
    protected List<Block> buildChildren() {
        List<Block> blocks = new ArrayList<>();
        ASTNode child = myNode.getFirstChildNode();
        while (child != null) {
            var skip = child.getTextRange().getLength() == 0 || child.getElementType() == TokenType.WHITE_SPACE;

            if (child.getElementType() != TokenType.WHITE_SPACE) {
                Block block = new LibSLBlock(child, null, null);
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

        if (parentElement instanceof LibSLPSIFileRoot ||
                parentElement instanceof LslTopLevelDecl)
            return Indent.getNoneIndent();

        if (LibSLTokenSets.INSTANCE.BRACES.contains(elementType))
            return Indent.getNoneIndent();

        if (parentElement instanceof LslStatementsOwner && element instanceof LslStatement)
            return Indent.getNormalIndent();

        return Indent.getNoneIndent();
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
//        return spacingBuilder.getSpacing(this, child1, child2);
        return null;
    }

    @Override
    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }

}