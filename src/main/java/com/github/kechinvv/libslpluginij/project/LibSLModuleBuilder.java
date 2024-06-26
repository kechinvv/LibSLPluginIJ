package com.github.kechinvv.libslpluginij.project;

import com.github.kechinvv.libslpluginij.language.LibSLIcon;
import com.intellij.codeInsight.actions.ReformatCodeProcessor;
import com.intellij.compiler.CompilerWorkspaceConfiguration;
import com.intellij.ide.IdeBundle;
import com.intellij.ide.projectWizard.ProjectSettingsStep;
import com.intellij.ide.util.projectWizard.*;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.GitRepositoryInitializer;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.application.WriteAction;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.projectRoots.SdkTypeId;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.ModalityUiUtil;
import kotlin.Unit;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.intellij.openapi.progress.ProgressKt.runBackgroundableTask;
import static com.github.kechinvv.libslpluginij.LslNames.message;


public class LibSLModuleBuilder extends ModuleBuilder implements SourcePathsBuilder, ModuleBuilderListener {
    private List<Pair<String, String>> mySourcePaths;
    protected LslGeneratorContext lslGeneratorContext = new LslGeneratorContext();

    @Override
    public int getWeight() {
        return IJ_PLUGIN_WEIGHT;
    }

    public LibSLModuleBuilder() {
        addListener(this);
    }

    @Override
    public String getBuilderId() {
        return message("lsl.module.builder.id");
    }

    @Override
    public Icon getNodeIcon() {
        return LibSLIcon.PROJECT;
    }

    @Override
    public String getDescription() {
        return message("lsl.module.builder.description");
    }

    @Override
    public String getPresentableName() {
        return message("lsl.main.name");
    }

    @Override
    public String getGroupName() {
        return message("lsl.main.name");
    }

    @Override
    public boolean isSuitableSdkType(SdkTypeId sdkType) {
        return false;
    }

    @Override
    public ModuleWizardStep modifyProjectTypeStep(SettingsStep settingsStep) {
        // do not add standard SDK selector at the top
        return null;
    }

    public void setupModule(Module module) throws ConfigurationException {
        super.setupModule(module);
        startGenerator(module);
    }

    private void startGenerator(Module module) {
        var moduleContentRoot = LocalFileSystem.getInstance()
                .refreshAndFindFileByPath(Objects.requireNonNull(super.getContentEntryPath()).replace("\\", "/"));


        if (moduleContentRoot == null) throw new IllegalStateException("Module root not found");


        WriteAction.runAndWait(() -> new File(getSourcePaths().get(0).first +
                File.separator +
                lslGeneratorContext.group.replace(".", File.separator))
                .mkdirs());
        ModalityUiUtil.invokeLaterIfNeeded(ModalityState.NON_MODAL, module.getDisposed(), () -> {
            if (module.isDisposed()) return;

            (new ReformatCodeProcessor(module.getProject(), module, false)).run();

            if (lslGeneratorContext.gitIntegration) {
                runBackgroundableTask(IdeBundle.message("progress.title.creating.git.repository"),
                        module.getProject(),
                        true,
                        (it) -> {
                            Objects.requireNonNull(GitRepositoryInitializer.getInstance())
                                    .initRepository(module.getProject(), moduleContentRoot, true);
                            return Unit.INSTANCE;
                        });
            }
        });
    }

    @Override
    public void setupRootModel(@NotNull ModifiableRootModel rootModel) {
        ContentEntry contentEntry = doAddContentEntry(rootModel);
        if (contentEntry != null) {
            final List<Pair<String, String>> sourcePaths = getSourcePaths();

            if (sourcePaths != null) {
                for (final Pair<String, String> sourcePath : sourcePaths) {
                    String first = sourcePath.first;
                    new File(first).mkdirs();
                    final VirtualFile sourceRoot = LocalFileSystem.getInstance()
                            .refreshAndFindFileByPath(FileUtil.toSystemIndependentName(first));
                    if (sourceRoot != null) {
                        contentEntry.addSourceFolder(sourceRoot, false, sourcePath.second);
                    }
                }
            }
        }
    }

    @Override
    public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext, @NotNull ModulesProvider modulesProvider) {
        return new ModuleWizardStep[]{};
    }

    @Override
    public ModuleWizardStep getCustomOptionsStep(WizardContext context, Disposable parentDisposable) {
        return new LslPanel(new LslContext(context, parentDisposable, lslGeneratorContext, this));
    }


    public @NotNull List<Class<? extends ModuleWizardStep>> getIgnoredSteps() {
        return List.of(ProjectSettingsStep.class);
    }

    @Override
    public void moduleCreated(@NotNull Module module) {
        CompilerWorkspaceConfiguration.getInstance(module.getProject()).CLEAR_OUTPUT_DIRECTORY = false;
    }

    @Override
    public List<Pair<String, String>> getSourcePaths() {
        if (mySourcePaths == null) {
            mySourcePaths = new ArrayList<>();
            @NonNls final String path = getContentEntryPath()
                    + File.separator
                    + message("lsl.root.default");
            new File(path).mkdirs();
            mySourcePaths.add(Pair.create(path, ""));
        }
        return mySourcePaths;
    }

    @Override
    public void setSourcePaths(List<Pair<String, String>> sourcePaths) {
        mySourcePaths = sourcePaths != null ? new ArrayList<>(sourcePaths) : null;
    }

    @Override
    public void addSourcePath(Pair<String, String> sourcePathInfo) {
        if (mySourcePaths == null) {
            mySourcePaths = new ArrayList<>();
        }
        mySourcePaths.add(sourcePathInfo);
    }

    @NotNull
    @Override
    public ModuleType getModuleType() {
        return LibSLModuleType.getInstance();
    }

}
