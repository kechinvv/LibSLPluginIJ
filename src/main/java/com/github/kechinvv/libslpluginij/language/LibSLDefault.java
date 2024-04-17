package com.github.kechinvv.libslpluginij.language;

import java.util.Collection;
import java.util.List;

public class LibSLDefault {

    public static final LibSLDefault INSTANCE = new LibSLDefault();

    private LibSLDefault() {
    }

    public final Collection<String> defaultTypes =
            List.of("bool",
                    "int8", "int16", "int32", "int64",
                    "float32", "float64",
                    "void", "any", "array",
                    "char", "string",
                    "unsigned8", "unsigned16", "unsigned32", "unsigned64");
}
