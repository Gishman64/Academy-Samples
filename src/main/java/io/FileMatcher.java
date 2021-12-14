package io;

import java.nio.file.Path;
import java.util.List;

public interface FileMatcher {
    List<Path> match(Path start);
}
