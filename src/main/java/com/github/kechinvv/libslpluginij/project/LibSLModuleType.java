package com.github.kechinvv.libslpluginij.project;

import com.github.kechinvv.libslpluginij.language.LibSLIcon;
import com.intellij.ide.util.projectWizard.*;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.util.ArrayUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;

public class LibSLModuleType extends ModuleType<LibSLModuleBuilder> {
    protected LibSLModuleType() {
        super("LIBSL_MODULE");
    }

    @NotNull
    public static LibSLModuleType getInstance() {
        return (LibSLModuleType) ModuleTypeManager.getInstance().findByID("LIBSL_MODULE");
    }

    @Override
    public @NotNull LibSLModuleBuilder createModuleBuilder() {
        return new LibSLModuleBuilder();
    }

    @Override
    public @NotNull @Nls(capitalization = Nls.Capitalization.Title) String getName() {
        return "LibSL";
    }

    @Override
    public @NotNull @Nls(capitalization = Nls.Capitalization.Sentence) String getDescription() {
        return "Create specification with LibSL";
    }

    @Override
    public @NotNull Icon getNodeIcon(boolean b) {
        return LibSLIcon.FILE;
    }

//    @Override
//    public ModuleWizardStep @NotNull [] createWizardSteps(@NotNull WizardContext wizardContext,
//                                                          @NotNull LibSLModuleBuilder moduleBuilder,
//                                                          @NotNull ModulesProvider modulesProvider) {
//        return new ModuleWizardStep[]{new LslPanel.form()};
//    }

//    @Override
//    public ModuleWizardStep modifyProjectTypeStep(@NotNull SettingsStep settingsStep, @NotNull final ModuleBuilder moduleBuilder) {
//        return ProjectWizardStepFactory.getInstance().createJavaSettingsStep(settingsStep, moduleBuilder,
//                moduleBuilder::isSuitableSdkType);
//    }

}
