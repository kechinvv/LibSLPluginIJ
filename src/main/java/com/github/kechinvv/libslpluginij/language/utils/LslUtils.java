package com.github.kechinvv.libslpluginij.language.utils;

import com.github.kechinvv.libslpluginij.antlr.LibSLLexer;
import com.github.kechinvv.libslpluginij.language.LibSLFileType;
import com.github.kechinvv.libslpluginij.language.psi.LibSLPSIFileRoot;
import com.github.kechinvv.libslpluginij.language.psi.rules.LslFile;
import com.github.kechinvv.libslpluginij.language.psi.rules.LslIdentifier;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.util.PsiTreeUtil;

import static com.github.kechinvv.libslpluginij.language.LibSLParserDefinition.tokens;

public class LslUtils {

    public final static LslUtils INSTANCE = new LslUtils();

    private LslUtils() {}

    public static PsiElement createIdentifierTokenFromText(Project project, String text) {
        var child = createIdentifierFromText(project, text)
                .getNode()
                .findChildByType(tokens.get(LibSLLexer.Identifier));
        if (child != null) return child.getPsi();
        else return null;
    }

    public static LslIdentifier createIdentifierFromText(Project project, String text) {
        var javaScriptFile = createFileFromText(project, text);
        return PsiTreeUtil.findChildOfType(javaScriptFile, LslIdentifier.class, true);
    }

    public static LibSLPSIFileRoot createFileFromText(Project project, String text)  {
        return (LibSLPSIFileRoot) PsiFileFactory.getInstance(project)
                .createFileFromText("stub.lsl", LibSLFileType.INSTANCE, text);
    }
}
