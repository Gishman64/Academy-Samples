package io;

import java.io.File;

public class SimpleFilenameMatchingStrategy implements FileMatchingStrategy {

    private final String pattern;

    public SimpleFilenameMatchingStrategy(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean matches(File file) {
        return file.isFile() && file.getName().equals(pattern); //simple fast
    }
}
