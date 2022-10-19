package com.vereview.process;

import com.vereview.file.FileUtils;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class JpgProcessor extends ProcessorInfo implements Processor {

    public void copy(Path image, Path exportFile) throws IOException {
        Files.createDirectories(exportFile.getParent());
        Path temp = getTempFile(image);
        BufferedImage jpg = ImageIO.read(temp.toFile());
        ImageIO.write(jpg, "jpg", exportFile.toFile());
        Files.deleteIfExists(temp);
    }

    public Path getTempFile(Path image) throws IOException{
        Path temp = Files.createTempFile("VE_IMAGE", ".jpg");
        FileUtils utils = new FileUtils();
        try (InputStream in = utils.getUncompressedInputStream(image); OutputStream out = Files.newOutputStream(temp)) {
            IOUtils.copy(in, out);
        }catch (IOException ioe){
            throw ioe;
        }
        return temp;
    }
}
