package com.github.kechinvv.libslpluginij.dialogs;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

@Service
@State(name = "LibSLGenSettings")
public final class LibSLToolsComponent implements PersistentStateComponent<LibSLToolsStore> {

    private LibSLToolsStore mySettings = new LibSLToolsStore();

    public static LibSLToolsComponent getInstance(Project project) {
        return project.getService(LibSLToolsComponent.class);
    }

    @NotNull
    @Override
    public LibSLToolsStore getState() {
        return mySettings;
    }

    @Override
    public void loadState(@NotNull LibSLToolsStore state) {
        mySettings = state;
    }

}
