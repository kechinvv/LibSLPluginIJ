package com.github.kechinvv.libslpluginij.language.contributor;

import com.github.kechinvv.libslpluginij.antlr.LibSLLexer;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import static com.github.kechinvv.libslpluginij.language.LibSLParserDefinition.tokens;

final class LibSLCompletionContributor extends CompletionContributor {

    LibSLCompletionContributor() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(tokens.get(LibSLLexer.IDENTIFIER)),
                new CompletionProvider<>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create("Hello"));
                    }
                }
        );
    }


}
