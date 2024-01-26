package com.github.kechinvv.libslpluginij.language.contributor;

import com.github.kechinvv.libslpluginij.antlr.LibSLParser;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiErrorElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import static com.github.kechinvv.libslpluginij.language.LibSLParserDefinition.tokens;

final class LibSLCompletionContributor extends CompletionContributor {
    public static final Logger LOG = Logger.getInstance("ConfigureANTLRCompletion");

    LibSLCompletionContributor() {
        var testProvider = new CompletionProvider<>() {
            public void addCompletions(@NotNull CompletionParameters parameters,
                                       @NotNull ProcessingContext context,
                                       @NotNull CompletionResultSet resultSet) {
                LOG.info("try to complete " + parameters);
                resultSet.addElement(LookupElementBuilder.create("hello"));
                resultSet.addElement(LookupElementBuilder.create("privet"));
            }
        };
        var testPlace = PlatformPatterns.psiElement(tokens.get(LibSLParser.Identifier)).withParent(PsiErrorElement.class);

        extend(CompletionType.BASIC, testPlace, testProvider);
    }


}
