package com.vereview.file;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipException;

public class FileUtils {

    public InputStream getUncompressedInputStream(Path file) throws IOException{

        return Files.newInputStream(file);
        /*InputStream uncompressed = null;
        try {
            uncompressed = new GZIPInputStream(Files.newInputStream(file));
        }catch (ZipException ze){
            if(uncompressed != null){
                uncompressed.close();
            }
            uncompressed = Files.newInputStream(file);
        }
        return uncompressed;
        */
    }
}
