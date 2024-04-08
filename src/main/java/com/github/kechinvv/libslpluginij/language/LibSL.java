package com.github.kechinvv.libslpluginij.language;

import com.intellij.lang.Language;

import static com.github.kechinvv.libslpluginij.LslNames.message;

public class LibSL extends Language {

    public static final LibSL INSTANCE = new LibSL();

    private LibSL() {
        super(message("lsl.main.name"));
    }

}
