package io;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.logging.Logger;

public class MultiThreadFileMatcher extends RecursiveTask<List<Path>> implements FileMatcher {

    Logger log = Logger.getLogger(this.getClass().getName());

    private final Path root;
    private final FileMatchingStrategy fms;
    private final FileFilter exceptIgnoring;
    private final int level;

    public MultiThreadFileMatcher(Path root, FileMatchingStrategy fms, FileFilter ignore, int level) {
        this.root = root;
        this.fms = fms;
        this.exceptIgnoring = ignore;
        this.level = level;
    }

    @Override
    public List<Path> match(Path start) {
        return null;
    }

    @Override
    protected List<Path> compute() {

        if (level > 3) { //слишком глубоко ходить не будем, сколько можно то...
            return null;
        }

        var collectiveRes = new LinkedList<Path>();
        var forks = new LinkedList<MultiThreadFileMatcher>();
        final File fRoot = root.toFile();
        Path found = null;

        if (fRoot.isDirectory()) {

            File[] neighbors = fRoot.listFiles(exceptIgnoring);

            assert neighbors != null;
            for (File n : neighbors) {
                if (n.isDirectory() && !isTerminal(n)) {
                    final var sub = new MultiThreadFileMatcher(n.toPath(), fms, exceptIgnoring, level + 1); //Все на списках, чтобы реализации не отличались
                    sub.fork();
                    forks.add(sub);
                } else {
                    final boolean res = fms.matches(n);
                    if (res) {
                        found = n.toPath();
                        break;
                    }
                }
            }
        }

        for (var task : forks) {
            Optional.of(task)
                    .map(ForkJoinTask::join)
                    .ifPresent(collectiveRes::addAll);
        }
        if (found != null) {
            collectiveRes.add(found);
        }
        return collectiveRes;
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
