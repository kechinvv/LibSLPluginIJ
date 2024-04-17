package com.github.kechinvv.libslpluginij.language.utils;

import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class LslPsiUtils {

    public final static LslPsiUtils INSTANCE = new LslPsiUtils();

    private LslPsiUtils() {
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

    public static PsiElement getPrevSiblingSkipSpacesAndComments(PsiElement element) {
        var prevSibling = element.getPrevSibling();
        while (prevSibling instanceof PsiWhiteSpace || prevSibling instanceof PsiComment) {
            prevSibling = prevSibling.getPrevSibling();
        }
        return prevSibling;
    }


}
