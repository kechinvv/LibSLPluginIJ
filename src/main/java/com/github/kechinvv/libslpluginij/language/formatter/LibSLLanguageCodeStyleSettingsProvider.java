package com.github.kechinvv.libslpluginij.language.formatter;

import com.github.kechinvv.libslpluginij.language.LibSL;
import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import org.jetbrains.annotations.NotNull;

final class LibSLLanguageCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider {

    @NotNull
    @Override
    public Language getLanguage() {
        return LibSL.INSTANCE;
    }

    @Override
    public void customizeSettings(@NotNull CodeStyleSettingsCustomizable consumer, @NotNull SettingsType settingsType) {
        if (settingsType == SettingsType.SPACING_SETTINGS) {
            consumer.showStandardOptions("SPACE_AROUND_ASSIGNMENT_OPERATORS");
            consumer.renameStandardOption("SPACE_AROUND_ASSIGNMENT_OPERATORS", "Separator");
        } else if (settingsType == SettingsType.BLANK_LINES_SETTINGS) {
            consumer.showStandardOptions("KEEP_BLANK_LINES_IN_CODE");
        }
    }

    @Override
    public String getCodeSample(@NotNull SettingsType settingsType) {
        return """
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
                """;
    }

}
