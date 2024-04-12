package com.github.kechinvv.libslpluginij.dialogs;

import com.github.kechinvv.libslpluginij.actions.LslToolAction;
import com.intellij.openapi.project.Project;

import java.util.ArrayList;
import java.util.HashMap;

public class LibSLToolsStore {

    HashMap<String, LslToolAction> ACTUAL_PROPERTIES = new HashMap<>();

    public HashMap<String, LslToolAction>getActions() {
        return ACTUAL_PROPERTIES;
    }

    public static HashMap<String, LslToolAction> getActions(Project project) {
        LibSLToolsStore store = LibSLToolsComponent.getInstance(project).getState();
        return store.getActions();
    }

}
