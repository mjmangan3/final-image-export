package com.vereview.process;

import com.aspose.imaging.Image;

import com.aspose.imaging.imageoptions.JpegOptions;
import com.phutility.vault.VeSaaSVaultService;
import com.vereview.file.FileUtils;
import org.apache.commons.io.IOUtils;
//import org.apache.commons.imaging.*;


//import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;


/**
 * Created by mjmangan on 9/27/17.
 */
public class PngProcessor extends ProcessorInfo implements Processor {
    private PngConvert pngConvert = PngConvert.getInstance();

    public PngProcessor() {
        this.service = new VeSaaSVaultService();
    }

    public void copy(Path image, Path exportFile) throws IOException{
        Files.createDirectories(exportFile.getParent());
        Path temp = getTempFile(image);
        BufferedImage png = ImageIO.read(temp.toFile());
        ImageIO.write(png, "jpg", exportFile.toFile());
        //pngConvert.transform(temp, exportFile);
        Files.deleteIfExists(temp);
    }

    public Path getTempFile(Path image) throws IOException{
        Path temp = Files.createTempFile("VE_IMAGE", ".png");
        FileUtils utils = new FileUtils();
        try (InputStream in = utils.getUncompressedInputStream(image)/*service.getUncompressedInputStream(image.toAbsolutePath().toString(), userName, password, true)*/; OutputStream out = Files.newOutputStream(temp)) {
            IOUtils.copy(in, out);
        }catch (IOException ioe){
            throw ioe;
        }
        return temp;
    }
}
