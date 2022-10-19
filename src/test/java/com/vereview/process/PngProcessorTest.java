package com.vereview.process;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by mjmangan on 9/28/17.
 */
public class PngProcessorTest {

    @Test
    public void toJpgTest(){
        try {
            PngProcessor processor = new PngProcessor();
            Path png = Paths.get("\\\\192.168.22.177\\data\\vereview\\case_13_3413\\binary\\03\\e3\\87\\ORIGINAL", "PAGE_000039.png.gz");
            Path jpg = Files.createTempFile("VE", ".jpg");
            processor.copy(png, jpg);
            System.out.println(jpg.toAbsolutePath().toString());
            Files.deleteIfExists(jpg);
        }catch (Throwable t){
            Assert.fail(ExceptionUtils.getStackTrace(t));
        }
    }
}
