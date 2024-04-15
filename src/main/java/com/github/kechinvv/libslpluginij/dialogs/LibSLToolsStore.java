package com.github.kechinvv.libslpluginij.dialogs;

import com.intellij.openapi.project.Project;

import java.util.HashMap;

public class LibSLToolsStore {

    public HashMap<String, String> ACTUAL_PROPERTIES = new HashMap<>();

    public HashMap<String, String> getActions() {
        return ACTUAL_PROPERTIES;
    }

    public static HashMap<String, String> getActions(Project project) {
        LibSLToolsStore store = LibSLToolsComponent.getInstance(project).getState();
        return store.getActions();
    }

}


