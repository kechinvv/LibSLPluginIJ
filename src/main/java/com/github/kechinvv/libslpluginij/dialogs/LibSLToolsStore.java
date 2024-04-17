package com.github.kechinvv.libslpluginij.dialogs;

import com.github.kechinvv.libslpluginij.storage.ActionData;
import com.github.kechinvv.libslpluginij.storage.ActionHolderConverter;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.annotations.Attribute;

import java.util.HashMap;

public class LibSLToolsStore {

    @Attribute(converter = ActionHolderConverter.class)
    public HashMap<String, ActionData> ACTUAL_PROPERTIES = new HashMap<>();

    public HashMap<String, ActionData> getActions() {
        return ACTUAL_PROPERTIES;
    }

    public static HashMap<String, ActionData> getActions(Project project) {
        LibSLToolsStore store = LibSLToolsComponent.getInstance(project).getState();
        return store.getActions();
    }

}


