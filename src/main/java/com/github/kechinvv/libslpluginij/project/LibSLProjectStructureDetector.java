package com.github.kechinvv.libslpluginij.project;

import com.intellij.ide.util.projectWizard.importSources.DetectedProjectRoot;
import com.intellij.ide.util.projectWizard.importSources.ProjectStructureDetector;
import com.intellij.openapi.util.io.FileUtil;
import org.jetbrains.annotations.NotNull;

import java.io.File;
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
}
