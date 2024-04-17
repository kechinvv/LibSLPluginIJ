package com.github.kechinvv.libslpluginij.project;

import com.intellij.ide.util.projectWizard.importSources.DetectedSourceRoot;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.github.kechinvv.libslpluginij.LslNames.message;

import java.io.File;

public class LibSLSourceRoot extends DetectedSourceRoot {
    public LibSLSourceRoot(File directory, @Nullable String packagePrefix) {
        super(directory, packagePrefix);
    }

    @Override
    public @NotNull @Nls(capitalization = Nls.Capitalization.Sentence) String getRootTypeName() {
        return message("lsl.main.name");
    }
}
