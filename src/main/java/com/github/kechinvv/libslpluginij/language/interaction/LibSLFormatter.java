package com.github.kechinvv.libslpluginij.language.interaction;

import com.github.kechinvv.libslpluginij.antlr.LibSLParser;
import com.github.kechinvv.libslpluginij.language.LibSL;
import com.intellij.formatting.*;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;

import static com.github.kechinvv.libslpluginij.language.LibSLParserDefinition.rules;

final class LibSLFormatter implements FormattingModelBuilder {

    private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {
        return new SpacingBuilder(settings, LibSL.INSTANCE)
                .around(rules.get(LibSLParser.RULE_automatonStatement))
                .none();
    }

    @Override
    public @NotNull FormattingModel createModel(@NotNull FormattingContext formattingContext) {
        final CodeStyleSettings codeStyleSettings = formattingContext.getCodeStyleSettings();
        return FormattingModelProvider
                .createFormattingModelForPsiFile(formattingContext.getContainingFile(),
                        new LibSLBlock(formattingContext.getNode(),
                                null, null, createSpaceBuilder(codeStyleSettings)),
                        codeStyleSettings);
    }

}