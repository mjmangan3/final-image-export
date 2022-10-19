package com.vereview.csv;

import com.vereview.TestUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by mjmangan on 9/24/17.
 */
public class OptWriterTest {
    Path testExportPath;

    @Before
    public void setUp(){
        try {
            testExportPath = Files.createTempFile("VE", ".opt");
        }catch (Throwable t){
            Assert.fail(ExceptionUtils.getStackTrace(t));
        }
    }

    @After
    public void tearDown(){
        try {
            Files.deleteIfExists(testExportPath);
        }catch (Throwable t){
            Assert.fail(ExceptionUtils.getStackTrace(t));
        }
    }


    @Test
    public void createOptTest(){
        try {
            OptData data = TestUtils.createTestOptData();
            OptWriter writer = new OptWriter(data);
            writer.createOpt(testExportPath);
            System.out.println(testExportPath.toAbsolutePath().toString());
        }catch (Throwable t){
            Assert.fail(ExceptionUtils.getStackTrace(t));
        }
    }
}
