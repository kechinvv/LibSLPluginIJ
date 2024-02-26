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
import java.util.Map;

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
        return """
                libsl "1.1.0";
                                
                library std
                    version "11"
                    language "Java"
                    url "https://github.com/openjdk/jdk11/blob/master/src/java.base/share/classes/java/lang/StringBuffer.java";
                                
                // imports
                                
                import java/lang/Character;
                                
                automaton SomeAutomaton
                (
                    var att: Object,
                    var mark: int,
                    var position: int
                )
                : LSLSomeAutomaton
                {
                    // states and shifts
                                
                    initstate Allocated;
                    state Initialized;
                                
                    shift Allocated -> Initialized by [
                        // constructors
                        `<init>` (SomeAutomatonT, SomeAutomatonT, int, int, int, int, int),
                        `<init>` (SomeAutomatonT, int),
                    ];
                                
                    shift Initialized -> self by [
                        // instance methods
                        address,
                        alignedSlice,
                        alignmentOffset,
                        capacity,
                        put (SomeAutomatonT, int, byte),
                        putChar (SomeAutomatonT, char),
                        putChar (SomeAutomatonT, int, char),
                        putDouble (SomeAutomatonT, double),
                        putDouble (SomeAutomatonT, int, double),
                        putFloat (SomeAutomatonT, float),
                        putFloat (SomeAutomatonT, int, float),
                        putInt (SomeAutomatonT, int),
                        putInt (SomeAutomatonT, int, int),
                        putLong (SomeAutomatonT, int, long),
                    ];
                                
                    var storage: array<byte> = null;
                                
                    proc _checkBounds(off: int, len: int, size: int): void
                    {
                        if ((off | len | (off + len) | (size - (off + len))) < 0)
                            action THROW_NEW("java.lang.IndexOutOfBoundsException", []);
                    }
                                
                    fun *.asCharBuffer (@target self: DirectByteBuffer): CharBuffer
                    {
                        var off: int = this.position;
                        var lim: int = this.limit;
                        if (off > lim)
                            action THROW_NEW("java.lang.AssertionError", []);   // #warning: assert (off <= lim) in original
                        var rem: int = _remaining();
                        var size: int = rem >> 1;
                                
                        if (UNALIGNED == false && ((this.address + off) % (1 << 1) != 0))
                        {
                           if (this.bigEndian == true) result = (new ByteBufferAsCharBufferBAutomaton(state = Initialized, bb = self, mark = -1, position = 0, limit = size, capacity = size, address = this.address + off)) as CharBuffer;
                           else result = (new ByteBufferAsCharBufferLAutomaton(state = Initialized, bb = self, mark = -1, position = 0, limit = size, capacity = size, address = this.address + off)) as CharBuffer;
                        } else
                        {
                            if (this.nativeByteOrder == true) result = (new DirectCharBufferUAutomaton(state = Initialized, bb = self, mark = -1, position = 0, limit = size, capacity = size, address = off)) as CharBuffer;
                            else result = (new DirectCharBufferSAutomaton(state = Initialized, bb = self, mark = -1, position = 0, limit = size, capacity = size, address = off)) as CharBuffer;
                        }
                    }
                }
                """;
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
        return "LibSL";
    }

}