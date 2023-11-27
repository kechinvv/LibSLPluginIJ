package com.github.kechinvv.libslpluginij.language;

import com.intellij.lang.Language;

public class LibSL extends Language {

    public static final LibSL INSTANCE = new LibSL();

    private LibSL() {
        super("LibSL");
    }

}
