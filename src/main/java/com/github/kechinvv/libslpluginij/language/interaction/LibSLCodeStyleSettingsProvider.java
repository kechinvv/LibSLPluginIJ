package com.github.kechinvv.libslpluginij.language.interaction;

import com.github.kechinvv.libslpluginij.language.LibSL;
import com.intellij.application.options.CodeStyleAbstractConfigurable;
import com.intellij.application.options.CodeStyleAbstractPanel;
import com.intellij.application.options.TabbedLanguageCodeStylePanel;
import com.intellij.psi.codeStyle.CodeStyleConfigurable;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsProvider;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;
import org.jetbrains.annotations.NotNull;

final class LibSLCodeStyleSettingsProvider extends CodeStyleSettingsProvider {

    @Override
    public CustomCodeStyleSettings createCustomSettings(@NotNull CodeStyleSettings settings) {
        return new LibSLCodeStyleSettings(settings);
    }

    @Override
    public @NotNull String getConfigurableDisplayName() {
        return "LibSL";
    }

    @NotNull
    public CodeStyleConfigurable createConfigurable(@NotNull CodeStyleSettings settings,
                                                    @NotNull CodeStyleSettings modelSettings) {
        return new CodeStyleAbstractConfigurable(settings, modelSettings, this.getConfigurableDisplayName()) {
            @Override
            protected @NotNull CodeStyleAbstractPanel createPanel(@NotNull CodeStyleSettings settings) {
                return new LibSLCodeStyleMainPanel(getCurrentSettings(), settings);
            }
        };
    }

    private static class LibSLCodeStyleMainPanel extends TabbedLanguageCodeStylePanel {

        public LibSLCodeStyleMainPanel(CodeStyleSettings currentSettings, CodeStyleSettings settings) {
            super(LibSL.INSTANCE, currentSettings, settings);
        }

    }

}