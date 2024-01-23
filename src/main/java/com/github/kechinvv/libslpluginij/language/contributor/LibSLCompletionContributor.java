package com.github.kechinvv.libslpluginij.language.contributor;

import com.github.kechinvv.libslpluginij.antlr.LibSLLexer;
import com.github.kechinvv.libslpluginij.antlr.LibSLParser;
import com.github.kechinvv.libslpluginij.language.LibSL;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import static com.github.kechinvv.libslpluginij.language.LibSLParserDefinition.rules;
import static com.github.kechinvv.libslpluginij.language.LibSLParserDefinition.tokens;

final class LibSLCompletionContributor extends CompletionContributor {
    public static final Logger LOG = Logger.getInstance("ConfigureANTLRCompletion");
    LibSLCompletionContributor() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(),
                new CompletionProvider<>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        LOG.info("try to complete " + parameters);
                        resultSet.addElement(LookupElementBuilder.create("Hello"));
                    }
                }
        );
    }


}
