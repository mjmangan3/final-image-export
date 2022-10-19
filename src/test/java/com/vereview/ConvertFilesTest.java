package com.vereview;

import com.vereview.process.Processor;
import com.vereview.process.ProcessorFactory;
import com.vereview.utils.ExportUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class ConvertFilesTest {

    @Test
    public void convertFilesTest(){
        try {
            final Path baseToDir = Paths.get("\\\\192.168.22.116\\Users\\bob\\Desktop\\Data\\LOAD DATA\\AllState\\to_process");
            final Path baseProcessedDir = Paths.get("\\\\192.168.22.116\\Users\\bob\\Desktop\\Data\\LOAD DATA\\AllState\\post_process2");

            Files.walkFileTree(baseToDir, new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Path newFile = ExportUtils.mergePaths(baseToDir, baseProcessedDir, file);
                    String extension = ExportUtils.getExtension(file);
                    Processor processor = ProcessorFactory.getProcessor(extension);
                    processor.copy(file, newFile);
                    System.out.println("DEBUG | copy successful | File: " + file.toAbsolutePath().toString());
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    System.out.println("ERROR | copy error | File: " + file.toAbsolutePath().toString() + " | exception: " + ExceptionUtils.getStackTrace(exc));
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });

        }catch (Throwable t){
            Assert.fail(ExceptionUtils.getStackTrace(t));
        }
    }


}
