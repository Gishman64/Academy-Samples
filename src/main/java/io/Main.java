package io;

import java.io.FileFilter;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Main {
    public static void main(String[] args) {
        Path root = Path.of("/home/codeinside/");
        final List<String> ignoreDirs = List.of(".gradle", ".m2", ".themes");
        final FileFilter ignoreFilter = file -> !ignoreDirs.contains(file.getName());

        recursive(root, ignoreFilter);

        multi(root, ignoreFilter);

    }

    private static void recursive(Path root, FileFilter ignoreFilter) {
        long d1 = System.currentTimeMillis();

        FileMatcher fm = new RecursiveFileMatcher(
                new SimpleFilenameMatchingStrategy("file3.txt"),
                ignoreFilter, 0);
        var found = fm.match(root);
        long d2 = System.currentTimeMillis();

        System.out.println(found + " За " + (d2 - d1) + "мс");
    }

    private static void multi(Path root, FileFilter ignoreFilter) {
        long d2;
        long d1;
        MultiThreadFileMatcher fmMulti = new MultiThreadFileMatcher(
                root,
                new SimpleFilenameMatchingStrategy("file3.txt"),
                ignoreFilter,
                0);

        final ForkJoinPool forkJoinPool = new ForkJoinPool();
        d1 = System.currentTimeMillis();

        forkJoinPool.execute(fmMulti);

        final List<Path> join = fmMulti.join();

        d2 = System.currentTimeMillis();
        System.out.println(join + " За " + (d2 - d1) + "мс");
    }

}


//}