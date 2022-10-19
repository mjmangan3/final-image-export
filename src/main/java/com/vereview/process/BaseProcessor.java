package com.vereview.process;


import com.phutility.vault.VeSaaSVaultService;
import com.vereview.file.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by mjmangan on 10/2/17.
 */
public class BaseProcessor extends ProcessorInfo implements Processor{

    public BaseProcessor() {
        this.service = new VeSaaSVaultService();
    }

    @Override
    public void copy(Path image, Path exportFile) throws IOException{
        Files.createDirectories(exportFile.getParent());
        FileUtils fileUtils = new FileUtils();
        try (InputStream in = fileUtils.getUncompressedInputStream(image); OutputStream out = Files.newOutputStream(exportFile)){
            IOUtils.copy(in, out);
        }catch (IOException ioe){
            throw ioe;
        }
    }
}
