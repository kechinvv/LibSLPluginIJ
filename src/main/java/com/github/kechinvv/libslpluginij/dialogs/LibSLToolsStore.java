package com.github.kechinvv.libslpluginij.dialogs;

import com.github.kechinvv.libslpluginij.actions.LslToolAction;
import com.intellij.openapi.project.Project;

import java.util.ArrayList;

public class LibSLToolsStore {

    ArrayList<LslToolAction> ACTUAL_PROPERTIES = new ArrayList<>();

    public ArrayList<LslToolAction> getActions() {
        return ACTUAL_PROPERTIES;
    }

    public static ArrayList<LslToolAction> getActions(Project project) {
        LibSLToolsStore store = LibSLToolsComponent.getInstance(project).getState();
        return store.getActions();
    }

}
