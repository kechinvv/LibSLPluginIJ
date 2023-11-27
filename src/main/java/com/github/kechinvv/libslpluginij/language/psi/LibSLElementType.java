package com.github.kechinvv.libslpluginij.language.psi;

import com.github.kechinvv.libslpluginij.language.LibSL;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.*;

public class LibSLElementType extends IElementType {

    public LibSLElementType(@NotNull @NonNls String debugName) {
        super(debugName, LibSL.INSTANCE);
    }

}
