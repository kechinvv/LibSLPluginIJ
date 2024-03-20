package com.github.kechinvv.libslpluginij.project;

import com.github.kechinvv.libslpluginij.language.LibSLIcon;
import com.intellij.compiler.CompilerWorkspaceConfiguration;
import com.intellij.ide.projectWizard.ProjectSettingsStep;
import com.intellij.ide.starters.local.*;
import com.intellij.ide.starters.local.wizard.StarterInitialStep;
import com.intellij.ide.starters.shared.StarterLanguage;
import com.intellij.ide.starters.shared.StarterProjectType;
import com.intellij.ide.util.projectWizard.*;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.projectRoots.SdkTypeId;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

public class LibSLModuleBuilder extends ModuleBuilder implements SourcePathsBuilder, ModuleBuilderListener {

    public LibSLModuleBuilder() {
        addListener(this);
    }


    @Override
    public String getBuilderId() {
        return "lslbuilder";
    }


    @Override
    public Icon getNodeIcon() {
        return LibSLIcon.FILE;
    }

    @Override
    public String getDescription() {
        return "LibSL. Library specification language.";
    }

    @Override
    public String getPresentableName() {
        return "LibSL Module";
    }

    @Override
    public String getGroupName() {
        return "LibSL";
    }

    private List<Pair<String, String>> mySourcePaths;

    @Override
    public void moduleCreated(@NotNull Module module) {
        CompilerWorkspaceConfiguration.getInstance(module.getProject()).CLEAR_OUTPUT_DIRECTORY = false;
    }

    @Override
    public List<Pair<String, String>> getSourcePaths() {
        if (mySourcePaths == null) {
            final List<Pair<String, String>> paths = new ArrayList<>();
            @NonNls final String path = getContentEntryPath() + File.separator + "spec";
            new File(path).mkdirs();
            paths.add(Pair.create(path, ""));
            return paths;
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

//    public ModuleWizardStep modifyProjectTypeStep(@NotNull SettingsStep settingsStep) {
//        // do not add standard SDK selector at the top
//        return null;
//    }

//    @NotNull
//    @Override
//    protected List<GeneratorAsset> getAssets(@NotNull Starter starter) {
//        return null;
//    }
//
//    @NotNull
//    @Override
//    protected List<StarterLanguage> getLanguages() {
//        return List.of(new StarterLanguage("LibSL", "LibSL", "LibSL", false, "Library specification language"));
//    }
//
//    @NotNull
//    @Override
//    protected List<StarterProjectType> getProjectTypes() {
//        return emptyList();
//    }
//
//    @NotNull
//    @Override
//    protected StarterPack getStarterPack() {
//        return new StarterPack("LibSL", List.of(
//                new Starter("LibSL", "LibSL", getDependencyConfig("/META-INF/plugin.xml"), emptyList())
//        ));
//    }

    @Override
    public ModuleWizardStep[] createWizardSteps(WizardContext wizardContext, ModulesProvider modulesProvider) {
        return new ModuleWizardStep[]{};
    }

    @Override
    public ModuleWizardStep getCustomOptionsStep(WizardContext context, Disposable parentDisposable ) {
        return new LslPanel();
    }

//    @Override
//    public @NotNull StarterInitialStep createOptionsStep(@NotNull StarterContextProvider contextProvider) {
//    }

    public @NotNull List<Class<? extends ModuleWizardStep>> getIgnoredSteps() {
        return List.of(ProjectSettingsStep.class);
    }


}
