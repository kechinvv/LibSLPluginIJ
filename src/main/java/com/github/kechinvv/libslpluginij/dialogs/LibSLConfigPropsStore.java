package com.github.kechinvv.libslpluginij.dialogs;

import com.intellij.openapi.project.Project;

public class LibSLConfigPropsStore {

    static final LibSLConfigProps DEFAULT_PROPERTIES = initDefaultProperties();
    LibSLConfigProps ACTUAL_PROPERTIES = null;

    public LibSLConfigProps getProperties() {
        if (ACTUAL_PROPERTIES == null) ACTUAL_PROPERTIES = DEFAULT_PROPERTIES;
        return ACTUAL_PROPERTIES;
    }

    public static LibSLConfigProps getProperties(Project project) {
        LibSLConfigPropsStore store = LibSLPropsComponent.getInstance(project).getState();
        return store.getProperties();
    }

    private static LibSLConfigProps initDefaultProperties() {
        LibSLConfigProps defaultSettings = new LibSLConfigProps();

        defaultSettings.translatorCmd = "";
        defaultSettings.translatorInput = "--work-dir";
        defaultSettings.taintCmd = "";
        defaultSettings.taintInput = "--work-dir";

        return defaultSettings;
    }
}
