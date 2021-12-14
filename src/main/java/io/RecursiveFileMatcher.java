package io;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RecursiveFileMatcher implements FileMatcher {

    private final FileMatchingStrategy contentMatcher;
    private final FileFilter ignoreFilter;
    private final int level;

    public RecursiveFileMatcher(FileMatchingStrategy contentMatcher, FileFilter ignoreFilter, int startDepth) {
        this.contentMatcher = contentMatcher;
        this.ignoreFilter = ignoreFilter;
        this.level = startDepth;
    }

    @Override
    public List<Path> match(Path start) {
        return this.match(start, this.level);
    }

    private List<Path> match(Path start, int level) {
        if (level > 3) {
            return Collections.emptyList();
        }
        File[] nodes = start.toFile().listFiles(ignoreFilter);
        List<Path> found = new ArrayList<>(1);
        boolean matches;
        if (nodes != null) {
            for (File fd : nodes) {
                if (fd.isFile()) {
                    matches = contentMatcher.matches(fd); //Matching file content with string
                    if (matches) {
                        found.add(fd.toPath()); //Все на списках, чтобы реализации не отличались
                    }
                } else if (fd.isDirectory() && !isTerminal(fd)) { //keep searchin` in non-empty directories
                    found.addAll(match(fd.toPath(), level + 1));
                }
            }
        }
        return found;
    }

    private boolean isTerminal(File fd) {
        if (!fd.isDirectory()) {
            throw new IllegalArgumentException("Arg must be a directory.");
        }
        return Optional.of(fd)
                .map(File::listFiles)
                .map(files -> files.length == 0)
                .orElse(false);
    }
}
