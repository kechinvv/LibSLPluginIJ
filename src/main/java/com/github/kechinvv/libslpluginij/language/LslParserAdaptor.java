package com.github.kechinvv.libslpluginij.language;

import com.github.kechinvv.libslpluginij.antlr.LibSLParser;
import com.intellij.lang.Language;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import org.antlr.intellij.adaptor.parser.ANTLRParserAdaptor;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;

public class LslParserAdaptor extends ANTLRParserAdaptor {
    public LslParserAdaptor(Language language, Parser parser) {
        super(language, parser);
    }

    @Override
    protected ParseTree parse(Parser parser, IElementType root) {
        if (root instanceof IFileElementType) {
            return ((LibSLParser) parser).file();
        } else throw new UnsupportedOperationException("Wrong Lsl file structure");
    }
}
