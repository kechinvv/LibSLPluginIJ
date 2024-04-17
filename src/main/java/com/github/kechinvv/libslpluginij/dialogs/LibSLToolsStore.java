package com.github.kechinvv.libslpluginij.dialogs;

import com.github.kechinvv.libslpluginij.storage.ActionData;
import com.github.kechinvv.libslpluginij.storage.ActionHolderConverter;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.annotations.Attribute;

import java.util.HashMap;

public class LibSLToolsStore {

    @Attribute(converter = ActionHolderConverter.class)
    public HashMap<String, ActionData> ACTUAL_PROPERTIES = new HashMap<>();
    public String addedName;
    public String addedCmd;
    public String addedInput;

    public HashMap<String, ActionData> getActions() {
        return ACTUAL_PROPERTIES;
    }

    public String getAddedName() {
        return addedName;
    }

    public String getAddedCmd() {
        return addedCmd;
    }

    public String getAddedInput() {
        return addedInput;
    }


    public static HashMap<String, ActionData> getActions(Project project) {
        LibSLToolsStore store = LibSLToolsComponent.getInstance(project).getState();
        return store.getActions();
    }

    public static String getAddedName(Project project) {
        LibSLToolsStore store = LibSLToolsComponent.getInstance(project).getState();
        return store.getAddedName();
    }

    public static String getAddedCmd(Project project) {
        LibSLToolsStore store = LibSLToolsComponent.getInstance(project).getState();
        return store.getAddedCmd();
    }

    public static String getAddedInput(Project project) {
        LibSLToolsStore store = LibSLToolsComponent.getInstance(project).getState();
        return store.getAddedInput();
    }

    public static void saveAddedFields(Project project, String name, String cmd, String input) {
        LibSLToolsStore store = LibSLToolsComponent.getInstance(project).getState();
        store.addedName = name;
        store.addedCmd = cmd;
        store.addedInput = input;
    }


}


