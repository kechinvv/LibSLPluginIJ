package com.github.kechinvv.libslpluginij.language.highlight;


import com.github.kechinvv.libslpluginij.language.LibSLIcon;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static com.github.kechinvv.libslpluginij.LslNames.message;

final class LibSLColorSettingsPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Identifier", LibSLSyntaxHighlighter.ID),
            new AttributesDescriptor("Keyword", LibSLSyntaxHighlighter.KEYWORD),
            new AttributesDescriptor("String", LibSLSyntaxHighlighter.STRING),
            new AttributesDescriptor("Line comment", LibSLSyntaxHighlighter.LINE_COMMENT),
            new AttributesDescriptor("Block comment", LibSLSyntaxHighlighter.BLOCK_COMMENT),
            new AttributesDescriptor("Bad character", LibSLSyntaxHighlighter.BAD_CHARACTER),
    };


    @Nullable
    @Override
    public Icon getIcon() {
        return LibSLIcon.FILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new LibSLSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        try {
            var resource = this.getClass().getResourceAsStream("/examples/CRC32.automaton.lsl").readAllBytes();
            return new String(resource, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @Override
    public AttributesDescriptor @NotNull [] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @Override
    public ColorDescriptor @NotNull [] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return message("lsl.main.name");
    }

}