package com.github.kechinvv.libslpluginij.dialogs;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

@State(name = "LibSLGenSettings")
public class LibSLPropsComponent implements PersistentStateComponent<LibSLConfigPropsStore> {


    private LibSLConfigPropsStore mySettings = new LibSLConfigPropsStore();

    public static LibSLPropsComponent getInstance(Project project) {
        return project.getService(LibSLPropsComponent.class);
    }

    @NotNull
    @Override
    public LibSLConfigPropsStore getState() {
        return mySettings;
    }

    @Override
    public void loadState(@NotNull LibSLConfigPropsStore state) {
        mySettings = state;
    }

}
