package com.vereview.process;

/**
 * Created by mjmangan on 10/2/17.
 */
public class ProcessorFactory {

    public static Processor getProcessor(String extension){
        if(extension.equalsIgnoreCase("png")){
            return new PngProcessor();
        }else if(extension.equalsIgnoreCase("tif") || extension.equalsIgnoreCase("tiff")) {
            return new TifProcessor();
        }else if(extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg")) {
            return new TifProcessor();
        }else {
            return new BaseProcessor();
        }
    }
}
