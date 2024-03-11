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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import static com.github.kechinvv.libslpluginij.language.LibSLParserDefinition.tokens;

public class LslUtils {

    public final static LslUtils INSTANCE = new LslUtils();

    private LslUtils() {
    }

    public static Collection<PsiElement> getAllSiblings(PsiElement element) {
        var siblings = new ArrayList<PsiElement>();
        var temp = element;
        while (temp != null) {
            siblings.add(temp);
            temp = temp.getNextSibling();
        }
        return siblings;
    }

    public static List<PsiElement> getFilteredSiblings(PsiElement element, Predicate<PsiElement> predicate) {
        var siblings = new ArrayList<PsiElement>();
        var temp = element;
        while (temp != null) {
            if (predicate.test(temp)) siblings.add(temp);
            temp = temp.getNextSibling();
        }
        return siblings;
    }


}
