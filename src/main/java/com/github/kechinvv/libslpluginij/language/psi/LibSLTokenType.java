package com.github.kechinvv.libslpluginij.language.psi;

import com.github.kechinvv.libslpluginij.language.LibSL;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.*;

public class LibSLTokenType extends IElementType {

    public LibSLTokenType(@NotNull @NonNls String debugName) {
        super(debugName, LibSL.INSTANCE);
    }

    @Override
    public String toString() {
        return "LibSLTokenType." + super.toString();
    }

}
