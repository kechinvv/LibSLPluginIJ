package com.github.kechinvv.libslpluginij.language.formatter;

import com.github.kechinvv.libslpluginij.antlr.LibSLLexer;
import com.github.kechinvv.libslpluginij.antlr.LibSLParser;
import com.github.kechinvv.libslpluginij.language.LibSL;
import com.intellij.formatting.*;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;

import static com.github.kechinvv.libslpluginij.language.LibSLParserDefinition.rules;
import static com.github.kechinvv.libslpluginij.language.LibSLParserDefinition.tokens;

final class LibSLFormatter implements FormattingModelBuilder {

    private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {
        return new SpacingBuilder(settings, LibSL.INSTANCE)
                .around(tokens.get(LibSLLexer.ASSIGN_OP))
                .spaceIf(settings.getCommonSettings(LibSL.INSTANCE.getID()).SPACE_AROUND_ASSIGNMENT_OPERATORS)
                .before(tokens.get(LibSLLexer.Identifier))
                .none();
    }

    @Override
    public @NotNull FormattingModel createModel(@NotNull FormattingContext formattingContext) {
        final CodeStyleSettings codeStyleSettings = formattingContext.getCodeStyleSettings();
        return FormattingModelProvider
                .createFormattingModelForPsiFile(formattingContext.getContainingFile(),
                        new LibSLBlock(formattingContext.getNode(),
                                null, null),
                        codeStyleSettings);
    }

}