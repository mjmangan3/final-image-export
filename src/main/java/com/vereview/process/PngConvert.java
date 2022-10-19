package com.vereview.process;

import com.aspose.imaging.Image;
import com.aspose.imaging.imageoptions.JpegOptions;

import java.io.IOException;
import java.nio.file.Path;

public class PngConvert {
    private static final PngConvert instance = new PngConvert();

    private PngConvert(){

    }

    public static PngConvert getInstance(){
        return instance;
    }

    public synchronized void transform(Path image, Path exportFile) throws IOException {
        try(Image i = Image.load(image.toAbsolutePath().toString())) {
            i.save(exportFile.toAbsolutePath().toString(), new JpegOptions());
        }
    }
}
