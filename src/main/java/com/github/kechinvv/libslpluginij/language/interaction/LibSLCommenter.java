package com.github.kechinvv.libslpluginij.language.interaction;

import com.intellij.lang.Commenter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class LibSLCommenter implements Commenter {

    @Override
    public @NotNull String getLineCommentPrefix() {
        return "//";
    }

    @Override
    public @NotNull String getBlockCommentPrefix() {
        return "/*";
    }

    @Override
    public @NotNull String getBlockCommentSuffix() {
        return "*/";
    }

    @Nullable
    @Override
    public String getCommentedBlockCommentPrefix() {
        return null;
    }

    @Nullable
    @Override
    public String getCommentedBlockCommentSuffix() {
        return null;
    }

}