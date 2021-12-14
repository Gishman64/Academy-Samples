package io;

import java.io.File;

public interface FileMatchingStrategy {
    boolean matches(File file);
}
