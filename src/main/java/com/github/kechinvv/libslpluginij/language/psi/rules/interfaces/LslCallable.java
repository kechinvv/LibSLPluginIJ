package com.github.kechinvv.libslpluginij.language.psi.rules.interfaces;

import com.github.kechinvv.libslpluginij.language.psi.rules.LslFunctionBody;
import com.github.kechinvv.libslpluginij.language.psi.rules.LslFunctionHeader;

public interface LslCallable {

    LslHeader header();

    LslFunctionBody body();
}
