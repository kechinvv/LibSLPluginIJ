package com.github.kechinvv.libslpluginij.project;

import com.intellij.ide.util.importProject.ModuleDescriptor;
import com.intellij.ide.util.importProject.ProjectDescriptor;
import com.intellij.ide.util.projectWizard.importSources.DetectedProjectRoot;
import com.intellij.ide.util.projectWizard.importSources.ProjectFromSourcesBuilder;
import com.intellij.ide.util.projectWizard.importSources.ProjectStructureDetector;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.io.FileUtil;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.github.kechinvv.libslpluginij.LslNames.message;

public class LibSLProjectStructureDetector extends ProjectStructureDetector {

    private final static Logger LOG = Logger.getInstance("LibSLProjectStructureDetector");

    @NotNull
    @Override
    public DirectoryProcessingResult detectRoots(@NotNull File dir,
                                                 File @NotNull [] files,
                                                 @NotNull File base,
                                                 @NotNull List<DetectedProjectRoot> result) {
        try (var walkStream = Files.walk(base.toPath(), 1)) {
            var newBase = walkStream.filter(path -> path.toFile().getName().equals(message("lsl.root.default")))
                    .findFirst().map(Path::toFile)
                    .orElseThrow(() -> new IllegalArgumentException("No spec dir"));
            var patternFileName = Pattern.compile(".*\\.lsl");
            List<File> filesByMask = FileUtil.findFilesByMask(patternFileName, newBase);
            if (!filesByMask.isEmpty()) {
                result.add(new DetectedProjectRoot(dir) {
                    @NotNull
                    @Override
                    public String getRootTypeName() {
                        return message("lsl.module");
                    }
                });
            }
        } catch (IOException e) {
            LOG.error("NIO exception");
        } catch (IllegalArgumentException e) {
            LOG.info("NOT LSL PRJ");
        }
        return DirectoryProcessingResult.SKIP_CHILDREN;
    }

    @Override
    public void setupProjectStructure(@NotNull Collection<DetectedProjectRoot> roots,
                                      @NotNull ProjectDescriptor projectDescriptor,
                                      @NotNull ProjectFromSourcesBuilder builder) {
        if (!roots.isEmpty() && !builder.hasRootsFromOtherDetectors(this)) {
            if (projectDescriptor.getModules().isEmpty()) {
                List<ModuleDescriptor> modules = roots.stream()
                        .map(root -> new ModuleDescriptor(root.getDirectory(), LibSLModuleType.getInstance(), getSources(root)))
                        .collect(Collectors.toList());
                projectDescriptor.setModules(modules);
            }
        }
    }

    private List<LibSLSourceRoot> getSources(DetectedProjectRoot root) {
        var res = new ArrayList<LibSLSourceRoot>();
        try (var walkStream = Files.walk(root.getDirectory().toPath(), 1)) {
            walkStream.filter(path -> path.toFile().getName().equals(message("lsl.root.default")))
                    .findFirst().map((it) -> res.add(new LibSLSourceRoot(it.toFile(), null)))
                    .orElseThrow(() -> new IllegalArgumentException("No spec dir"));
        } catch (IOException e) {
            LOG.error("IO EXC");
        } catch (IllegalArgumentException e) {
            LOG.info("NOT LSL PRJ");
        }
        return res;
    }

    @Override
    public String getDetectorId() {
        return message("lsl.main.name");
    }
}
