package com.vereview.process;

import com.vereview.file.FileUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.imaging.*;
import org.apache.commons.imaging.formats.tiff.constants.TiffConstants;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class TifProcessor extends ProcessorInfo implements Processor {

    public void copy(Path image, Path exportFile) throws IOException {
        try {
            Files.createDirectories(exportFile.getParent());
            Path temp = getTempFile(image);
            BufferedImage tif = Imaging.getBufferedImage(temp.toFile());
            Map<String, Object> params = new HashMap<>();
            params.put(ImagingConstants.PARAM_KEY_COMPRESSION, TiffConstants.TIFF_COMPRESSION_CCITT_GROUP_4);
            Imaging.writeImage(tif, exportFile.toFile(), ImageFormats.TIFF, params);
        }catch (ImageWriteException iwe){
            throw new RuntimeException("failed to write new Group 4 tiff", iwe);
        }catch (ImageReadException ire){
            throw new RuntimeException("failed to read tiff", ire);
        }catch (IOException ioe){
            throw ioe;
        }



    }
    public Path getTempFile(Path image) throws IOException{
        Path temp = Files.createTempFile("VE_IMAGE", ".tif");
        FileUtils utils = new FileUtils();
        try (InputStream in = utils.getUncompressedInputStream(image); OutputStream out = Files.newOutputStream(temp)) {
            IOUtils.copy(in, out);
        }catch (IOException ioe){
            throw ioe;
        }
        return temp;
    }
}
