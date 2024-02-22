package com.github.kechinvv.libslpluginij.language.formatter;

import com.github.kechinvv.libslpluginij.language.psi.LibSLPSIFileRoot;
import com.github.kechinvv.libslpluginij.language.psi.LibSLTokenSets;
import com.github.kechinvv.libslpluginij.language.psi.rules.*;
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

        if (LibSLTokenSets.INSTANCE.BRACES.contains(elementType) ||
                element instanceof LslElseStatement)
            return Indent.getNoneIndent();

        if (parentElement instanceof LslStatementsOwner &&
                (element instanceof LslStatement || LibSLTokenSets.INSTANCE.COMMENTS.contains(elementType)))
            return Indent.getNormalIndent();

        if (LibSLTokenSets.INSTANCE.TAB_HEADER.contains(elementType) ||
                element instanceof LslTargetType)
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

//    @Override
//    public Wrap getWrap() {
//        var element = myNode.getPsi();
//
//        if (element instanceof LslProcDecl || element instanceof LslFunctionDecl)
//            return Wrap.createWrap(WrapType.NORMAL, true);
//
//        return super.getWrap();
//    }

}