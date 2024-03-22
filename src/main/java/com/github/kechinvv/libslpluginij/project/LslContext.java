package com.github.kechinvv.libslpluginij.project;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.Disposable;

public class LslContext {
    String artifact = "lsl-spec";

    WizardContext wizardContext;
    LslGeneratorContext generatorContext;
    ModuleBuilder moduleBuilder;
    Disposable parentDisposable;

    public LslContext(WizardContext context, Disposable parentDisposable, LslGeneratorContext generatorContext, ModuleBuilder moduleBuilder) {
        this.wizardContext = context;
        this.parentDisposable = parentDisposable;
        this.generatorContext = generatorContext;
        this.moduleBuilder = moduleBuilder;
    }
}
