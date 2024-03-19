package com.github.kechinvv.libslpluginij.project;

import com.intellij.ide.util.importProject.ModuleDescriptor;
import com.intellij.ide.util.importProject.ProjectDescriptor;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.ProjectJdkForModuleStep;
import com.intellij.ide.util.projectWizard.importSources.DetectedProjectRoot;
import com.intellij.ide.util.projectWizard.importSources.ProjectFromSourcesBuilder;
import com.intellij.ide.util.projectWizard.importSources.ProjectStructureDetector;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class LibSLProjectStructureDetector extends ProjectStructureDetector {
    @NotNull
    @Override
    public DirectoryProcessingResult detectRoots(@NotNull File dir,
                                                 File @NotNull [] files,
                                                 @NotNull File base,
                                                 @NotNull List<DetectedProjectRoot> result) {
        var pattern = Pattern.compile(".*\\.lsl");
        List<File> filesByMask = FileUtil.findFilesByMask(pattern, base);
        if (!filesByMask.isEmpty()) {
            result.add(new DetectedProjectRoot(dir) {
                @NotNull
                @Override
                public String getRootTypeName() {
                    return "LibSL";
                }
            });
        }
        return DirectoryProcessingResult.SKIP_CHILDREN;
    }

    @Override
    public void setupProjectStructure(@NotNull Collection<DetectedProjectRoot> roots,
                                      @NotNull ProjectDescriptor projectDescriptor,
                                      @NotNull ProjectFromSourcesBuilder builder) {
        if (!roots.isEmpty() && !builder.hasRootsFromOtherDetectors(this)) {
            if (projectDescriptor.getModules().isEmpty()) {
                projectDescriptor.setModules(roots.stream()
                        .map(root -> new ModuleDescriptor(root.getDirectory(), LibSLModuleType.getInstance(),
                                ContainerUtil.emptyList())).toList());
            }
        }
    }

    @NotNull
    @Override
    public List<ModuleWizardStep> createWizardSteps(@NotNull ProjectFromSourcesBuilder builder,
                                                    ProjectDescriptor projectDescriptor,
                                                    Icon stepIcon) {
        ProjectJdkForModuleStep projectJdkForModuleStep = new ProjectJdkForModuleStep(builder.getContext(), null);
        return Collections.singletonList(projectJdkForModuleStep);
    }
}
