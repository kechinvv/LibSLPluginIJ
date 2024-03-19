package com.github.kechinvv.libslpluginij.project;

import com.github.kechinvv.libslpluginij.language.LibSLIcon;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.ProjectJdkForModuleStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

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
        return "LibSL Module";
    }

    @Override
    public @NotNull @Nls(capitalization = Nls.Capitalization.Sentence) String getDescription() {
        return "Some test description";
    }

    @Override
    public @NotNull Icon getNodeIcon(boolean b) {
        return LibSLIcon.FILE;
    }

    @NotNull
    @Override
    public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext,
                                                @NotNull LibSLModuleBuilder moduleBuilder,
                                                @NotNull ModulesProvider modulesProvider) {
        return new ModuleWizardStep[]{new ProjectJdkForModuleStep(wizardContext, null) {
            @Override
            public void updateDataModel() {
                super.updateDataModel();
//                moduleBuilder.setModuleJdk(getJdk());
            }
        }};
    }
}
