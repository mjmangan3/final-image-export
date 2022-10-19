package com.vereview.utils;

import com.aspose.imaging.internal.T.S;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

/**
 * Created by mjmangan on 9/30/17.
 */
public class ExportUtils {

    public static String getPaddedNumber(Integer number){
        return String.format("%05d", number);
    }

    public static String getExtension(Path filePath){
        String fp = filePath.toAbsolutePath().toString();
        String fileName = fp.substring(fp.lastIndexOf("\\") + 1);
        String[] parts = fileName.split(Pattern.quote("."));
        if(parts.length >= 2){
            return parts[1];
        }
        return "";
    }

    public static Integer incrementFolder(Integer fileCount, Integer folderNumber){
        if((fileCount != 0) && fileCount % 1000 == 0){
            return ++folderNumber;
        }
        return folderNumber;
    }


    public static Path mergePaths(Path startDir, Path endDir, Path file){
        Path relPath = startDir.relativize(file);
        return Paths.get(endDir.toString(), relPath.toString());
    }
}
